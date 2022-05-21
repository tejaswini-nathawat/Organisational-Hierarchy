
class avlnode {
	int element;
	int height;
	avlnode left;
	avlnode right;
	avlnode parentavl;
	Node treenode;

	// constructor default

	public avlnode() {

		left = null;
		right = null;
		element = 0;
		height = 0;
	}

	// paramters

	public avlnode(int no, Node p) {

		left = null;
		right = null;
		element = no;
		treenode = p;

	}

}

class avltree {

	private Node N;
	private avlnode r;

	public avltree() {

		N = null;
		r = null;
	}

	// fn1
	public boolean emptyfn() {

		if (r == null)
			return true;

		else
			return false;
	}

	int geth(avlnode node) {

		if (node == null)
			return -1;
		else
			return node.height;
	}

	// fn2
	int getmaxh(int ht1, int ht2) {

		if (ht1 > ht2)
			return ht1;
		else
			return ht2;
	}

	// fn3
	public int size() {

		return sizefn(r);
	}

	private int sizefn(avlnode n) {
		if (n == null)
			return 0;

		else {
			int len = 1;
			len = len + sizefn(n.left);
			len = len + sizefn(n.right);
			return len;
		}
	}

	// fn4
	public Node search(int idno) {

		return search(r, idno);

	}

	private Node search(avlnode n, int idno) {

		if (n == null)

			return null;

		if (n.element == idno)
			return n.treenode;
		if (n.element > idno)
			return search(n.left, idno);
		if (n.element < idno)
			return search(n.right, idno);
		return n.treenode;

	}

	public void insertelement(int idno, Node j) {

		r = insertelement(idno, j, r);

	}

	private avlnode insertelement(int idno, Node j, avlnode n) {

		if (n == null)
			n = new avlnode(idno, j);

		else if (idno < n.element) {

			n.left = insertelement(idno, j, n.left);
			if (geth(n.left) - geth(n.right) == 2) {

				if (idno < n.left.element)
					n = singlerotwithleft(n);
				else
					n = doublerotatewithleft(n);

			}

		}

		else if (idno > n.element) {

			n.right = insertelement(idno, j, n.right);
			if (geth(n.right) - geth(n.left) == 2) {

				if (idno > n.right.element)
					n = singlerotwithright(n);
				else
					n = doublerotatewithright(n);
			}
		}

		else
			; // if id is there but this case wont arise as we will check this in main fn

		n.height = getmaxh(geth(n.left), geth(n.right)) + 1;

		return n;
	}

	private avlnode singlerotwithleft(avlnode y) {
		avlnode z = y.left;
		y.left = z.right;
		z.right = y;
		y.height = getmaxh(geth(y.left), geth(y.right)) + 1;
		z.height = getmaxh(geth(z.left), y.height) + 1;
		return z;
	}

	private avlnode singlerotwithright(avlnode z) {
		avlnode y = z.right;
		z.right = y.left;
		y.left = z;
		z.height = getmaxh(geth(z.left), geth(z.right)) + 1;
		y.height = getmaxh(geth(y.right), z.height) + 1;
		return y;
	}

	private avlnode doublerotatewithleft(avlnode x) {
		x.left = singlerotwithright(x.left);
		return singlerotwithleft(x);
	}

	private avlnode doublerotatewithright(avlnode x) {
		x.right = singlerotwithleft(x.right);
		return singlerotwithright(x);
	}

	// deletion

	avlnode findmin(avlnode node) {

		avlnode pred = node;

		while (pred.left != null)
			pred = pred.left;

		return pred;
	}

	int Balancecdn(avlnode node) {

		if (node == null)
			return 0;

		return geth(node.left) - geth(node.right);
	}

	public void deletion(int key) {

		deletenode(r, key);
	}

	private void deletenode(avlnode node, int key) {

		if (node == null)
			return;

		if (key < node.element)
			deletenode(node.left, key);
		else if (key > node.element)
			deletenode(node.right, key);

		// if key==node.element

		else {

			// case1 if the given node is leaf node

			if ((node.left == null) && (node.right == null)) {

				node = null;
				return;

			}

			if ((node.left == null) || (node.right == null)) {

				if (node.left == null) {
					if (node.parentavl.left.equals(node))
						node.parentavl.left = node.right;
					else
						node.parentavl.right = node.right;
				}

				if (node.right == null) {
					if (node.parentavl.left.equals(node))
						node.parentavl.left = node.left;
					else
						node.parentavl.right = node.left;
				}

				node = null;

			}

			// case 3 when node with both child is to be deleted

			else {

				avlnode h = findmin(node.right);

				node.element = h.element;
				deletenode(node.right, h.element);
			}
		}

		// if key not found

		if (node == null)
			return;

		// ba;ance the tree

		node.height = getmaxh(geth(node.left), geth(node.right)) + 1;

		int balance = Balancecdn(node);

		if (balance > 1) {

			if (Balancecdn(node.left) >= 0)

				node = singlerotwithright(node);
		}

		if (balance > 1) {

			if (Balancecdn(node.left) < 0)

				node = doublerotatewithright(node);
		}

		if (balance < -1) {
			if (Balancecdn(node.right) <= 0)
				node = singlerotwithleft(node);
		}

		if (balance < -1) {

			if (Balancecdn(node.right) > 0)

				node = doublerotatewithleft(node);
		}

		return;
	}

}

class emptyqexception extends Exception {

	public emptyqexception(String str) {

		super(str);

	}

}

// Tree node
class Node {
	int idn;
	Node parent;
	int num;

	int depth;

	Node[] child = new Node[num];

	// constructor

	public Node() {
		parent = null;
		num = 0;

	}
}

class queue {

	Node[] gentreenode;

	int f = 0;

	int r = 0;

	public queue(int sizeoftree) {

		gentreenode = new Node[sizeoftree + 10];
	}

	boolean isqempty() {
		if (f == r) {
			return true;
		} else
			return false;
	}

	void entqueue(Node node) {

		gentreenode[r] = node;
		r++;
	}

	Node dequeue() throws emptyqexception {

		if (isqempty())
			throw new emptyqexception("q is empty");

		else {
			Node o = gentreenode[f];
			f = f + 1;
			return o;
		}
	}

}

public class OrgHierarchy implements OrgHierarchyInterface {

	avltree avtree = new avltree();

	// root node
	Node root;

	public OrgHierarchy() {

		root = null;

	}

	public boolean isEmpty() {

		if (root == null)
			return true;
		else
			return false;

	}

	public int size() {

		return avtree.size();

		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");

	}

	public int level(int id) throws IllegalIDException {

		// your implementation

		Node x = avtree.search(id);

		if (x == null)
			throw new IllegalIDException("id not found");

		return x.depth;

		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

	public void hireOwner(int id) throws NotEmptyException {

		// your implementation

		if (root != null)
			throw new NotEmptyException("owner present ");

		root = new Node();

		root.idn = id;

		root.depth = 1;

		avtree.insertelement(id, root);
	}

	public void hireEmployee(int id, int bossid) throws IllegalIDException {

		// your implementation

		Node duplicate = avtree.search(id);
		if (duplicate != null)
			throw new IllegalIDException("id already there");

		Node newemployee = new Node();
		Node bossnode = avtree.search(bossid);
		if (bossnode == null)
			throw new IllegalIDException("bossid wrong");
		newemployee.idn = id;
		newemployee.parent = bossnode;
		newemployee.num = 0;
		newemployee.depth = bossnode.depth + 1;

		// grow bossnode children
		// System.out.println(bossnode.num);

		if (bossnode.num + 1 > bossnode.child.length) {

			Node[] A = new Node[2 * (bossnode.num + 1)];

			for (int i = 0; i < bossnode.num; i++) {

				A[i] = bossnode.child[i];
			}

			// System.out.println( "print it" + bossnode.child[0].idn);

			bossnode.child = A;

			bossnode.child[bossnode.num] = newemployee;

			bossnode.num++;
		}

		else {

			bossnode.child[bossnode.num] = newemployee;
			bossnode.num++;

		}
		avtree.insertelement(id, newemployee);

		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");

	}

	public void fireEmployee(int id) throws IllegalIDException {
		// your implementation

         if(this.isEmpty()) throw new IllegalIDException("empty");

		if (id == root.idn)
			throw new IllegalIDException("owner cant be deleted");

		Node n = avtree.search(id);

		if (n == null)
			throw new IllegalIDException("id not found");
		if (n.num != 0)
			throw new IllegalIDException("id is not of leaf node");

		

		Node npar = n.parent;

		for (int j = 0; j < npar.child.length; j++) {

			if (n.equals(npar.child[j])) {
				npar.child[j] = null;
				break;
			}

		}
		n.num = 0;
		n = null;

		// restructure npar
		int p = 0;

		Node c[] = new Node[npar.num - 1];
		for (int i = 0; i < npar.child.length; i++) {

			if (npar.child[i] == null)
				continue;
			else {
				c[p] = npar.child[i];

				p++;

			}

		}

		npar.child = c;
		npar.num--;
		
		//avtree.deletion(id);

		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");

	}

	public void fireEmployee(int id, int manageid) throws IllegalIDException {

		// your implementation
		if(this.isEmpty()) throw new IllegalIDException("empty");
		if (id == root.idn)
			throw new IllegalIDException("owner cant be deleted");

		Node firedemp = avtree.search(id);

		if (firedemp == null)
			throw new IllegalIDException("id not found");

		Node manager = avtree.search(manageid);

		if (manager == null)
			throw new IllegalIDException("id not found");

		// manager children update

		Node[] A = new Node[(manager.child.length + firedemp.child.length)];

		int i;

		for (i = 0; i < manager.child.length; i++) {

			A[i] = manager.child[i];
		}

		for (int j = 0; j < firedemp.num; j++) {

			firedemp.child[j].parent = manager;

			A[i] = firedemp.child[j];

			A[i].depth = manager.depth + 1;

			i++;
		}

		manager.child = A;

		manager.num = manager.num + firedemp.num;

		//avtree.deletion(id);

		Node parent = firedemp.parent;

		// System.out.println(firedemp.parent.idn);

		for (int j = 0; j < parent.child.length; j++) {

			if (firedemp.equals(parent.child[j]))
				parent.child[j] = null;

		}

		firedemp.num = 0;
		firedemp = null;

		// if(parent.child[0]==null) System.out.println("true");

		// restrucuture firedemp parent node

		Node B[] = new Node[parent.num - 1];
		int p = 0;

		for (int h = 0; h < parent.child.length; h++) {

			if (parent.child[h] == null) {
				continue;
			} else {
				B[p] = parent.child[h];
				// System.out.println("this is firedparentschild"+B[p].idn);

				p++;
			}
		}

		parent.child = B;
		parent.num--;

		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");

	}

	public int boss(int id) throws IllegalIDException {

		// your implementation

		if (id == root.idn)
			return -1;

		Node emp = avtree.search(id);
		if (emp == null)
			throw new IllegalIDException("id not found");

		Node bossemp = emp.parent;

		return bossemp.idn;

		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

	public int lowestCommonBoss(int id1, int id2) throws IllegalIDException {

		// your implementation

		if (id1 == root.idn || id2 == root.idn)
			return -1;

		Node n1, n2;

		n1 = avtree.search(id1);
		if (n1 == null)
			throw new IllegalIDException("id not found");

		n2 = avtree.search(id2);
		if (n2 == null)
			throw new IllegalIDException("id not found");

		int l1, l2, currentlevel;

		l1 = this.level(id1);

		l2 = this.level(id2);

		if (l1 >= l2) {

			currentlevel = l1;

			while (!n1.parent.equals(root)) {

				if (currentlevel > l2) {

					if (n1.parent.equals(n2))
						return n2.parent.idn;

					n1.parent = n1.parent.parent;

					currentlevel--;

				}

				else if (currentlevel <= l2) {
					if (n1.parent.equals(n2.parent))
						return n1.parent.idn;

					n1.parent = n1.parent.parent;
					n2.parent = n2.parent.parent;
				}
			}

		}

		if (l1 < l2) {

			currentlevel = l2;

			while (!n2.parent.equals(root)) {

				if (currentlevel > l1) {

					if (n2.parent.equals(n1))
						return n1.parent.idn;
					n2.parent = n2.parent.parent;

					currentlevel--;

				}

				else if (currentlevel <= l1) {
					if (n2.parent.equals(n1.parent))
						return n2.parent.idn;

					n2.parent = n2.parent.parent;
					n1.parent = n1.parent.parent;
				}
			}

			if (n2.parent.equals(root))
				return root.idn;

		}

		return root.idn;

		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");

	}

	void swap(int a, int b) {

		int tmp;
		tmp = a;
		a = b;
		b = tmp;

	}

	void sortarr(int[] intArray) {

		int temp;

		for (int i = 0; i < intArray.length; i++) {

			for (int j = i + 1; j < intArray.length; j++) {

				if (intArray[i] > intArray[j]) { // swap elements if not in order

					temp = intArray[i];

					intArray[i] = intArray[j];

					intArray[j] = temp;

				}

			}

		}

	}

	public String toString(int id) throws IllegalIDException {

		// your implementation

		Node root = avtree.search(id);

		if (root == null)
		
		throw new IllegalIDException("id not found");

		String str = "";

		int flag = 0;

		queue Q = new queue(this.size());

		Node X = root;

		int levelid;

		int counter = this.level(root.idn);

		Q.entqueue(root);

		int[] ids = new int[this.size()];

		int j = 0;

		while (!Q.isqempty()) {

			try {

				X = Q.dequeue();

				// System.out.println(X.idn);

			} catch (emptyqexception e) {
				;
			}

			levelid = this.level(X.idn);

			if (counter < levelid) {

				counter++;

				int[] newarr = new int[j];
				for (int i = 0; i < newarr.length; i++) {

					newarr[i] = ids[i];

				}

				this.sortarr(newarr);

				for (int i = 0; i < newarr.length; i++) {

					if (flag == 0)
						str = str + Integer.toString(newarr[i]);

					else
						str = str + " " + Integer.toString(newarr[i]);

					flag = 1;

				}

				j = 0;
			}

			if (counter == levelid) {

				ids[j] = X.idn;

				j++;
			}

			for (int i = 0; i < X.child.length; i++) {

				if (X.child[i] != null) {

					Q.entqueue(X.child[i]);
				}
			}
		}

		int[] newarr = new int[j];
		for (int i = 0; i < newarr.length; i++) {

			newarr[i] = ids[i];

		}

		this.sortarr(newarr);

		for (int i = 0; i < newarr.length; i++) {
			
			if (flag == 0)
						str = str + Integer.toString(newarr[i]);

			else str = str + " " + Integer.toString(newarr[i]);

		}

		return str;

		// throw new java.lang.UnsupportedOperationException("Not implemented yet.");

	}

	}
