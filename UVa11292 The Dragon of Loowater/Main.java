import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int numHeads, numKnights;
	static int[] headArr, knightArr;
	static String[] lineSplit;

	public static void main(String[] args) throws IOException {

		// Scanner in = new Scanner(System.in);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while( true ) {

			lineSplit = in.readLine().split(" ");
			
			numHeads = Integer.parseInt( lineSplit[0] );
			numKnights = Integer.parseInt( lineSplit[1] );			

			if( numHeads == 0 && numKnights == 0 ) break;

			// allocate array
			headArr = new int[numHeads];
			knightArr = new int[numKnights];

			// Get all data
			for(int i=0; i<numHeads; i++)
				headArr[i] = Integer.parseInt(in.readLine());

			for(int i=0; i<numKnights; i++)
				knightArr[i] = Integer.parseInt(in.readLine());

			// Do not process if numKnight < numHead
			if( numKnights < numHeads ) {
				System.out.println("Loowater is doomed!");
				continue;
			}

			// Process
			Arrays.sort( headArr );
			Arrays.sort( knightArr );

			// Create pointer
			int headPtr = 0, knightPtr = 0;

			// Vars
			int cost = 0;
			boolean isSucceed = true;

			while(true) {

				if( headArr[headPtr] <= knightArr[knightPtr] ) {
					cost += knightArr[knightPtr];
					headPtr++;
					knightPtr++;
				} else if( headArr[headPtr] > knightArr[headPtr] ) {
					knightPtr++;
				}

				// check for no answer
				if(  knightPtr == numKnights && headPtr < numHeads ) {
					isSucceed = false;
					break;
				}
				
				// check for finished job
				if( headPtr == numHeads ) {
					break;
				}				

			} // end process

			if( !isSucceed ) {
				System.out.println("Loowater is doomed!");
			} else {
				System.out.println( cost );
			}

		}

		in.close();

	}

}