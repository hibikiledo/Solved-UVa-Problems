import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class Main {

	public static void main(String[] args) throws IOException {
		
		HashMap<Character,Integer> map = new HashMap<Character, Integer>();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		Integer value;
		
		while( (line=in.readLine()) != null ) {
			
			map.clear();
			initMap( map );
			
			// build frequency map
			for(int i=0; i<line.length(); i++) {
				if( isAlphabet(line.charAt(i)) ) {
					
					value = map.get(line.charAt(i));
					map.put(line.charAt(i), value+1);					
					
				}
			}
			
			// get all element into array
			ArrayList<KV> freqList = new ArrayList<KV>();
			
			Set<Character> set = map.keySet();
			Iterator<Character> i = set.iterator();
			
			while( i.hasNext() ) {
				Character c = i.next();
				freqList.add( new KV(c, map.get(c)));
			}
			
			// sort array
			Collections.sort( freqList );
			
			int max = freqList.get(0).count;
			for( KV kv : freqList ) {
				if( kv.count != max ) {
					break;
				}
				System.out.print( kv.ch );
			}
			System.out.println(" " + max);
			
		}

	}
	
	static void initMap(HashMap<Character,Integer> map) {
		
		for(char c='a'; c<='z'; c++) 
			map.put(c, 0);		
		
		for(char c='A'; c<='Z'; c++) 
			map.put(c, 0);
		
	}
	
	static boolean isAlphabet(Character c) {
		return (c>='A'&&c<='Z') || (c>='a'&& c<='z');
	}

}

class KV implements Comparable<KV> {
	
	@Override
	public int compareTo(KV other) {
		if( this.count < other.count ) {
			return 1;
		} else if( this.count > other.count ) {
			return -1;
		} else { // equal count
			
			if( this.ch < other.ch ) {
				return -1;
			} else if( this.ch > other.ch ) {
				return 1;
			} 
			
			return 0;
		}
	}

	Character ch;
	int count;
	
	public KV( Character ch, int count) {
		this.ch = ch;
		this.count = count;
	}

	@Override
	public String toString() {
		return "KV [ch=" + ch + ", count=" + count + "]";
	}	
}