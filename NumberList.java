/**     

    &lt;b&gt;Note: Corrections have been made to the return types for both toArray() methods. (2015-10-13).&lt;/b&gt;

    An object of this class represents a number list, i.e., an ordered collection
    of integers, each of Java class &lt;a href="http://docs.oracle.com/javase/7/docs/api/java/lang/Long.html"&gt;Long&lt;/a&gt;, 
    with duplicates permitted. Be sure to read the Java documentation on
    &lt;a href="http://docs.oracle.com/javase/7/docs/api/java/util/Collection.html"&gt;interface java.util.Collection&lt;/a&gt;.

**/

public class NumberList implements java.util.Collection {

    private Long[] data;
    private int count;

    /** Constructs an empty number list. */
    public NumberList(){
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        this.data = new Long[10];
        this.count = 0;
    }


    /** Constructs a number list from an array of Longs. */
    public NumberList( Long[] l ){
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        this.data = new Long[ l.length ];
        for (int i = 0; i < l.length; i++) {
                this.data[i] = l[i];
                this.count++;
        }
    }



    /** Doubles the size of the data array **/
    public void doubleSpace() {
        Long[] bigarray = new Long[ 2 * data.length ];
        for ( int i = 0; i < data.length; i++) {
            bigarray[i] = this.data[i];
        }
        this.data = bigarray;
    }
    


    /** Increases by one the number of instances of the given element in this collection. */
    public boolean add ( Object obj ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */

        if ( this.data.length == this.count ) {
            this.doubleSpace();
        }
        this.data[ this.count ] = (Long) obj;
        count++;
        
        return true;
    }
   


    /** Adds all of the elements of the given number list to this one. */
    public boolean addAll ( java.util.Collection c ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        NumberList addList = new NumberList( ((NumberList)c).data );
        if ( c instanceof NumberList ) { 
            for ( int i = 0; i < addList.count; i++ ) {
                if ( addList.data[i] != null ) {
                    this.add( addList.data[i] );
                }
            }
            return true;
        }
        return false;
    }
 

    /** Removes all of the elements from this collection. */
    public void clear () {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        for ( int i = 0; i < this.count; i++ ) {
            this.data[i] = null;
        }
        this.count = 0;
    }
 


    /** Returns true iff this number list contains at least one instance of the specified element. */
    public boolean contains ( Object obj ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        for ( int i = 0; i < this.count; i++ ) {
            if ( this.data[i] - ((Long)obj) == 0) {
                return true;
            }
        }
        return false;
    }
 

    /** Returns true iff this number list contains at least one instance of each element 
        in the specified list. Multiple copies of some element in the argument do not
        require multiple copies in this number list. */
    public boolean containsAll ( java.util.Collection c ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
       if (c instanceof NumberList) {
            for (int i = 0; i < ((NumberList)c).count; i++ ) {
                if ( !this.contains( ((NumberList)c).data[i] ) ) {
                    return false;
                }
            }
       }
       return true;
    }
 
 


    /** Compares the specified object with this collection for equality. */
    public boolean equals ( Object obj ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        if ( obj instanceof NumberList ){
            if ( !(this.count - ((NumberList)obj).count == 0) ) {
                return false;
            }
            if ( this.count == 0 && this.data[0] == null && ((NumberList)obj).data[0] == null && ((NumberList)obj).count == 0 ) {
                return true;
            }
            for ( int i = 0; i < this.count; i++ ) {
                if ( !(this.data[i] - ((NumberList)obj).data[i] == 0) ) {
                    //different numbers in the list
                    return false;
                }
            }
            return true;
        }
        return false;
    }
 


    /** Unsupported for this class */
    public int hashCode () {
        throw new UnsupportedOperationException();
    }



    /** Returns true if this collection contains no elements. */
    public boolean isEmpty () {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        return (this.count == 0);
    }



    /** Unsupported for this class */
    public java.util.Iterator iterator () {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        throw new UnsupportedOperationException();
    }



    /** Removes a single instance of the specified element from 
        this collection, if it is present. */
    public boolean remove ( Object obj ) {
        
        if ( this.contains((Long) obj) ) {
            int index = -1;
            for (int i = 0; i < this.count; i++) {
                //find the element
                if ( this.data[i] - (Long)obj == 0) {
                    //element has been found
                    index = i;   
                }
            }
            
            if (index > -1) {
                for (int j = index; j < this.count-1; j++) {
                    //shift numbers to remove element
                    this.data[j] = this.data[j+1];
                }
                this.data[count-1] = null;
                count += -1;  
                return true;
            }  
        }
        return false;
    }



    /** Removes all of this collection's elements that are also contained 
        in the specified collection. */
    public boolean removeAll ( java.util.Collection c ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        if ( c instanceof NumberList ) {
            for ( int i = 0; i < ((NumberList)c).count; i++ ) {
                while(this.contains( ((NumberList)c).data[i] )) {
                    this.remove( ((NumberList)c).data[i] );
                }
                
            }
            return true;
        }
        return false;
    }



	/** Retains only the elements in this collection that are contained in the specified collection. 
		 In other words, removes from this collection all of its elements that are not contained in the 
		 specified collection. */
	public boolean retainAll ( java.util.Collection c ) {
        if ( c instanceof NumberList ){
            for ( int i = 0; i < this.count; i++ ) {
                if ( !((NumberList)c).contains(this.data[i]) ) {
                    this.remove( this.data[i] );
                    i += -1;
                }
            }
            return true;
        }
		return false;
	}


    /** Returns the number of elements in this number list, including duplicates. */
    public int sizeIncludingDuplicates () {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        return this.count;
    }
    
    

    /** Returns a Long[] containing all of the elements in this collection, not including duplicates. */
    public Long[] toArray () {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        NumberList filler = new NumberList();
        for ( int i = 0; i < this.count; i++ ) {
            if ( ! filler.contains(this.data[i]) ) {
                filler.add( this.data[i] );
            }
        }
        Long[] fillerNums = new Long[filler.count];
        for ( int i = 0; i < filler.count; i++ ) {
            fillerNums[i] = filler.data[i];
        }
        return fillerNums;
    }



    /** Not supported for this class. */
    public Object[] toArray ( Object[] obj ) {
        throw new UnsupportedOperationException();
    }



    /** Returns the number of elements in this number list, not including duplicates. */
    public int size () {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        return this.toArray().length;
    }


    /** Returns the number of instances of the given element in this number list. */
    public int count ( Object obj ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        int number = 0;
        for (int i = 0; i < this.count; i++) {
            if ( this.data[i] - (Long)obj == 0 ){
                number++;
            }
        }
        return number;
    }
    

    
    /** This returns a stringy version of this number list. */
    public String toString () { // overrides Object.toString()
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        String s = "";
        for ( int i = 0; i < this.count; i++ ) {
            if ( this.data[i] != null ) {
                s = s + "["+this.data[i] + "]";    
            }
        } 
        return s;
    }


    
    /** This so-called "static factory" returns a new number list comprised of the numbers in the specified array.
        Note that the given array is long[], not Long[]. */
    public static NumberList fromArray ( long[] l ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */
        Long[] l2 = new Long[ l.length ];
        for ( int i = 0; i < l.length; i++ ) {
            l2[i] = (Long) l[i];
        }
        return new NumberList(l2);
    }

    
    /** This main method is just a comprehensive test program for the class. */
    public static void main ( String[] args ) {
        /* REPLACE THE NEXT STATEMENT WITH YOUR CODE */

        

        //Testing constructors:         (check)
        System.out.println("Testing Constructors...");
        System.out.println( (new NumberList()).count == 0 );
        Long[] test1RA = new Long[10];
        test1RA[0] = new Long("4");
        test1RA[1] = new Long("4");
        test1RA[2] = new Long("2");
        test1RA[3] = new Long("8");
        test1RA[4] = new Long("00");
        test1RA[5] = new Long("-7");
        test1RA[6] = new Long("60000000");
        test1RA[7] = new Long("23232323232");
        test1RA[8] = new Long("725");
        test1RA[9] = new Long("6");
        NumberList test1 = new NumberList(test1RA);
        int a = 0;
        for ( int i = 0; i < test1RA.length; i++ ) {
            if ( test1.contains(test1RA[i]) ) {
                a++;
            }
        }
        if ( a == test1RA.length ) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

        //Testing double space:
        System.out.println("Testing custom doubleSpace()...");
        NumberList testDouble = new NumberList();
        testDouble.doubleSpace();
        testDouble.doubleSpace();
        System.out.println( testDouble.data.length == 40 );
        
        Long[] doubleRA = new Long[3];
        doubleRA[0] = new Long("2");
        doubleRA[1] = new Long("4");
        doubleRA[2] = new Long("5");
        NumberList testDouble2 = new NumberList(doubleRA);
        testDouble2.doubleSpace();
        System.out.println( testDouble2.data.length == 6 && testDouble2.count == 3);

        //Testing add:
        System.out.println("Testing equals..");
        testDouble.add( new Long("2") );
        System.out.println(! testDouble.equals(testDouble2) );
        testDouble.add( new Long("4") );
        testDouble.add( new Long("5") );
        System.out.println( testDouble.equals(testDouble2) );
        testDouble2.add( new Long("11"));
        System.out.println( testDouble2.data.length == 6);

        //testing addAll():
        System.out.println("Testing addAll()...");
        testDouble.addAll(testDouble);
        testDouble2.remove(new Long("11"));
        testDouble2.add(new Long("2"));
        testDouble2.add(new Long("4"));
        testDouble2.add(new Long("5"));
        System.out.println( testDouble2.equals(testDouble) );

        //testing clear
        System.out.println("Testing Clear...");
        NumberList clearTest = new NumberList(doubleRA);
        clearTest.clear();
        System.out.println(clearTest.count == 0);

        //testing contains
        System.out.println("Testing contains...");
        System.out.println( testDouble.contains(new Long("4")) );

        //Testing contains all
        System.out.println("Testing containsAll");
        System.out.println( testDouble2.containsAll(testDouble) );

        //Testing isEmpty
        System.out.println("Testing isEmpty..");
        System.out.println(clearTest.isEmpty());

        //Test remove
        System.out.println("testing remove...");
        NumberList testRemove1 = new NumberList(doubleRA);
        NumberList testRemove2 = new NumberList(doubleRA);
        testRemove2.add( new Long("6") );
        testRemove2.remove( new Long("6") );
        System.out.println( testRemove1.equals(testRemove2) );

        //Test remove all
        System.out.println("testing removeall..");
        testRemove2.add( new Long("6") );
        testRemove2.removeAll(testRemove1);
        System.out.println(testRemove2.count == 1);

        Long[] ball = new Long[4];
        ball[0] = new Long("4");
        ball[1] = new Long("4");
        ball[2] = new Long("2");
        ball[3] = new Long("8");

        //Test retain all
        System.out.println("Testing retainall");
        
        NumberList retAllSol = new NumberList(ball);
        retAllSol.remove(new Long("8"));

        NumberList retball = new NumberList(ball);
        NumberList retdouble = new NumberList(doubleRA);

        retball.retainAll(retdouble);

        System.out.println( retball.equals( retAllSol) );

        //test fromArray
        System.out.println("testing fromarray...");
        long[] l = new long[4];
        l[0] = (long)(new Long("4"));
        l[1] = (long)(new Long("4"));
        l[2] = (long)(new Long("2"));
        l[3] = (long)(new Long("8"));
        NumberList fromArrayl = retball.fromArray(l);
        System.out.println( fromArrayl.equals(new NumberList(ball))  );

        //test size includ dupes:
        System.out.println("testing sid...");
        System.out.println( fromArrayl.sizeIncludingDuplicates() == 4);
        
        //test size not including dupes:
        System.out.println("testing snid...");
        System.out.println( fromArrayl.size() == 3);

        //test count:
        System.out.println("testing count...");
        System.out.println( fromArrayl.count( new Long("4") ) == 2);

        //test to array
        System.out.println("testing toArray...");
        NumberList arrayTest1 = new NumberList(ball);
        arrayTest1.remove(new Long("4"));
        System.out.println( (new NumberList(fromArrayl.toArray()) ).equals(arrayTest1) );
    }
    
}
