package application;

public class QuadraticHashing<T extends Comparable<T>> {

	private HNode<T>[] arr;
	private int data;

	public QuadraticHashing() {
          arr = new HNode[11];
          for(int i = 0; i < arr.length; i++) {
        	  arr[i] = new HNode<>();
          }
	}
	public boolean insert(T data) {
		if(this.data >= arr.length/2) {
			this.data = 0;
			rehash();
			//return false;
		}
		return insert(data, arr);
	}
	private boolean insert(T data, HNode<T>[] arr) {

		HNode<T> node = new HNode<T>(data, 'F');
		int hash = Math.abs(data.hashCode()) ;
		int index = hash % arr.length;
		int i = 0;
		while(i < arr.length && arr[index].getFlag() == 'F') {
			i++;
			index = (hash+i*i)%arr.length;
		}
		if(i < arr.length) {
			arr[index] = node;
			this.data++;
			return true;
		}
		return false;
			
	}
	public T delete(T data) {
		int hash = Math.abs(data.hashCode());
		int index = hash % arr.length;
		int i = 0;
		while(i < arr.length && arr[index].getFlag() == 'F') {
			if(arr[index].getData().compareTo(data) == 0) {
				T deleted = arr[index].getData();
				arr[index].setData(null);
				arr[index].setFlag('D');
				this.data--;
				return deleted;
			}
			else {
			i++;
			index = (hash+i*i)%arr.length;
			}
		}
		return null;
			
	}
	public T find(T data) {
		int hash = Math.abs(data.hashCode());// data.hashCode();
		int index = hash % arr.length;
		int i = 0;
		while(i < arr.length && arr[index].getFlag() == 'F') {
			if(arr[index].getData().compareTo(data) == 0) {
				T searched = arr[index].getData();
				return searched;
			}
			else {
			i++;
			index = (hash+i*i)%arr.length;
			}
		}
		return null;
			
	}
	public void print() {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(i+"  "+arr[i].getData()+" "+arr[i].getFlag());
		}
	}
	public boolean isPrime(int x) {
		for(int i = 2; i < x/2; i++)
			if(x % i == 0)
				return false;
		return true;
	}
	public int greatPrime(int x) {
		while(!isPrime(x))
			x++;
		return x;
	}
	public void rehash() {
		HNode<T>[] newArr = new HNode[greatPrime(arr.length * 2)];
		for(int i = 0; i < newArr.length; i++) {
			newArr[i] = new HNode<>();
        }
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].getFlag() == 'F') {
				   insert(arr[i].getData(), newArr);
			}
		}
		arr = newArr;
		System.out.println(arr.length);
	}
	public HNode<T>[] getArr() {
		return arr;
	}
	public void setArr(HNode<T>[] arr) {
		this.arr = arr;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
}
