package deque;

import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.println;

class LinkedListDeque<T> implements Deque<T>{
    protected Node<T> sentinal= new Node<T>(null, null, null);
    protected int size;
    public LinkedListDeque(){
       sentinal.post=sentinal;
       sentinal.pre=sentinal;
       size=0;
    }
    @Override
    public void addFirst(T item){
        Node<T> newNode= new Node<T>(sentinal, item, sentinal.post);
        newNode.pre.post=newNode;
        newNode.post.pre=newNode;
        size++;
    }
    @Override
    public void addLast(T item){
        Node<T> newNode= new Node<T>(sentinal.pre, item, sentinal);
        newNode.pre.post=newNode;
        newNode.post.pre=newNode;
        size++;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        Node<T> temp=sentinal.post;
        if(temp!=sentinal){
            print(temp.value);
            temp=temp.post;
        }
        while(temp.post!=sentinal){
            print(" "+temp.value);
            temp=temp.post;
        }
        println();
    }
    @Override
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        Node<T> removeNode=sentinal.post;
        sentinal.post=removeNode.post;
        removeNode.post.pre=sentinal;
        size--;
        return removeNode.value;
    }
    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        Node<T> removeNode=sentinal.pre;
        sentinal.pre=removeNode.pre;
        removeNode.pre.post=sentinal;
        size--;
        return removeNode.value;
    }
    @Override
    public T get(int index){
        Node<T> temp=sentinal.post;
        int i=0;
        while(i!=index && temp!=sentinal){
            temp=temp.post;
            i++;
        }
        if (temp==sentinal){
            return null;
        }
        else{
            return temp.value;
        }
    }
    private T getRecursive(Node<T> node, int index){
        if (node==sentinal){
            return null;
        }
        if (index==0){
            return node.value;
        }
        return getRecursive(node.post,index-1);
    }
    public T getRecursive(int index){
        return getRecursive(sentinal.post,index);
    }
    private static class Node<T>{
        public T value;
        public Node<T> post;
        public Node<T> pre;
        public Node(Node<T> pre,T item,Node<T> post){
            this.value=item;
            this.pre=pre;
            this.post=post;
        }
    }
}