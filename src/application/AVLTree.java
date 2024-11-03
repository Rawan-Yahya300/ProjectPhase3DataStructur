//package application;
//
//
//
//public class AVLTree<T extends Comparable<T> > {
//
//	private TNode<T> root;
//
//	public TNode<T> rotateRight(TNode<T> N) {
//
//		TNode<T> c = N.getLeft();
//
//		N.setLeft(c.getRight());
//
//		c.setRight(N);
//
//		return c;
//
//	}
//
//	public TNode<T> rotateLeft(TNode<T> N) {
//
//		TNode<T> c = N.getRight();
//
//		N.setRight(c.getLeft());
//
//		c.setLeft(N);
//
//		return c;
//
//	}
//
//	public TNode<T> rotateRightLeft(TNode<T> N) {
//
//		TNode<T> c = N.getRight();
//
//		N.setRight(rotateRight(c));
//
//		return rotateLeft(N);
//
//	}
//
//	public TNode<T> rotateLeftRight(TNode<T> N) {
//
//		TNode<T> c = N.getLeft();
//
//		N.setLeft(rotateLeft(c));
//
//		return rotateRight(N);
//
//	}
//
//	private TNode<T> rebalance(TNode<T> N) {
//
//		int diff = getHeightDifference(N);
//
//		if (diff > 1) {
//
//			if (getHeightDifference(N.getLeft()) > 0)
//
//				N = rotateRight(N);
//
//			else
//
//				N = rotateLeftRight(N);
//
//		} else if (diff < -1) {
//
//			if (getHeightDifference(N.getRight()) < 0)
//
//				N = rotateLeft(N);
//
//			else
//
//				N = rotateRightLeft(N);
//
//		}
//
//		return N;
//
//	}
//
//	private int getHeightDifference(TNode<T> n) {
//		if (n != null) {
//			int hightLeft = height(n.getLeft());
//			int hightRigth = height(n.getRight());
//			return hightLeft - hightRigth;
//		} else
//			return 0;
//
//	}
//
//	public void insert(T data) {
//
//		if (isEmpty())
//
//			root = new TNode<>(data);
//
//		else {
//
//			TNode<T> rootNode = root;
//
//			addEntry(data, rootNode);
//
//			root = rebalance(rootNode);
//
//		}
//
//	}
//
//	private boolean isEmpty() {
//
//		if (root == null)
//
//			return true;
//
//		return false;
//
//	}
//
//	private void addEntry(T data, TNode<T> node) {
//
//		assert node != null;
//
//		if (data.compareTo(node.getData()) < 0) {// left subtree taller
//
//			if (node.hasLeft()) {
//
//				TNode<T> leftChild = node.getLeft();
//
//				addEntry(data, leftChild);
//
//				node.setLeft(rebalance(leftChild));
//
//			} else
//
//				node.setLeft(new TNode<T>(data));
//
//		} else {// right subtree taller
//
//			if (node.hasRight()) {
//
//				TNode<T> rightChild = node.getRight();
//
//				addEntry(data, rightChild);
//
//				node.setRight(rebalance(rightChild));
//
//			} else
//
//				node.setRight(new TNode<T>(data));
//
//		}
//
//	}
//
//	public TNode<T> deleteData(T data) {
//		TNode<T> current = root;
//		TNode<T> parent = root;
//		boolean isLeftChild = false;
//		if (root == null)
//			return null;// tree is empty
//		while (current != null && !(current.getData().compareTo(data) == 0)) {
//			parent = current;
//			if (data.compareTo(current.getData()) < 0) {
//				current = current.getLeft();
//				isLeftChild = true;
//			} else {
//				current = current.getRight();
//				isLeftChild = false;
//			}
//		}
//		if (current == null)
//			return null; // node to be deleted not found
//		// case 1: node is a leaf
//		if (!current.hasLeft() && !current.hasRight()) {
//			if (current == root) // tree has one node
//				root = null;
//			else {
//				if (isLeftChild)
//					parent.setLeft(null);
//				else
//					parent.setRight(null);
//			}
//		} else if (current.hasLeft() && !current.hasRight()) { // current has left child only
//			if (current == root) {
//				root = current.getLeft();
//			} else if (isLeftChild) {
//				parent.setLeft(current.getLeft());
//			} else {
//				parent.setRight(current.getLeft());
//			}
//		} else if (current.hasRight() && !current.hasLeft()) { // current has right child only
//			if (current == root) {
//				root = current.getRight();
//			} else if (isLeftChild) {
//				parent.setLeft(current.getRight());
//			} else {
//				parent.setRight(current.getRight());
//			}
//		} else {
//			TNode<T> successor = getSuccessor(current);
//			if (current == root)
//				root = successor;
//			else if (isLeftChild) {
//				parent.setLeft(successor);
//			} else {
//				parent.setRight(successor);
//			}
//			successor.setLeft(current.getLeft());
//		}
//
//		// other cases
//		return current;
//	}
//
//	private TNode<T> getSuccessor(TNode<T> node) {
//		TNode<T> parentOfSuccessor = node;
//		TNode<T> successor = node;
//		TNode<T> current = node.getRight();
//		while (current != null) {
//			parentOfSuccessor = successor;
//			successor = current;
//			current = current.getLeft();
//		}
//		if (successor != node.getRight()) { // fix successor connections
//			parentOfSuccessor.setLeft(successor.getRight());
//			successor.setRight(node.getRight());
//		}
//		return successor;
//	}
//
//	public TNode<T> delete(T data) {
//		TNode<T> temp = deleteData(data);
//		if (temp != null) {
//			TNode<T> rootNode = root;
//			root = rebalance(rootNode);
//		}
//		return temp;
//	}
//
//	public int height() {
//		return height(root);
//	}
//
//	private int height(TNode<T> node) {
//		if (node == null)
//			return 0;
//		if (node.isLeaf())
//			return 1;
//		int left = 0;
//		int right = 0;
//		if (node.hasLeft())
//			left = height(node.getLeft());
//		if (node.hasRight())
//			right = height(node.getRight());
//		return 1 + ((left > right) ? left : right);
//	}
//
//
//	public String toString() {
//		return traverseInOrder(root);
//	}
//
//	private String traverseInOrder(TNode<T> node) {
//		if (node == null) {
//			return "";
//		}
//		String result = "";
//		if (node.getLeft() != null) {
//			result += traverseInOrder(node.getLeft());
//		}
//		result += node + ", ";
//		if (node.getRight() != null) {
//			result += traverseInOrder(node.getRight());
//		}
//		return result;
//	}
//
//	public int size() {
//		return size(root);
//	}
//
//	private int size(TNode<T> node) {
//		if (node == null)
//			return 0;
//		return 1 + size(node.getLeft()) + size(node.getRight());
//	}
//
//	public TNode<T> getRoot() {
//		return root;
//	}
//
//	public void setRoot(TNode<T> root) {
//		this.root = root;
//	}
//
//
//	public TNode<T> find(T data) {
//		return find(data, root);
//	}
//
//	private TNode<T> find(T data, TNode<T> node) {
//		if (node != null) {
//			int comp = node.getData().compareTo(data);
//			if (comp == 0)
//				return node;
//			else if (comp > 0 && node.hasLeft())
//				return find(data, node.getLeft());
//			else // if (comp < 0 && node.hasRight())
//				return find(data, node.getRight());
//		}
//		return null;
//	}
//	public String printLevelOrder() {
//		String data = "";
//        if (root == null) {
//            return null;
//        }
//
//        LinkedQueue<T> queue = new LinkedQueue<>();
//        queue.enqueue(root.getData());
//
//        while (!queue.isEmpty()) {
//        	System.out.println("hiiiiii");
//            T currData = queue.dequeue().getData();
//            data  = data +currData + "\n";
//             
//            TNode<T> node = find(currData) ; //new TNode<>(currentNode);
//            if (node.getRight() != null) {
//                queue.enqueue(node.getRight().getData());
//            }
//
//            if (node.getLeft() != null) {
//                queue.enqueue(node.getLeft().getData());
//            }
//        }
//        return data;
//    }
//	
//
//
//}
package application;



public class AVLTree<T extends Comparable<T> > {

	private TNode<T> root;

	public TNode<T> rotateRight(TNode<T> N) {

		TNode<T> c = N.getLeft();

		N.setLeft(c.getRight());

		c.setRight(N);

		return c;

	}

	public TNode<T> rotateLeft(TNode<T> N) {

		TNode<T> c = N.getRight();

		N.setRight(c.getLeft());

		c.setLeft(N);

		return c;

	}

	public TNode<T> rotateRightLeft(TNode<T> N) {

		TNode<T> c = N.getRight();

		N.setRight(rotateRight(c));

		return rotateLeft(N);

	}

	public TNode<T> rotateLeftRight(TNode<T> N) {

		TNode<T> c = N.getLeft();

		N.setLeft(rotateLeft(c));

		return rotateRight(N);

	}

	private TNode<T> rebalance(TNode<T> N) {

		int diff = getHeightDifference(N);

		if (diff > 1) {

			if (getHeightDifference(N.getLeft()) > 0)

				N = rotateRight(N);

			else

				N = rotateLeftRight(N);

		} else if (diff < -1) {

			if (getHeightDifference(N.getRight()) < 0)

				N = rotateLeft(N);

			else

				N = rotateRightLeft(N);

		}

		return N;

	}

	private int getHeightDifference(TNode<T> n) {
		if (n != null) {
			int hightLeft = height(n.getLeft());
			int hightRigth = height(n.getRight());
			return hightLeft - hightRigth;
		} else
			return 0;

	}

	public void insert(T data) {

		if (isEmpty())

			root = new TNode<>(data);

		else {

			TNode<T> rootNode = root;

			addEntry(data, rootNode);

			root = rebalance(rootNode);

		}

	}

	private boolean isEmpty() {

		if (root == null)

			return true;

		return false;

	}

	private void addEntry(T data, TNode<T> node) {

		assert node != null;

		if (data.compareTo(node.getData()) < 0) {// left subtree taller

			if (node.hasLeft()) {

				TNode<T> leftChild = node.getLeft();

				addEntry(data, leftChild);

				node.setLeft(rebalance(leftChild));

			} else

				node.setLeft(new TNode<T>(data));

		} else {// right subtree taller

			if (node.hasRight()) {

				TNode<T> rightChild = node.getRight();

				addEntry(data, rightChild);

				node.setRight(rebalance(rightChild));

			} else

				node.setRight(new TNode<T>(data));

		}

	}

	public TNode<T> deleteData(T data) {
		TNode<T> current = root;
		TNode<T> parent = root;
		boolean isLeftChild = false;
		if (root == null)
			return null;// tree is empty
		while (current != null && !(current.getData().compareTo(data) == 0)) {
			parent = current;
			if (data.compareTo(current.getData()) < 0) {
				current = current.getLeft();
				isLeftChild = true;
			} else {
				current = current.getRight();
				isLeftChild = false;
			}
		}
		if (current == null)
			return null; // node to be deleted not found
		// case 1: node is a leaf
		if (!current.hasLeft() && !current.hasRight()) {
			if (current == root) // tree has one node
				root = null;
			else {
				if (isLeftChild)
					parent.setLeft(null);
				else
					parent.setRight(null);
			}
		} else if (current.hasLeft() && !current.hasRight()) { // current has left child only
			if (current == root) {
				root = current.getLeft();
			} else if (isLeftChild) {
				parent.setLeft(current.getLeft());
			} else {
				parent.setRight(current.getLeft());
			}
		} else if (current.hasRight() && !current.hasLeft()) { // current has right child only
			if (current == root) {
				root = current.getRight();
			} else if (isLeftChild) {
				parent.setLeft(current.getRight());
			} else {
				parent.setRight(current.getRight());
			}
		} else {
			TNode<T> successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild) {
				parent.setLeft(successor);
			} else {
				parent.setRight(successor);
			}
			successor.setLeft(current.getLeft());
		}

		// other cases
		return current;
	}

	private TNode<T> getSuccessor(TNode<T> node) {
		TNode<T> parentOfSuccessor = node;
		TNode<T> successor = node;
		TNode<T> current = node.getRight();
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.getLeft();
		}
		if (successor != node.getRight()) { // fix successor connections
			parentOfSuccessor.setLeft(successor.getRight());
			successor.setRight(node.getRight());
		}
		return successor;
	}

	public TNode<T> delete(T data) {
		TNode<T> temp = deleteData(data);
		if (temp != null) {
			TNode<T> rootNode = root;
			root = rebalance(rootNode);
		}
		return temp;
	}

	public int height() {
		return height(root);
	}

	private int height(TNode<T> node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		int left = 0;
		int right = 0;
		if (node.hasLeft())
			left = height(node.getLeft());
		if (node.hasRight())
			right = height(node.getRight());
		return 1 + ((left > right) ? left : right);
	}


	public String toString() {
		return traverseInOrder(root);
	}

	private String traverseInOrder(TNode<T> node) {
		if (node == null) {
			return "";
		}
		String result = "";
		if (node.getLeft() != null) {
			result += traverseInOrder(node.getLeft());
		}
		result += node + ", ";
		if (node.getRight() != null) {
			result += traverseInOrder(node.getRight());
		}
		return result;
	}

	public int size() {
		return size(root);
	}

	private int size(TNode<T> node) {
		if (node == null)
			return 0;
		return 1 + size(node.getLeft()) + size(node.getRight());
	}

	public TNode<T> getRoot() {
		return root;
	}

	public void setRoot(TNode<T> root) {
		this.root = root;
	}


	public TNode<T> find(T data) {
		return find(data, root);
	}

	private TNode<T> find(T data, TNode<T> node) {
		if (node != null) {
			int comp = node.getData().compareTo(data);
			if (comp == 0)
				return node;
			else if (comp > 0 && node.hasLeft())
				return find(data, node.getLeft());
			else // if (comp < 0 && node.hasRight())
				return find(data, node.getRight());
		}
		return null;
	}
	public String printLevelOrder() {
		String data = "";
        if (root == null) {
            return null;
        }

        LinkedQueue<T> queue = new LinkedQueue<>();
        queue.enqueue(root.getData());

        while (!queue.isEmpty()) {
            T currData = queue.dequeue().getData();
            data  = data +currData + "\n";
             
            TNode<T> node = find(currData) ; //new TNode<>(currentNode);
            if (node.getRight() != null) {
                queue.enqueue(node.getRight().getData());
            }

            if (node.getLeft() != null) {
                queue.enqueue(node.getLeft().getData());
            }
        }
        return data;
    }
	


}