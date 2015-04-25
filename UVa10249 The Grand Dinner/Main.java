import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int M, N, lastVisitedTable=0;
    static int[][] seatMatrix;
    static boolean fail;
    static StringBuilder sb;
    

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] splitStr;
        
        while( true ) {
        	
        	// reset flag & reallocate
        	fail = false;
        	sb = new StringBuilder();

	        splitStr = in.readLine().split(" ");
	
	        M = Integer.parseInt( splitStr[0] );
	        N = Integer.parseInt( splitStr[1] );
	        
	        if( M==0 && N==0 ) break;
	
	        // create the matrix
	        seatMatrix = new int[M+1][N+1];
	
	        // get team capacity (M)
	        splitStr = in.readLine().split(" ");
	        for(int i=1; i<=M; i++) {
	            seatMatrix[i][0] = Integer.parseInt(splitStr[i-1]);
	        }
	
	        // get table capacity (N)
	        splitStr = in.readLine().split(" ");
	        for(int i=1; i<=N; i++) {
	            seatMatrix[0][i] = Integer.parseInt(splitStr[i-1]);
	        }
	        
	        // start assigning
	        while( !isComplete() ) {
	        	
	        	if( !assign() ) { // cannot arrange print and continue
	        		fail = true;	
	        		break;
	        	}
	        }
	        
	        // show result
	        if( fail ) {
	        	
	        	sb.append("0\n");
	        	
	        } else { // not fail
	        	sb.append("1\n");
		        for(int i=1; i<seatMatrix.length; i++) {
		        	for(int j=1; j<seatMatrix[i].length; j++) {
		        		if( seatMatrix[i][j] == 1 ) 
		        			sb.append(j + " ");
		        	}
		        	sb.append("\n");
		        	
		        }
	        }
	        
	        System.out.print( sb );

        }
    }
    
    static boolean assign() {
    	int maxTeamSizeIndex = getMaxTeamSizeIndex();
    	int firstFailAssignIndex = -1;
    	
    	while( seatMatrix[maxTeamSizeIndex][0] > 0 ) {
   		
    		// move to next table
    		lastVisitedTable++;
    		
    		if( lastVisitedTable > N ) { // exceed available table
    			lastVisitedTable = 1;
    		}
    		
    		// assign if possible
    		if( isPossible( lastVisitedTable, maxTeamSizeIndex) ) {
    			
	    		seatMatrix[maxTeamSizeIndex][lastVisitedTable]++;
	    		seatMatrix[maxTeamSizeIndex][0]--;
	    		seatMatrix[0][lastVisitedTable]--;	
	    		firstFailAssignIndex = -1;
	    		
    		} else { // not possible    			
    			
    			// if come back to first failed table again, return false
    			if( lastVisitedTable == firstFailAssignIndex ) { 
    				return false;
    			}
    			
    			if( firstFailAssignIndex == -1 ) { // keep first fail table
    				firstFailAssignIndex = lastVisitedTable;
    			}
    			
    		}
    		
    	} // end while
    	
    	return true;
    }
    
    static int getMaxTeamSizeIndex() {
    	int maxSize = 0, index = -1;
    	for(int i=0; i<seatMatrix.length; i++) {
    		if( seatMatrix[i][0] > maxSize ) {
    			index = i;
    			maxSize = seatMatrix[i][0];
    		}
    	}    	
    	return index;
    }
    
    static boolean isPossible(int table, int team) {
    	return seatMatrix[ team ][ table ] < 1 // check >2 ppl from same team
    			&& seatMatrix[0][table] > 0 // check table capacity
    			&& seatMatrix[team][0] > 0; // check team capacity
    }
    
    static boolean isComplete() {
    	for(int i=0; i<seatMatrix.length; i++) {
    		if( seatMatrix[i][0] != 0 )
    			return false;
    	}
    	
    	return true;
    }    
    
}
