import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();;
		String[] files;
		int numFile, maxLength;
		String line;

		while( (line=in.readLine()) != null ) {

			numFile = Integer.parseInt( line );
			files = new String[ numFile ];
			sb = new StringBuilder();

			maxLength = 0;

			// read all the files
			for(int i=0; i<numFile; i++) {
				files[i] = line = in.readLine();
				// also track of max length
				if( line.length() > maxLength )
					maxLength = line.length();
			}

			// sort them
			Arrays.sort( files );

			// calculate
			int numCol = 62 / (maxLength + 2);
			int numRow = (int) Math.ceil( (float) numFile / numCol );

			// buffer result
			sb.append("------------------------------------------------------------\n");
			int colCount;
			String format;
			for(int i=0; i<numRow; i++) { // num row
				colCount = 0;
				for(int j=i; colCount<numCol && j<files.length; j+=numRow, colCount++) {
					format = "%-"+(maxLength+2)+"s";
					sb.append( String.format(format, files[j]) );
				}
				sb.append("\n");			
			}

			// print result
			System.out.print( sb );
		}		
	}	
}