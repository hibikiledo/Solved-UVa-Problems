import java.util.Arrays;
import java.util.Scanner;


public class Main {
	
	static int TC, count=1;
	static int[] data = new int[3];

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		TC = in.nextInt();
		
		while( TC-- > 0 ) {
			
			for(int i=0; i<data.length; i++) {
				data[i] = in.nextInt();
			}
			
			Arrays.sort( data );
			
			System.out.printf("Case %d: %d\n",  count++, data[1] );
			
		}
		
		in.close();

	}

}
