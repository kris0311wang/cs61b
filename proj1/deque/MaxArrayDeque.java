package deque;

import java.util.Comparator;

class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T> cmp;
    public MaxArrayDeque(Comparator<T> c){
        super();//if delete this line super constructor will also be executed
        cmp=c;
    }
    public MaxArrayDeque(){
    }
    public T max(Comparator<T> c){
        T max=null;
        for (int i=0;i<size();i++){
            T item=get(i);
            if (max==null  || c.compare(item,max)>0){
                max=item;
            }
        }
        return max;
    }
    public T max(){
        return max(cmp);
    }
}