import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;

public class Main {

	StreamTokenizer in;
	PrintWriter out;

	int N, C, K;

	public static void main(String[] args) throws Exception {
		new Main().solve();
	}

	private void solve() throws IOException {

		// init IO things
		in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		
		// init array and hash map
		int[] voteCount;
		Integer phoneNum, cID, count;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		// get input specification		
		N = nextInt();
		C = nextInt();
		K = nextInt();
		
		// init array
		voteCount = new int[C+1];
		
		// process all vote data
		for(int i=0; i<N; i++) {
			
			phoneNum = nextInt();
			cID = nextInt();
			
			// first time
			if( map.get(phoneNum) == null ) { 
				map.put( phoneNum, 1);
				count = 1;
			} else { // later time, increment
				count = map.get( phoneNum );
				count += 1;
				map.put(phoneNum, count);
			}
			
			// check for conditions and increment vote counter
			if( count <= K && cID >= 1 && cID <= C ) {
				voteCount[ cID ]++;			
			}			
		}
		
		// print result
		for(int i=1; i<=C; i++) {
			out.println( voteCount[i] );
		}
		
		// flush
		out.flush();
	}

	private int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;	
	}

}
