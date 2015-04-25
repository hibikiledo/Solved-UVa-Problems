import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int TC;
	static int M, C, itemsCount;
	
	static int[][] data, mem;

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		TC = in.nextInt();
		
		while( TC-- > 0 ) {

			M = in.nextInt();
			C = in.nextInt();
			
			data = new int[C+1][20+1];
			mem = new int[C+1][M+1];
			
			// read input
			for(int i=1; i<=C; i++) { // fill num items in index 0	
				
				data[i][0] = itemsCount = in.nextInt();
				for(int j=1; j<=itemsCount; j++) {
					data[i][j] = in.nextInt();
				}
			}
			
			// solve
			int result = shop(0,0);
			if( result == 0 ) {
				System.out.println("no solution");
			} else {
				System.out.println( result );
			}
		
		}		
		
		in.close();
		
	}
	
	static int shop(int part, int moneyUsed) {	
		
		if( moneyUsed > M ) {
			return -1;
		}
		
		if( part == C ) {
			return moneyUsed;
		}		
		
		if( mem[part][moneyUsed] == 1 ) {
			return -1;
		}		
		
		// allocate array to keep result for current part
		int[] answer = new int[ data[part+1][0]+1 ];		
		int partPieces = data[part+1][0];
		
		
		for(int i=1; i<=partPieces; i++) {				
			answer[i] = shop(part+1, moneyUsed + data[part+1][i] );
			
			if( answer[i] >= 0 )
				mem[part+1][moneyUsed + data[part+1][i]] = 1;

		}
		
		// return max money used
		Arrays.sort(answer);
		return answer[ answer.length - 1 ];
		
	}

}
