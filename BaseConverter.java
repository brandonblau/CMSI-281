public class BaseConverter{	

	//Written by Brandon Blau and Alexis Arencibia 

	//submit to Rachel on GitHub 
	
	public static void main ( String[] args ) {
       if ( ! validArgs ( args ) ) {
           throw new IllegalArgumentException();
        }
        else {
          // YOUR CODE GOES HERE
          Long baseN;
			    String str = args[0];
			    str = str.replaceAll("\\[", "").replaceAll("\\]","-");
			    String[] original = str.split("-");
			    Long baseOrig = new Long(args[1]);
		  	  if ( args.length != 3 ) {
				    baseN = new Long(10);
			    } else {
				    baseN = new Long(args[2]);
			    }
		  	  Long number10 = new Long("16");
		  	  System.out.println( baseTenToN( toBaseTen(original,baseOrig), baseN) );
        }
  }

	public static Long toBaseTen( String[] original, Long baseOriginal ) {
		  Long tenValue = new Long(0);
		  int i = 0;
		  while ( i < original.length ) {
			  if ( Long.valueOf(original[i]) < 0 ) {
				  throw new UnsupportedOperationException();			//error: negative numbers [here]
			  }
			  if ( Long.valueOf(original[i]) >= baseOriginal ) {
				  throw new UnsupportedOperationException();			//error: input numbers [here] bigger than base
			  }
			  tenValue = tenValue * baseOriginal + Long.valueOf(original[i]);
			  i++;
		  }
		  return tenValue;
	}

	public static String baseTenToN( Long tenValue, Long baseN ) {
		  String result = "";
		  Long dig = new Long(0);
		  while ( tenValue > 1 ) {
			  dig = tenValue % baseN;
			  result = "["+ dig +"]"+ result;
			  tenValue = tenValue / baseN;
		  }
		  if ( tenValue != 0 ) {
			  result = "[" + tenValue +"]" + result;
		  }
		  return result;
	}

	public static boolean validArgs( String[] args ) {
        // YOUR CODE GOES HERE
        if ( args.length < 2 ) {			//not enough arguments
        	return false;
        }
       	if ( !isNumeric(args[1]) ){		//original base isnt numeric
       		return false;
       	}
       	if ( args.length == 3 && !isNumeric(args[2]) ) {		//second base isnt numeric
       		return false;
       	}
       	Long baseOrig = new Long(args[1]);
       	if (args.length == 3) {
       		Long baseN = new Long(args[2]);
       		if ( baseOrig - 2 < 0 ) {		//first base < 2
       			return false;
       		}
       		if ( baseN - 2 < 0) {			//second base < 2
       			return false;
       		}
       	}
		    String str = args[0];
		    str = str.replaceAll("\\[", "").replaceAll("\\]","-");
		    String[] original = str.split("-");
		    for ( int i = 0; i < original.length; i++ ) {			//non-numeric values in [here]
			    if ( !isNumeric(original[i]) ){
				  return false;
			    }
		    }
        return true;
  }

  public static boolean isNumeric( String str )  {  
  		try  {  
    		double d = Double.parseDouble(str);  
  		}  
  		catch(NumberFormatException nfe)  {  
    		return false;  
  		}  
  		return true;  
	}
	
	
}
