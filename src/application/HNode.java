package application;

public class HNode<T extends Comparable<T>> {
   
	T data;
	
	char flag;
	
	public HNode() {
		flag = 'E';
	}

	public HNode(T data, char flag) {
		this.data = data;
		this.flag = flag;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		this.flag = flag;
	}
	
	
}
