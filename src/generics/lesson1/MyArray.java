package generics.lesson1;

import oop.battle_simulation.Hero;

public class MyArray<T extends Hero> {
    //teraz moge dodawac tylko hero
    private T[] array;
    private int size;
    private int capacity;

    public MyArray(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.array = (T[])new Object[capacity];
    }
    public void resize(int newSize){
        if(newSize <= capacity){
            return;
        }
        T[] newArray = (T[])new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        capacity = newSize;
    }


    public void add(T obj){
        if(size == capacity){
            resize(capacity == 0 ? 8 : capacity * 2);
        }
        array[size++] = obj;
    }

    public T get(int index){
        return array[index];
    }

    public int getSize(){
        return size;
    }
}
