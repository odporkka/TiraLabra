package tiralabra.datacompressor.datastructures;

public class CustomArray {

    private Object[] array;

    public CustomArray() {
        this.array = new Object[0];
    }

    public CustomArray(int size) {
        this.array = new Object[size];
    }

    public void add(Object o) {
        Object[] newArray = new Object[array.length + 1];

        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = o;

        array = newArray;
    }

    public int size() {
        return this.array.length;
    }

    public Object get(int index) {
        Object o = null;
        if (index >= 0 && index < array.length) {
            o = array[index];
        }
        return o;
    }
}
