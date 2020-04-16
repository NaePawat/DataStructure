import java.util.ArrayList;

public class AVLTree {
	AVLNode root;
	int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void makeEmpty() {
		root = null;
		size = 0;
	}

	public Iterator findMin() {
		return findMin(root);
	}

	public Iterator findMin(AVLNode n) {
		if (n == null)
			return null;
		if (n.left == null) {
			Iterator itr = new AVLTreeIterator(n);
			return itr;
		}
		return findMin(n.left);
	}

	public Iterator findMax() {
		return findMax(root);
	}

	public Iterator findMax(AVLNode n) {
		if (n == null)
			return null;
		if (n.right == null) {
			Iterator itr = new AVLTreeIterator(n);
			return itr;
		}
		return findMax(n.right);
	}

	public Iterator find(int v) {
		return find(v, root);
	}

	public Iterator find(int v, AVLNode n) {
		if (n == null)
			return null;
		if (v == n.data)
			return new AVLTreeIterator(n);
		if (v < n.data)
			return find(v, n.left);
		else
			return find(v, n.right);
	}

	public AVLNode insert(int v) {
		return insert(v, root, null);
	}

	// return the node n after v was added into the tree
	public AVLNode insert(int v, AVLNode n, AVLNode parent) {
		if (n == null) {
			n = new AVLNode(v, null, null, parent, 0);
			size++;
		} else if (v < n.data) {
			n.left = insert(v, n.left, n);
		} else if (v > n.data) {
			n.right = insert(v, n.right, n);
		}
		n = rebalance(n);
		return n;
	}

	public AVLNode insertNoBalance(int v) {
		return insertNoBalance(v, root, null);
	}

	private AVLNode insertNoBalance(int v, AVLNode n, AVLNode parent) {
		if (n == null) {
			n = new AVLNode(v, null, null, parent, 0);
			size++;
		} else if (v < n.data) {
			n.left = insertNoBalance(v, n.left, n);
		} else if (v > n.data) {
			n.right = insertNoBalance(v, n.right, n);
		}
		AVLNode.updateHeight(n);
		return n;
	}

	public AVLNode remove(int v) {
		return remove(v, root, null);
	}

	// return the node n after v was removed from the tree
	public AVLNode remove(int v, AVLNode n, AVLNode parent) {
		if (n == null)
			; // do nothing, there is nothing to be removed
		else if (v < n.data) {
			n.left = remove(v, n.left, n);
		} else if (v > n.data) {
			n.right = remove(v, n.right, n);
		} else {
			if (n.left == null && n.right == null) {
				n = null;
				size--;
			} else if (n.left != null && n.right == null) {
				n.left.parent = parent;
				n = n.left;
				size--;
			} else if (n.right != null && n.left == null) {
				n.right.parent = parent;
				n = n.right;
				size--;
			} else {
				AVLTreeIterator i = (AVLTreeIterator) findMin(n.right);
				int minInRightSubtree = i.currentNode.data;
				n.data = minInRightSubtree;
				n.right = remove(minInRightSubtree, n.right, n);
			}
		}
		n = rebalance(n);
		return n;
	}

	public AVLNode rebalance(AVLNode n) {
		if (n == null)
			return n;
		int balance = AVLNode.tiltDegree(n);
		if (balance >= 2) {
			if (AVLNode.tiltDegree(n.left) <= -1) // 3rd case
				n.left = rotateRightChild(n.left);
			n = rotateLeftChild(n); // 1st case
		} else if (balance <= -2) {
			if (AVLNode.tiltDegree(n.right) >= 1) // 4th case
				n.right = rotateLeftChild(n.right);
			n = rotateRightChild(n); // 2nd case
		}
		AVLNode.updateHeight(n);
		return n;
	}

	public AVLNode rotateLeftChild(AVLNode n) {
		AVLNode l = n.left;
		AVLNode lr = n.left.right; // can be null
		n.left = lr;
		if (lr != null) {
			lr.parent = n;
		}
		l.right = n;
		l.parent = n.parent;
		n.parent = l;

		AVLNode.updateHeight(n);
		AVLNode.updateHeight(l);
		return l;
	}

	public AVLNode rotateRightChild(AVLNode n) {
		AVLNode r = n.right;
		AVLNode rl = n.right.left; // can be null
		n.right = rl;
		if (rl != null) {
			rl.parent = n;
		}
		r.left = n;
		r.parent = n.parent;
		n.parent = r;

		AVLNode.updateHeight(n);
		AVLNode.updateHeight(r);
		return r;
	}

	public void makeAVL() throws Exception {
		//code this method
		int[] data = new int[size];
		int index=0;
		AVLTreeIterator a1 = (AVLTreeIterator)findMin(); //start at min value
		while(a1.hasNext()) { // until before final value
			data[index]=a1.currentNode.data;
			index++;
			a1.next();
		}
		a1=(AVLTreeIterator)findMax(); //add final value 
		data[size-1] = a1.currentNode.data;
		this.makeEmpty();
		for(int i=0;i<data.length;i++) {
			this.insert(data[i]);
		}
	}

	public boolean isAVL() { //it is almost impossible to do without recursion!
		// code this method
		return isAVL(this.root);
	}
	
	public boolean isAVL(AVLNode n) { //recursive method
		if(n==null)
			return true;
		if(Math.abs(AVLNode.tiltDegree(n))<=1&&isAVL(n.left)&&isAVL(n.right))
			return true;
		return false;
	}

	public static boolean same(AVLTree t1, AVLTree t2) {
		//code this method
		if(t1.root==null&&t2.root==null) 
			return true;
		ArrayList<Integer> data1 = new ArrayList<Integer>();
		ArrayList<Integer> data2 = new ArrayList<Integer>();
		data1.add(t1.root.data); //store first data
		data2.add(t2.root.data); //store first data
		while(data1.size()>0||data2.size()>0) {
			int temp1 = data1.remove(0); //temp1 = data that remove
			int temp2 = data2.remove(0); //temp2 = data that remove
			if(temp1!=temp2) // find if the trees are identical
				return false;
			AVLTreeIterator a1 =(AVLTreeIterator)t1.find(temp1); //find the position of that value to access its children
			AVLTreeIterator a2 =(AVLTreeIterator)t2.find(temp2); //find the position of that value to access its children
			if(a1.currentNode.left != null) //add children to array list
				data1.add(a1.currentNode.left.data);
			if(a1.currentNode.right != null) //add children to array list
				data1.add(a1.currentNode.right.data);	
			if(a2.currentNode.left != null) //add children to array list
				data2.add(a2.currentNode.left.data);
			if(a2.currentNode.right != null) //add children to array list
				data2.add(a2.currentNode.right.data);	
		}
		return true;
	}

	

	public static void main(String[] args) throws Exception {
		// example: print a tree

		AVLTree t = new AVLTree();
		t.root = t.insertNoBalance(7);
		t.root = t.insertNoBalance(2);
		t.root = t.insertNoBalance(12);
		t.root = t.insertNoBalance(20);
		t.root = t.insertNoBalance(14);
		t.root = t.insertNoBalance(9);
		t.root = t.insertNoBalance(30);
		t.root = t.insertNoBalance(-5);
		t.root = t.insertNoBalance(-10);
		t.root = t.insertNoBalance(4);
		t.root = t.insertNoBalance(-8);
		t.root = t.insertNoBalance(-1);
		t.root = t.insertNoBalance(1);
		t.root = t.insertNoBalance(3);
		t.root = t.insertNoBalance(0);
		t.root = t.insertNoBalance(5);
		t.root = t.insertNoBalance(6);
		t.root = t.insertNoBalance(16);
		t.root = t.insertNoBalance(8);
		BTreePrinter.printNode(t.root);

	}

}
