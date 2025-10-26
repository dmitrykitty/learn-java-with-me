package generics.lesson1;

import oop.battle_simulation.Hero;

import java.util.Iterator;

public class MyArray<T> implements Iterable<T>{
    //teraz moge dodawac tylko hero
    private T[] array;
    private int size;
    private int capacity;

    public MyArray(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.array = (T[])new Object[capacity];
    }

    private class MyIterator implements Iterator<T>{
        private int index;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return array[index++];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
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
