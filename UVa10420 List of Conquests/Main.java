import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Main {
	
	static int lines;

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		String[] lineSplit;
		Integer value;
		
		lines = Integer.parseInt( in.readLine() );
		
		// read all lines
		while( lines-- > 0 ) {
			
			lineSplit = in.readLine().split(" ");
			
			if( (value=map.get(lineSplit[0])) == null ) { // first time
				// create arrayList
				value = 0;				
			}			
			
			// update inside map
			map.put( lineSplit[0], value+1);
		}
		
		// process
		ArrayList<Struct> answer = new ArrayList<Struct>(); 
		
		Set<String> keys = map.keySet();
		Iterator<String> i = keys.iterator();
		
		String key;
		while( i.hasNext() ) {
			key = i.next();
			answer.add( new Struct(key, map.get(key)) );			
		}
		
		// sort them
		Collections.sort( answer );
		
		// print result
		for( Struct e : answer ) {
			System.out.println(e.country + " " + e.num);
		}

	}

}

class Struct implements Comparable<Struct> {
	String country;
	int num;
	
	public Struct(String c, int n) {
		this.country = c;
		this.num = n;
	}

	@Override
	public int compareTo(Struct o) {
		return o.country.compareTo(this.country) * -1;
	}	
}
