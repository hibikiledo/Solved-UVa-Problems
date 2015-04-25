import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayDeque;

public class Main {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream out = System.out;
	
	static String line;
	static String[] lineSplit;
	static int L,R,C;
	static char[][][] dungeon;
	static boolean[][][] arrVisited;
	static ArrayDeque<Node> queue = new ArrayDeque<Node>();
	static Node startNode, endNode, currentNode;
	
	public static void main(String[] args) throws IOException {	
		
		while( true ) {
			
			// Reset Stuffs
			queue.clear();
			
			line = in.readLine();
			lineSplit = line.split(" ");
			
			L = Integer.parseInt( lineSplit[0] );
			R = Integer.parseInt( lineSplit[1] );
			C = Integer.parseInt( lineSplit[2] );
			
			if( L==0 && R==0 && C==0 ) break;
			
			// Create awful dungeon
			dungeon = new char[L+1][R+1][C+1];
			arrVisited = new boolean[L+1][R+1][C+1];
			
			// Read input
			for(int i=1; i<=L; i++) {
				for(int j=1; j<=R; j++) {
					line = in.readLine();
					for(int k=1; k<=C; k++) {
						dungeon[i][j][k] = line.charAt(k-1);
						if( line.charAt(k-1) == 'S' ) {
							startNode = new Node(i, j, k, 0);
						}
						if( line.charAt(k-1) == 'E' ) {
							endNode = new Node(i, j, k, null);
						}
						if( line.charAt(k-1) == '#' ) {
							arrVisited[i][j][k] = true;
						}
					}
				}
				
				// Read empty line
				in.readLine();
					
			}
			
			// Add starting position to queue (Root)
			queue.add( startNode );
			
			// Do BFS search
			while( true ) {
				
				currentNode = queue.poll();
				
				if( currentNode == null || currentNode.equals(endNode) ) {
					break;
				}
				
				// Add next states
				addNextStage(currentNode);				
				
			}
			
			if( currentNode == null ) {
				System.out.println("Trapped!");
			} else {
				System.out.printf( "Escaped in %d minute(s).\n", currentNode.timeUsed );
			}	
			
		}
		
		// Print counter
		System.out.println( Node.NodeObjCounter );

	}
	
	static void addNextStage(Node currentNode) {
		
		Node nextNode;
		
		// North
		nextNode = new Node(currentNode.L, Math.max(1, currentNode.R-1),
				currentNode.C, currentNode.timeUsed+1 );
		if( !arrVisited[nextNode.L][nextNode.R][nextNode.C] ) {
			queue.add(nextNode);
			arrVisited[nextNode.L][nextNode.R][nextNode.C] = true;
		}
		
		// South
		nextNode = new Node(currentNode.L, (int) Math.min(R, currentNode.R+1),
				currentNode.C, currentNode.timeUsed+1 );
		if( !arrVisited[nextNode.L][nextNode.R][nextNode.C] ) {
			queue.add(nextNode);
			arrVisited[nextNode.L][nextNode.R][nextNode.C] = true;
		}
		
		// East
		nextNode = new Node(currentNode.L, currentNode.R,
				Math.min(C, currentNode.C+1), currentNode.timeUsed+1 );
		if( !arrVisited[nextNode.L][nextNode.R][nextNode.C] ) {
			queue.add(nextNode);
			arrVisited[nextNode.L][nextNode.R][nextNode.C] = true;
		}
		
		// West
		nextNode = new Node(currentNode.L, currentNode.R,
				Math.max(1, currentNode.C-1), currentNode.timeUsed+1 );
		if( !arrVisited[nextNode.L][nextNode.R][nextNode.C] ) {
			queue.add(nextNode);
			arrVisited[nextNode.L][nextNode.R][nextNode.C] = true;
		}
		
		// Up
		nextNode = new Node(Math.min(L, currentNode.L+1), currentNode.R,
				currentNode.C, currentNode.timeUsed+1 );
		if( !arrVisited[nextNode.L][nextNode.R][nextNode.C] ) {
			queue.add(nextNode);
			arrVisited[nextNode.L][nextNode.R][nextNode.C] = true;
		}
		
		// Down
		nextNode = new Node(Math.max(1, currentNode.L-1), currentNode.R,
				currentNode.C, currentNode.timeUsed+1 );
		if( !arrVisited[nextNode.L][nextNode.R][nextNode.C] ) {
			queue.add(nextNode);
			arrVisited[nextNode.L][nextNode.R][nextNode.C] = true;
		}
		
	}	

}

class Node {
	
	int L,R,C;
	Integer timeUsed;
	
	static int NodeObjCounter = 0;
	
	public Node(int L, int R, int C, Integer timeUsed) {
		this.L = L;
		this.R = R;
		this.C = C;
		this.timeUsed = timeUsed;
		NodeObjCounter++;
	}

	@Override
	public boolean equals(Object obj) {
		Node other = (Node) obj;
		return this.L == other.L && this.R == other.R && this.C == other.C;		
	}
	
}