package application;

import java.util.Comparator;

public class Heap<T extends Comparator<T>> implements MaxHeapInterface<T> {

	T[] arr;
	int n;

	public Heap(int size) {
		arr = (T[]) new Comparator[size];
	}

	public Heap(T[] newArr) {
		arr = (T[]) new Comparator[newArr.length + 1];
		for (int i = 0; i < newArr.length; i++) {
			arr[i + 1] = newArr[i];
			n++;
		}
		
		for (int i = n / 2; i >= 1; i--) {
			sink(i);
			
		}
	}

	@Override
	public void add(T newEntry) {
		// TODO Auto-generated method stub
		if (n < arr.length - 1) {
			arr[++n] = newEntry;
			swim(n);

		}
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	public boolean less(int x, int y) {
		return (arr[x].compare(arr[x],arr[y]) < 0);

	}

	public void exch(int x, int y) {
		T temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	@Override
	public T removeMax() {
		T max = arr[1];
		exch(1, n--);

		sink(1);

		arr[n + 1] = null;
		return max;
	}

	@Override
	public T getMax() {

		return arr[1];
	}

	@Override
	public boolean isEmpty() {

		return n == 0;
	}

	@Override
	public int getSize() {

		return arr.length ;
	}
	public T getData(int i) {
		return arr[i];
	}
	@Override
	public void clear() {
		// arr[1] = null;
		n = 0;
	}

	private void sink(int k) {

		while (2 * k <= n) {

			int j = 2 * k;

			if (j < n && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;

			exch(k, j);

			k = j;
		}
	}

	@Override
	public void print() {
		for (int i = 1; i <= n; i++)
			System.out.print(arr[i].toString() + " ");
		System.out.println();

	}
	
	public void heapSort() {

		int m = n;
		while(m >= 1) {
			exch(1,m);
			m--;
			int k = 1;
			while (2 * k <= m) {

				int j = 2 * k;

				if (j < m && less(j, j + 1))
					j++;
				if (!less(k, j))
					break;

				exch(k, j);

				k = j;
			}
			
		}

		
	}

}
