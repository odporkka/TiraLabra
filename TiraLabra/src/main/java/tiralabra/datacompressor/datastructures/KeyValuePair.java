package tiralabra.datacompressor.datastructures;

public class KeyValuePair {

    private final Object value;
    public Object key;

    public KeyValuePair(Object key, Object value) {
        this.value = value;
        this.key = key;
    }
    
    public Object get(Object key){
        if (this.key.equals(key)) return this.value;
        return null;
    }

    public Object getValue(){
        return this.value;
    }

    public Object getKey() {
        return this.key;
    }
}
