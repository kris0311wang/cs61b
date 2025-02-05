package deque;

import java.util.Iterator;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.println;

public class ArrayDeque<T> implements Deque<T>,Iterable<T> {
    protected int capacity =8;
    protected T[] array=null;
    protected int head;
    protected int tail;
    public ArrayDeque(){
        capacity =8;
        array=(T[]) new Object[capacity];
        head=0;
        tail=0;
    }
    @Override
    public int size(){
        return (capacity+tail-head)%capacity;
    }
    private void resize(int newCapacity){
        assert size()<=newCapacity-1;//need one extra position to differ between full and empty
        T[] newArray=(T[])new Object[newCapacity];
        if (head<=tail){
            System.arraycopy(array,head,newArray,0,size());
        }else{//tail is in front of head
            System.arraycopy(array,head,newArray,0,capacity-head);
            System.arraycopy(array,0,newArray,capacity-head,tail);
        }
        tail=size();
        head=0;
        capacity=newCapacity;
        array=newArray;
    }
    @Override
    public void addFirst(T item){
        if(capacity-1==size()){
            resize(2*capacity);
        }
        head=(capacity+head-1)%capacity;
        array[head]=item;
    }
    @Override
    public void addLast(T item){
        if(capacity-1==size()){
            resize(2*capacity);
        }
        array[tail]=item;
        tail=(tail+1)%capacity;
    }
    @Override
    public void printDeque(){
        if (!isEmpty()){
            print(array[head]);
            for (int i = (head + 1)%capacity; i != tail; i = (i + 1) % capacity) {
                print(" "+array[i]);
            }
        }
        println();
    }
    @Override
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        T value=array[head];
        array[head]=null;
        head=(head+1)%capacity;
        if (size()<capacity/2){
            resize(capacity*2/3);
        }
        return value;
    }
    @Override
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        int newtail=(tail+capacity-1)%capacity;
        T value=array[newtail];
        array[newtail]=null;
        tail=newtail;
        if (size()<capacity/2){
            resize(capacity*2/3);
        }
        return value;
    }
    @Override
    public T get(int index){
        if (index>=size()){
            return null;
        }
        return array[(head+index)%capacity];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator<T>();
    }
    private class ArrayDequeIterator<T> implements Iterator<T>{
        int index=0;
        @Override
        public boolean hasNext() {
            if(tail==index){
                return false;
            }
            return true;
        }
        public ArrayDequeIterator(){
            index=head;
        }

        @Override
        public T next() {
            int oldindex=index;
            index=(index+1)%capacity;
            return (T) array[oldindex];
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null){
            return false;
        }
        if (obj==this){
            return true;
        }
        if (obj.getClass()!=this.getClass()){
            return false;
        }
        ArrayDeque<T> other=(ArrayDeque<T>) obj;
        if (size()!=other.size()){
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(other.get(i))){
                return false;
            }
        }
        return true;
    }
}