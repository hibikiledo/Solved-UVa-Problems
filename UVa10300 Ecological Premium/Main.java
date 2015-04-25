import java.util.Scanner;

public class Main {

	static int TC, numFarmer;
	static int[][] data;
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		TC = in.nextInt();
		
		while( TC-- > 0 ) {
			
			numFarmer = in.nextInt();
			
			// allocate array
			data = new int[ numFarmer ][3];
			
			// read input
			for(int i=0; i<numFarmer; i++) {
				for(int j=0; j<3; j++) {
					data[i][j] = in.nextInt();
				}				
			}
			
			// calculate total premium
			int totalPremium=0;
			for(int i=0; i<numFarmer; i++) {
				totalPremium +=  data[i][0] * data[i][2] ;
			}
			
			// print result
			System.out.println( totalPremium );			
			
		}
		
		in.close();

	}

}
