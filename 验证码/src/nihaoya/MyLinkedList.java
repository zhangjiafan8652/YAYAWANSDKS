package nihaoya;



import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/10/11.
 */

public class MyLinkedList<T>  {

    private  String id;

    private LinkedList<T> mData=new LinkedList<T>();
    private TreeSet<T> mData_set=new TreeSet<T>();

    public LinkedList<T> getmData() {
        return mData;
    }

    public void addAll(Collection<? extends T> mdata){
        mData.clear();

        mData_set.addAll(mdata);

        mData.addAll(mData_set);



    }

    public void add(T mdata){
        mData.clear();
        mData_set.add(mdata);
        mData.addAll(mData_set);

    }

    public T get(int location){
        return mData.get(location);
    }

    public int size(){

        return mData.size();

    }


    public void clear() {
        mData_set.clear();
        mData.clear();
    }

    public void removeAll(Collection<? extends T> mdata){
        mData_set.removeAll(mdata);
        mData.clear();
        mData.addAll(mData_set);

    }
}
