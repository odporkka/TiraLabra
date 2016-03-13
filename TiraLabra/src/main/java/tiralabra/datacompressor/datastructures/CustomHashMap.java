/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datacompressor.datastructures;

import java.util.Set;

public class CustomHashMap {
    
    private KeyValuePair[] array;

    public CustomHashMap() {
        this.array = new KeyValuePair[0];
    }

    public CustomHashMap(int size) {
        this.array = new KeyValuePair[size];
    }

    public void put(Object key, Object value) {
        
        KeyValuePair newPair = new KeyValuePair(key, value);
        
        KeyValuePair[] newArray = new KeyValuePair[array.length + 1];

        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = newPair;

        array = newArray;
    }

    public int size() {
        return this.array.length;
    }

    public Object get(Object key) {
        Object o = null;
        for (KeyValuePair pair : array) {
            o = pair.get(key);
            if (o!= null) return o;
        }
        return o;
    }
    
    public Object[] keySet(){
        Object[] keySet = new Object[this.array.length];
        for (int i = 0; i < this.array.length; i++) {
            keySet[i] = this.array[i].key;
        }
        return keySet;
    }
    
    public boolean containsKey(Object key){
        for (KeyValuePair entry : this.array) {
            if (entry.key == key) {
                return true;
            }
        }
        return false;
    }
    
    public void remove(Object key){
        KeyValuePair[] newArray = new KeyValuePair[array.length-1];
        boolean removed = false;
        int j= 0;
        for (int i = 0; i < this.array.length; i++) {
            if (array[i].key == key && !removed){
                removed = true;
            } else {
                newArray[j] = array[i];
                j++;
            }            
        }
        if (removed) array = newArray;
    }
    
    public boolean isEmpty(){
        return this.array.length == 0;
    }
    
    public KeyValuePair[] entrySet(){
        return this.array;
    }
}