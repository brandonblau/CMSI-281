import java.lang.Iterable;
import java.util.Iterator;
import java.util.Stack;

public class BinaryTree implements Iterable {

	private Node root;
	private Node cursor;

	public static void main( String[] args ) {
		BinaryTree bt = new BinaryTree( "A" );
		bt.attachLeftSonAtCursor("B");
		bt.attachRightSonAtCursor("C");
		bt.putCursorAtLeftSon();
		bt.attachLeftSonAtCursor("D");
		bt.attachRightSonAtCursor("E");
		bt.putCursorAtRoot();
		bt.putCursorAtRightSon();
		bt.attachLeftSonAtCursor("F");
		bt.attachRightSonAtCursor("H");
		bt.putCursorAtLeftSon();
		bt.attachLeftSonAtCursor("G");
		bt.putCursorAtRoot();
		System.out.println(bt.toString());
		System.out.println(bt.size());
		System.out.println("hash: "+bt.hashCode());
		System.out.println("contains: "+bt.contains("A") );

		BinaryTree bt2 = new BinaryTree( "A" );
		bt2.attachLeftSonAtCursor("B");
		bt2.attachRightSonAtCursor("C");
		bt2.putCursorAtLeftSon();
		bt2.attachLeftSonAtCursor("D");
		bt2.attachRightSonAtCursor("E");
		bt2.putCursorAtRoot();
		bt2.putCursorAtRightSon();
		bt2.attachLeftSonAtCursor("F");
		bt2.attachRightSonAtCursor("H");
		bt2.putCursorAtLeftSon();
		bt2.attachLeftSonAtCursor("I");
		bt2.putCursorAtRoot();
		System.out.println("similar: "+bt.similar(bt2));
		System.out.println("equals test: "+ !bt.equals(bt2));

		bt.putCursorAtRoot();
		bt.putCursorAtRightSon();
		bt.pruneFromCursor();
		System.out.println("Prune test(find a restroom): "+bt.toString().equals("[A][B][D][E]"));
	}

	public String toString() {
		//returns a string rep of the tree in PREORDER
		String str = "";
		for ( Object p : this ) {
			str = str +"[" + p.toString() + "]"; 
		}
		return str;
	}

	public BinaryTree() {
		//constructs an empty tree
		this.cursor = this.root;
	}

	public BinaryTree( Object obj ) {
		//constructs a tre with just a root node decorated with obj
		this.root = new Node( obj );
		this.cursor = this.root;
	}

	public boolean contains( Object obj ) {
		//returns true iff the tree contains an object equivalent to obj
		for ( Object p : this ) {
			if ( p.equals(obj) ) {
				return true;
			}
		}
		return false;
	}

	public boolean similar( Object obj ) {
		//returns true iff obj is a similar binary tree (obj must have identical structure only)
		this.putCursorAtRoot();
		((BinaryTree)obj).putCursorAtRoot();
		if ( this.size() != ((BinaryTree)obj).size() ) {
			return false;
		}
		Node p = this.cursor;
		Node q = ((BinaryTree)obj).cursor;
		Stack<Node> pstack = new Stack<Node>();
		Stack<Node> qstack = new Stack<Node>();
		for (int i = 1; i < this.size(); i++) {
			if ( p.getLSON() != null && p.getRSON() != null ) {
				if ( q.getLSON() == null || q.getRSON() == null ) {
					return false;
				}
				pstack.push(p.getRSON());
				qstack.push(q.getRSON());
				p = p.getLSON();
				q = q.getLSON();
			} else if (p.getLSON() == null && p.getRSON() != null ) {
				if (q.getLSON() != null || q.getRSON() == null) {
					return false;
				}
				p = p.getRSON();
				q = q.getRSON();
			} else if (p.getLSON() != null && p.getRSON() == null ){
				if ( q.getLSON() == null || q.getRSON() != null ) {
					return false;
				}
				p = p.getLSON();
				q = q.getLSON();
			} else {
				//terminal node with more in the size to get to
				if ( q.getLSON() != null || q.getRSON() != null ){
					return false;
				}
				p = (Node)pstack.pop();
				q = (Node)qstack.pop();
			}
		}
		return true;
	}

	public boolean equals( Object obj ) {
		//returns true iff obj is an equivalent binary tree (identical size and objects)
		if ( !this.similar(obj) ) {
			return false;
		} 
		Iterator iP = this.iterator();
		Iterator iQ = ((BinaryTree)obj).iterator();
		while ( iP.hasNext() ) {
			if ( !( iP.next().equals(iQ.next()) ) ){
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return (this.size() == 0);
	}

	public int size() {
		int i = 0;
		for (Object p : this ) {
			i++;
		}
		return i;
	}

	public int hashCode() {
		int hash = 1;
		for ( Object p : this ) {
				hash = (hash+151) * p.hashCode() + 11 - 811;
		}
		return hash;
	}

	public Iterator iterator() {
		//should return a PREORDER iterator over the tree
		return new BTiterator(this.root);
	}

	public Iterator inOrder() {
		//should return an INORDER iterator over the tree
		return new BTinOrder(this.root);
	}

	public Node cursor() {
		return this.cursor;
	}

	public boolean putCursorAtRoot() {
		this.cursor = this.root;
		return !(this.size() == 0);
	}

	public boolean putCursorAtLeftSon() {
		if ( this.cursor.getLSON() != null ) {
			this.cursor = this.cursor.getLSON();
			return true;
		} else {
			return false;
		}
	}

	public boolean putCursorAtRightSon() {
		if ( this.cursor.getRSON() != null ) {
			this.cursor = this.cursor.getRSON();
			return true;
		} else {
			return false;
		}
	}

	public boolean putCursorAtFather() {
		if ( this.cursor.getFather() != null ) {
			this.cursor = this.cursor.getFather();
			return true;
		} else {
			return false;
		}
	}

	public boolean attachLeftSonAtCursor(Object obj) {
		if (cursor.getLSON() != null) {
			//return false if there is already a lson
			return false;
		}
		cursor.setLSON(new Node(obj) );
		cursor.getLSON().setFather(cursor);
		return true;
	}

	public boolean attachRightSonAtCursor(Object obj) {
		if (cursor.getRSON() != null) {
			//return false if there is already a rson
			return false;
		}
		cursor.setRSON(new Node(obj) );
		cursor.getRSON().setFather(cursor);
		return true;
	}	

	public boolean pruneFromCursor() {
		if (this.cursor == this.cursor.getFather().getLSON() ) {
			this.cursor.getFather().setLSON( null );
			this.cursor = this.root;
			return true;  
		} else if ( this.cursor == this.cursor.getFather().getRSON() ) {
			this.cursor.getFather().setRSON( null );
			this.cursor = this.root;
			return true; 
		}else {
			//only reached in error:
			return false;
		}

	}

	private class BTiterator implements Iterator {
		private Node p;
		private Stack<Node> s;
		private Node q;

		public BTiterator(Node root) {
			//constructor.  PREORDER, so root is p first.
			this.p = root;
			s = new Stack<Node>();
		}

		public Object next() {
			//returns the next item in pre-order (already at p) and updates p to point to the next next item;
			q = p;
			if ( p.getLSON() != null && p.getRSON() != null ) {
				s.push( p.getRSON() );
				p = p.getLSON();
			} else if ( p.getLSON() == null && p.getRSON() != null ) {
				p = p.getRSON();
			} else if ( p.getLSON() != null && p.getRSON() == null ) {
				p = p.getLSON();
			} else {
				//p is the terminal node.
				if ( s.size() >= 1 ) {

					p = s.pop();
				} else {
					p = null;
				}
			}
			return q.getData();
		}

		public boolean hasNext() {
			return !( p == null );
		}

		public void remove() {
			//remove the selected element 
			throw new UnsupportedOperationException();
		}

	}

	private class BTinOrder implements Iterator {
		private Node p;
		private Stack<Node> s;
		private Node q;

		public BTinOrder(Node root) {
			//constructor.  INORDER, so push root and find the first element.
			s = new Stack<Node>();
			this.p = root;
			q = null;
		}

		public Object next() {
			//returns the next item in INORDER and updates p to point to the next next item;
			q = null;
			while ( (!s.empty() || p!= null) && (q == null) ) {
				
				if ( p!=null ) {
					s.push(p);
					this.p=p.getLSON();
				} else {
					q=s.pop();
					this.p=q.getRSON();
				}
			}
			return q.getData();
		}

		public boolean hasNext() {
			return !( p == null && s.isEmpty() );
		}

		public void remove() {
			//remove the selected element 
			throw new UnsupportedOperationException();
		}

	}

	private class Node {
		//nodes for use in the binary tree 
		private Object data;
		private Node father;
		private Node lson;
		private Node rson;

		public Node( Object obj ) {
			this.data = obj;
			this.father = null;
		}

		public Node() {

		}

		public Object getData() {
			return this.data;
		}

		public void setData( Object obj ) {
			this.data = obj;
		}

		public Node getRSON() {
			return this.rson;
		}

		public void setRSON( Node son ) {
			this.rson = son;
		}

		public Node getLSON() {
			return this.lson;
		}

		public void setLSON( Node son ) {
			this.lson = son;
		}

		public Node getFather() {
			return this.father;
		}

		public void setFather( Node dad ) {
			this.father = dad;
		}
	}
}
