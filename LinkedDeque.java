public class LinkedDeque {
	//private variables:
	private int size;
	private Node left;
	private Node right;
	private Node base;
	
	public LinkedDeque() {
		//default constructor
		this.left = null;
		this.right = null;
		this.size = 0;

	}

	public void insertLeft( Object o ) {
		//insert the object into the "left" side of the deque (first)
		//change to anonymous entry in intial state (this.left = new node)
		if ( this.size == 0 ) {
			this.left = new Node(null, o, null);
			this.right = this.left;
		} else {
			this.left.setLeft( new Node(null, o, this.left) );	
			this.left = this.left.getLeft();
		}	
		this.size++;
	}

	public void insertRight( Object o ) {
		//insert the object into the "right" side of the deque (last)
		if ( this.size == 0 ) {
			this.right = new Node(null, o, null);
			this.left = this.right;
		} else {
			this.right.setRight( new Node(right, o, null) );
			this.right = this.right.getRight();
		}
		this.size++;
	}

	public void deleteLeft() {
		//deletes the left (first) in the deque
		if ( this.size < 1 ) {
			throw new UnsupportedOperationException();
		} 
		if ( this.size == 1 ) {
			//special case
			this.left = null;
			this.right = null;
			this.size = 0;
		} else {
			this.left = this.left.getRight();
			this.left.setLeft( null );
			this.size--;
		}
		
	}

	public void deleteRight() {
		//deletes the right (last) in the deque
		if ( this.size < 1 ) {
			throw new UnsupportedOperationException();
		}
		if ( this.size == 1 ) {
			this.right = null;
			this.left = null;
			this.size = 0;
		} else {
			this.right = this.right.getLeft();
			this.right.setRight( null );
			this.size--;
		}

	}

	public Object left() {
		//returns the left (first) element without motifying the deque
		return this.left.getO();
	}

	public Object right() {
		//returns the right (last) element without motifying the deque
		return this.right.getO();
	}

	public int size() {
		//obvious
		return this.size;
	}

	public String toString() {
		//returns [obj][obj][obj]...[obj]
		String output = "";
		Node horse = this.left;
		while ( horse != null ) {
			output = output + "[" + horse.getO() + "]";
			horse = horse.getRight();
		}
		return output;
	}

	public static void main( String[] args ) {
		//runs a comprehensive set of unit tests on the class
		System.out.println("Testing LinkedDeque");
		
		LinkedDeque a = new LinkedDeque();
		a.insertRight(9.25);
		System.out.println( a.toString().equals("[9.25]") );
		
		LinkedDeque b = new LinkedDeque();
		b.insertLeft(9.25);
		System.out.println(a.toString().equals(b.toString()) );

		a.insertLeft(5);
		System.out.println( a.toString().equals("[5][9.25]") );
		
		a.insertLeft("cat");
		a.insertRight("Horse");
		a.insertRight("banana");
		System.out.println(a.toString().equals("[cat][5][9.25][Horse][banana]"));
		System.out.println(a.size() == 5);

		a.deleteRight();
		System.out.println(a.toString().equals("[cat][5][9.25][Horse]") && a.size() == 4);
		a.deleteLeft();
		System.out.println(a.toString().equals("[5][9.25][Horse]") && a.size() == 3 && a.left() == 5 && a.right().equals("Horse") );

		a.deleteRight();
		a.deleteLeft();
		a.deleteRight();
		System.out.println(a.toString().equals("") && a.size() == 0);
	}

	public class Node {
	//each node has three pointers: One to the left; one to the right; one to the object stored in it
		private Node left;
		private Node right;
		private Object o;

		public Node( Node left, Object o, Node right ) {
			this.left = left;
			this.o = o;
			this.right = right;
		}

		public Node getLeft() {
			return this.left;
		}

		public Node getRight() {
			return this.right;
		}

		public Object getO() {
			return this.o;
		}

		public void setLeft( Node left ) {
			this.left = left;
		}

		public void setRight( Node right ) {
			this.right = right;
		}

		public void setO( Object o ) {
			this.o = o;
		}
	}

}

