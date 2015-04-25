import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class MainRecursive {
	
	static int[][] mem, data;
	static int N, M;

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] lineSplit;
		
		while( true ) {
			
			lineSplit = in.readLine().split(" ");
			
			N = Integer.parseInt( lineSplit[0] );
			M = Integer.parseInt( lineSplit[1] );
			
			if( M==0 && N==0 ) break;
			
			mem = new int[N+1][M+1];
			data = new int[N+1][M+1];
			
			// fill data array
			for(int i=1; i<=N; i++) {
				
				lineSplit = in.readLine().split(" ");
				for(int j=1; j<=M; j++) {
					data[i][j] = Integer.parseInt( lineSplit[j-1] );
				}
				
			}
			
			// get top level data
			int[] answer = new int[M];
			for(int i=0; i<M; i++) {
				answer[i] = findPath(1, i+1);
			}
			
			// find min
			Arrays.sort( answer );
			
			// print
			System.out.println( answer[0] );
			
		}
	}
	
	static int findPath(int row, int col) {		
		
		if( mem[row][col] != 0 ) {
			return mem[row][col];
		}
		
		if( row == N ) {
			return mem[row][col] = data[row][col];
		}
				
		if( col == 1 ) { // leftmost
			return mem[row][col] = Math.min(findPath(row+1, col), findPath(row+1, col+1)) + data[row][col];
		} else if( col == M ) { // right most
			return mem[row][col] = Math.min(findPath(row+1,col), findPath(row+1,col-1)) + data[row][col];
		} else { // middle
			return mem[row][col] = Math.min(findPath(row+1, col), Math.min(findPath(row+1, col-1), findPath(row+1, col+1))) + data[row][col];
		}
		
	}

}