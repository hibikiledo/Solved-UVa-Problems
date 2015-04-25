import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;	
		
		while( (line=in.readLine()) != null ) {
			
			int i = 0;
			int count = 0;
			
			while( i < line.length() ) {
				
				try {
					// move until letter is found
					while( !isAlphabet(line.charAt(i))) {
						i++;
						// move beyond string and no letter found
						// end of searching
						if( i > line.length()-1 ) throw new Exception();
					}
					// move until non letter is found
					while( isAlphabet(line.charAt(i))) {
						i++;
						// in case that the line ends with word
						// increment count as well
						if( i > line.length()-1 ) {
							count++;
							throw new Exception();
						}
					}
					
					// normal cases
					count++;
					
				} catch (Exception e) {	}	
				
			}			
			System.out.println( count );			
		}		
	}
	
	static boolean isAlphabet(char c) {
		return ( c >= 65 && c <= 90) || (c >= 97 && c <= 122); 
	}
	
}