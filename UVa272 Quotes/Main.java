import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static String line;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		int flag = 0;

		while( (line=in.readLine()) != null ) {

			sb = new StringBuilder();

			// Building
			for(int i=0; i<line.length(); i++) {

				if( line.charAt(i) == '"'  ) {
					
					switch( flag ) {
						case 0:
							flag = 1;
							sb.append("``");
							break;
						case 1:
							flag = 0;
							sb.append("''");
							break;						
					}				
					
				} else {
					sb.append( line.charAt(i) );
				}
			} // end for
			
			// print result
			System.out.println( sb );

		}		
	}
}
