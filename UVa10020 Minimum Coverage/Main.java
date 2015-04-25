import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {
	
	static int M, L, R;
	static ArrayList<Pair> segments = new ArrayList<Pair>();
	static ArrayList<Pair> usedSegments = new ArrayList<Pair>();

	public static void main(String[] args) throws IOException {
		
		// Variables work only inside main
		int numCases;
		String[] temp;		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// get number of test cases
		numCases = Integer.parseInt( in.readLine().trim() );
		
		while( numCases>0 ) {
			
			// discard empty line
			in.readLine();			
			
			M = Integer.parseInt( in.readLine().trim() );
			
			// get segments
			while( true ) {
				temp = in.readLine().split("\\s");
				
				L = Integer.parseInt( temp[0].trim() );
				R = Integer.parseInt( temp[1].trim() );
				
				if( L == 0 && R == 0 ) {
					numCases--;
					break;
				}
				
				// accept only useful segments
				if( !(R <= 0 || L >= M) ) {
					segments.add( new Pair(L, R) );
				}
			}
			
			// process current test case
			// 1: sort them by L
			Collections.sort(segments);
			// 2: keep processing until R(last segment) >= M
			int curL = -1, curR = -1;
			int index;
			
			while( curR < M ) {
				
				// handle start segment
				if( usedSegments.size() == 0 ) {
					index = findOptimalStartLine();					
				} else { // later segment
					index = findOptimalLine( curL, curR );					
				}
				
				if( index == -1 ) break;
				
				curL = segments.get( index ).L; // keep value L
				curR = segments.get( index ).R; // keep value R
				usedSegments.add( segments.get(index) ); // keep segment as used
				segments.remove( index ); // remove from bucket				
			}
			
			
			// Show result
			System.out.println( usedSegments.size() );
			for( Pair segment : usedSegments ) {
				System.out.printf("%d %d\n", segment.L, segment.R );
			}
			if( numCases > 0 ) System.out.println();
			
			// clear static stuffs
			segments.clear();
			usedSegments.clear();	
		
		} // end while

	}
	
	static int findOptimalLine(int conL, int conR) {
		int index = -1;
		int maxR = 0;
		
		for(int i=0; i<segments.size(); i++) {
			if( segments.get(i).L > conL && segments.get(i).L <= conR ) {
				if( maxR < segments.get(i).R ) {
					maxR = segments.get(i).R;
					index = i;
				}
			}
		}
		return index;
	}
	
	static int findOptimalStartLine() {
		int index = -1;
		int maxR = 0;
		
		// find longest line that meets conditions
		for(int i=0; i<segments.size(); i++) {
			if( segments.get(i).L <= 0 && segments.get(i).R > 0 ) {
				if( maxR < segments.get(i).R ) {
					maxR = segments.get(i).R;
					index = i;
				}
			}
		}
		return index;
	}
}


class Pair implements Comparable<Pair> {
	
	public final int L,R;
	
	public Pair(int L, int R) {
		this.L = L;
		this.R = R;
	}
	
	@Override
	public int compareTo(Pair o) {
		if( o == null ) return 0;
		if( this.L < o.L ) {
			return -1;
		} else if ( this.L > o.L ) {
			return 1;
		} else {
			return 0;
		}
	}	
}