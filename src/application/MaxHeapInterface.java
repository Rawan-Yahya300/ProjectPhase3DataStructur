package application;

import java.util.Comparator;

public interface MaxHeapInterface<T extends Comparator<T>>{

 public void add(T newEntry);

 public T removeMax();

public T getMax();

public boolean isEmpty();

public int getSize();

public void clear();
public void print();

}// end MaxHeapinterface
