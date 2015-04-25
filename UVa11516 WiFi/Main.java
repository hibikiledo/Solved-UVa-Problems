import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int numCases, n, m;
	static String[] temp;
	static int[] houseNo = new int[100000 + 10];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {		
		
		int lo, hi, mid;
		
		numCases = Integer.parseInt( br.readLine() );
		
		// Fill all array
		Arrays.fill( houseNo, Integer.MAX_VALUE);

		while( numCases-- > 0 ) {
			
			lo = 0; hi = 1000000;

			temp = br.readLine().split(" ");
			n = Integer.parseInt( temp[0] );
			m = Integer.parseInt( temp[1] );			

			// Get house numbers
			for(int i=0; i<m; i++) {
				houseNo[i] = Integer.parseInt( br.readLine() );
			}
			
			// Sort them
			Arrays.sort( houseNo, 0, m );

			// Check of exceed # of router
			if( n >= m ) {
				System.out.printf("0.0\n");
				continue;
			}
			
			// Do the binary search
			while( hi - lo > 1) {
				mid = (hi + lo)/2;
				if( isOptimal( mid * 2) ) hi = mid;
				else lo = mid;
			}
			
			lo *= 10;
			hi *= 10;
			
			for(int i=0; i<m; i++)
				houseNo[i] *= 10;			
			
			// find in more detail
			while( hi - lo > 1) {
				mid = (hi + lo)/2;
				if( isOptimal( mid * 2) ) hi = mid;
				else lo = mid;
			}
			
			// Reset Array back
			Arrays.fill( houseNo, 0, m, Integer.MAX_VALUE);
			
			// print result
			System.out.printf("%d.%d\n", hi/10, hi%10);
		}
	}
	
	static boolean isOptimal(int maxRange) {
		int routerUsed = 1;
		
		// calculate maximum WiFi range from the first house
		int wifiEndpoint = houseNo[0] + maxRange;
		
		// count how many WiFi router is required
		for(int i=0; i<m; i++) {
			if( houseNo[i] > wifiEndpoint ) {
				wifiEndpoint = houseNo[i] + maxRange;
				routerUsed++;
			}
		}		
		
		return routerUsed <= n;
	}
	
}
