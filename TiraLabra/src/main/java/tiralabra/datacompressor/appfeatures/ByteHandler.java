package tiralabra.datacompressor.appfeatures;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ode
 */
public class ByteHandler {

    private byte[] inputArray;
    private byte[] outputArray;
    private HashMap<Byte, String> currentDictionary;

    public ByteHandler() {
    }

    public void setInputArray(byte[] inputArray) {
        this.inputArray = inputArray;
    }

    public void setCurrentDictionary(HashMap<Byte, String> currentDictionary) {
        this.currentDictionary = currentDictionary;
    }

    public void writeOutput() {
        ArrayList<String> bytesAsStrings = new ArrayList<>();
        String s = "";
        int n = 0;
        for (byte b : inputArray) {
            if (n%1024 == 0 && n!= 0) {
                addBytesToArray(s, bytesAsStrings);
                s = "";
                System.out.println("Compressed: "+n/1024+" kb");
            }
            s += currentDictionary.get(b);
            n++;
        }
        addBytesToArray(s, bytesAsStrings);
        makeOutput(bytesAsStrings);
    }

    private void makeOutput(ArrayList<String> bytesAsStrings) {
        int n = 0;
        Byte b;
        for (String s : bytesAsStrings) {
           b = makeByte(s);
        }
    }

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
                sum += Math.pow(2.0, power) * -1;
            }
            power++;
        }
        b = new Byte("" + sum);
        return b;
    }

    private void addBytesToArray(String s, ArrayList<String> bytesAsStrings) {
        if (s == null) return;
        String x = "" + s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            x += s.charAt(i);
            if (i % 8 == 0) {
                bytesAsStrings.add(x);
                x = "";
            }
        }
        if (x.length() > 0) {
            while (x.length() < 8) {
                x += "0";
            }
            bytesAsStrings.add(x);
        }
    }
}
