package application;

public class LinkedQueue<T extends Comparable<T>> {
	private Node<T> first;
	private Node<T> last;

	public boolean isEmpty() {
		return (first == null) && (last == null);
	}

	public void clear() {
		first = null;
		last = null;
	}

	public void enqueue(T data) {
		Node<T> newNode = new Node<T>(data);
		if (isEmpty())
			first = newNode;
		else
			last.setNext(newNode) ;;
		last = newNode;
	}
	public Node<T> dequeue() {
		if(first != null && first != last) {
			Node<T> node = first;
			first= first.getNext();
			return node;
		}else if(first != null && first == last) {
			Node<T> node = first;
			first = null;
			last = null;
			return node;
		}
		return null;
	}
	public Node<T> getFront() {
		if(first != null) {
			
			return first;
		}
		return null;
	}
	public void traverse() {
		Node<T> curr = first;
		System.out.print("first --> ");
		while(curr != null) {
			System.out.print(curr.toString()+" --> ");
			curr = curr.getNext();
		}
		System.out.print(" last");
		System.out.println();
	}

	public Node<T> getFirst() {
		return first;
	}

	public void setFirst(Node<T> first) {
		this.first = first;
	}

	public Node<T> getLast() {
		return last;
	}

	public void setLast(Node<T> last) {
		this.last = last;
	}
	
}