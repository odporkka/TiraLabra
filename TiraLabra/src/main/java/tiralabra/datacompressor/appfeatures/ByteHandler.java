package tiralabra.datacompressor.appfeatures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for custom byte writing and handling.
 *
 * @author ode
 */
public class ByteHandler {

    private byte[] inputArray;
    private byte[] outputArray;
    private HashMap<Byte, String> currentDictionary;
    private File resultFile;
    private File extractFile;
    private String header;
    private String fileExt;

    public ByteHandler() {
    }

    public void setInputArray(byte[] inputArray) {
        this.inputArray = inputArray;
    }

    public void setCurrentDictionary(HashMap<Byte, String> currentDictionary) {
        this.currentDictionary = currentDictionary;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileExt() {
        return fileExt;
    }

    /**
     * Method for writing packed file.
     *
     * @param header
     */
    public void writeOutput(String header) {
        //ArrayList containing output bytes in string form
        ArrayList<String> bytesAsStrings = new ArrayList<>();

        //Adding header if needed
        if (header != null) {
            this.header = header;
        }

        //Strign s contains one big string for output bytes up to 1 kB
        String s = "";
        int n = 0;
        System.out.println("Starting to translate...");
        int totalSize = inputArray.length;
        NumberFormat nf = new DecimalFormat("#0.00");
        for (byte b : inputArray) {
            if (s.length() >= 8192) {
                //When 1 kB is filled method for adding bytes in array is called
                String y = s.substring(0, 8192);
                addBytesToArray(y, bytesAsStrings);
                y = s.substring(8192);
                s = "" + y;
                double progress = ((n * 100.0) / totalSize);
                System.out.println(nf.format(progress) + " % done");
            }
            s += currentDictionary.get(b);
            n++;
        }
        addBytesToArray(s, bytesAsStrings);
        System.out.println("Done!");
        System.out.println(bytesAsStrings.get(0));
        System.out.println(bytesAsStrings.get(1));
        makeOutput(bytesAsStrings);
    }

    /**
     * Method for making String object from each output byte. Parameter s
     * contains String for up to 1 kB of input file translated with dictionary.
     * Method splits that String into 8 bit (mark) Strings and adds them into
     * bytesAsString array.
     *
     * @param s
     * @param bytesAsStrings
     */
    private void addBytesToArray(String s, ArrayList<String> bytesAsStrings) {
        if (s == null) {
            return;
        }
        //8 mark Strings for each byte
        String x = "";
        for (int i = 0; i < s.length(); i++) {
            if (i % 8 == 0 && i != 0) {
                bytesAsStrings.add(x);
                x = "";
            }
            x += s.charAt(i);
        }
        //If last String is less than 8 marks adds extra '0's at the end of
        //String. Note that this only happens at the last call of this method 
        //for every compression since previous calls each had 8192 marks (1 kB)
        if (x.length() > 0) {
            while (x.length() <= 8) {
                x += "0";
            }
            bytesAsStrings.add(x);
        }
    }

    /**
     * Makes byte array output from Strings in bytesAsString.
     *
     * @param bytesAsStrings
     */
    private void makeOutput(ArrayList<String> bytesAsStrings) {
        int n = 0;
        Byte b;
        //Calculating output array size.
        int size = bytesAsStrings.size();
        outputArray = new byte[size];

        System.out.println("Final size will be " + (size / (1024 * 1.0)) + " kB");
        //Making Bytes and addding them into output array.
        System.out.println("Making final file...");
        for (String s : bytesAsStrings) {
            b = makeByte(s);
            outputArray[n] = b;
            n++;
        }
        try {
            makeFile();
        } catch (IOException ex) {
            Logger.getLogger(ByteHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method for making actual Bytes from bytesAsStrings array. Writes in two's
     * complement form.
     *
     * @param s
     * @return
     */
    private Byte makeByte(String s) {
        Byte b;
        boolean negative = false;
        int sum = 0;
        if (s.charAt(0) == '1') {
            negative = true;
        }
        int power = 0;
        for (int i = 7; i > 0; i--) {
            if (!negative && s.charAt(i) == '1') {
                sum += Math.pow(2.0, power);
            } else if (negative && s.charAt(i) == '0') {
                sum += Math.pow(2.0, power);
            }
            power++;
        }
        if (negative) {
            sum += 1;
            sum = sum * (-1);
        }
        b = new Byte("" + sum);
        return b;
    }

    /**
     * Makes the actual compressed file. Imports header for dictionary if set.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void makeFile() throws FileNotFoundException, IOException {
        if (outputArray == null) {
            return;
        }
        System.out.println("Writing outputfile..");
        resultFile = new File(System.getProperty("user.home") + "/result" + fileExt);
        FileOutputStream fos = new FileOutputStream(resultFile);

        if (header != null) {
            fos.write(header.getBytes(Charset.defaultCharset()));
        }

        fos.write(outputArray);
        fos.close();
        System.out.println("Output stream closed!");
        System.out.println("File written at /home/result"+this.fileExt);
        System.out.println("\n");
        System.out.println("--------");
        System.out.println("\n");
    }

    public String getByteString(byte[] byteArray) {
        String result = "";
        for (byte c : byteArray) {
            result += getStringFromByte(c);
        }
        return result;
    }

    private String getStringFromByte(Byte b) {
        String byteAsString = "";
        boolean negative = true;
        int value = b.intValue();
        if (value >= 0) {
            negative = false;
            byteAsString += '0';
        } else {
            byteAsString += '1';
            value = value * (-1);
        }

        int power = 64;
        if (!negative) {
            for (int i = 0; i < 7; i++) {
                if (value >= power) {
                    byteAsString += '1';
                    value -= power;
                } else {
                    byteAsString += '0';
                }
                power = power / 2;
            }
        }
        if (negative) {
            value -= 1;
            power = 64;
            for (int i = 0; i < 7; i++) {
                if (value >= power) {
                    byteAsString += '0';
                    value -= power;
                } else {
                    byteAsString += '1';
                }
                power = power / 2;
            }

        }
        return byteAsString;
    }

    public void setOutputArray(byte[] outputArray) {
        this.outputArray = outputArray;
    }

    public void writeExtracted() throws IOException {
        try {
            extractFile = new File(System.getProperty("user.home") + "/extracted"
            +this.fileExt);
            FileOutputStream fos = new FileOutputStream(extractFile);
            fos.write(outputArray);
            fos.close();
            System.out.println("File extracted at: " + extractFile.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ByteHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
