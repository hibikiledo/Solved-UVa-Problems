import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Scanner in = new Scanner(System.in);
		//StringBuilder sb = new StringBuilder();
		String[] strSplited;
		String line;
		
		// Keep working until EOF
		while( (line=in.readLine()) != null ) {
			
			strSplited = line.split(" ");
			if( isEncodedOK( strSplited[0], strSplited[1] ) ) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}			
		}
		
	}
	
	static boolean isEncodedOK(String str1, String str2) {
		
		boolean found = false;
		int lastFoundIndex = 0, index;
		
		for(int i=0; i<str1.length(); i++) {
			found = false;
			
			index = lastFoundIndex != 0 ? lastFoundIndex+1 : lastFoundIndex;
			
			for(int j=index; j<str2.length(); j++) {				
				if( str1.charAt(i) == str2.charAt(j) ) {
					lastFoundIndex = j;
					found = true;
					break;
				}				
			}
			
			if( !found ) break;
			
		}		
		return found;
	}
}
