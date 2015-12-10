import java.util.HashMap;
import java.util.Arrays;

public class WordFrequencies {

	public static void main( String[] args ) throws Exception {

		if ( args.length == 0 ) {
			//run normally
			normalOp();
		} else if ( args[0].equals("-c") ) {
			//run clean.
			cleanOp();
		} else if ( args[0].equals("-s") ) {
			//run with sensitivity
			sensitiveOp();
		} else if ( args[0].equals("-sc") || args[0].equals("-cs") ) {
			//run with sensitivity and return clean.
			sensitiveCleanOp();
		} else {
			throw new IllegalArgumentException();
		}
		
	}

	public static void normalOp() throws Exception {
		//runs the program normally: capitalizing all words, and returning with the count

		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		java.io.BufferedReader stdIn = new java.io.BufferedReader ( new java.io.InputStreamReader ( System.in ) );
		String s = stdIn.readLine();

		while ( s!= null ) {
			//String[] words = s.split("\\W+");
			String[] words = s.split("[^a-zA-Z0-9']+");
			for (String str : words ){
				str = str.toUpperCase();
				if ( hashmap.containsKey(str) ) {
					int count = hashmap.get(str);
					count++;
					hashmap.put(str, count);
				} else {
					//not in the hashmap, add it.
					hashmap.put(str , 1);
				}
			}
			s = stdIn.readLine(); // grab the next line (or null) cntr-d to quit
		}

		//now theyre in the hashmap, need to: remove, alphabatize, reformat.
		String[] allWords = hashmap.keySet().toArray(new String[hashmap.keySet().size()]);
		Arrays.sort(allWords);
		for ( Object str : allWords ) {
			System.out.println(str + " - " + hashmap.get(str));
		}
	}

	public static void cleanOp() throws Exception {
		//runs the program clean: capitalizing all words, and returning without the count

		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		java.io.BufferedReader stdIn = new java.io.BufferedReader ( new java.io.InputStreamReader ( System.in ) );
		String s = stdIn.readLine();

		while ( s!= null ) {
			String[] words = s.split("[^a-zA-Z0-9']+");
			for (String str : words ){
				str = str.toUpperCase();
				if ( hashmap.containsKey(str) ) {
					int count = hashmap.get(str);
					count++;
					hashmap.put(str, count);
				} else {
					hashmap.put(str , 1);
				}
			}
			s = stdIn.readLine();
		}

		String[] allWords = hashmap.keySet().toArray(new String[hashmap.keySet().size()]);
		Arrays.sort(allWords);
		for ( Object str : allWords ) {
			System.out.println(str);
		}
	}

	public static void sensitiveOp() throws Exception{
		//runs the program sensitive: leaving all words, and returning with the count

		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		java.io.BufferedReader stdIn = new java.io.BufferedReader ( new java.io.InputStreamReader ( System.in ) );
		String s = stdIn.readLine();

		while ( s!= null ) {
			//String[] words = s.split("\\W+");
			String[] words = s.split("[^a-zA-Z0-9']+");
			for (String str : words ){
				if ( hashmap.containsKey(str) ) {
					int count = hashmap.get(str);
					count++;
					hashmap.put(str, count);
				} else {
					//not in the hashmap, add it.
					hashmap.put(str , 1);
				}
			}
			s = stdIn.readLine(); // grab the next line (or null) cntr-d to quit
		}

		//now theyre in the hashmap, need to: remove, alphabatize, reformat.
		String[] allWords = hashmap.keySet().toArray(new String[hashmap.keySet().size()]);
		Arrays.sort(allWords);
		for ( Object str : allWords ) {
			System.out.println(str + " - " + hashmap.get(str));
		}
	}

	public static void sensitiveCleanOp() throws Exception {
		//runs the program sensitive and clean: leaving all words, and returning w/o the count

		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		java.io.BufferedReader stdIn = new java.io.BufferedReader ( new java.io.InputStreamReader ( System.in ) );
		String s = stdIn.readLine();

		while ( s!= null ) {
			//String[] words = s.split("\\W+");
			String[] words = s.split("[^a-zA-Z0-9']+");
			for (String str : words ){
				if ( hashmap.containsKey(str) ) {
					int count = hashmap.get(str);
					count++;
					hashmap.put(str, count);
				} else {
					//not in the hashmap, add it.
					hashmap.put(str , 1);
				}
			}
			s = stdIn.readLine(); // grab the next line (or null) cntr-d to quit
		}

		//now theyre in the hashmap, need to: remove, alphabatize, reformat.
		String[] allWords = hashmap.keySet().toArray(new String[hashmap.keySet().size()]);
		Arrays.sort(allWords);
		for ( Object str : allWords ) {
			System.out.println(str);
		}
	}

}

//more infor here: http://examples.javacodegeeks.com/core-java/util/hashmap/java-hashmap-example/
