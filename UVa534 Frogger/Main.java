import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int numNodes, scenarioCount=1;
		String[] lineSplit;
		Node[] nodes;
		double[][] dist;
		
		while( true ) {
			
			numNodes = Integer.parseInt( in.readLine() );
			if( numNodes == 0 ) break;
			
			nodes = new Node[numNodes+1];
			dist = new double[numNodes+1][numNodes+1];
			
			// Get all nodes
			for(int i=1; i<=numNodes; i++) {
				lineSplit = in.readLine().split(" ");
				nodes[i] = new Node(Integer.parseInt(lineSplit[0]), 
						Integer.parseInt(lineSplit[1]));				
			}
			
			// Initialize
			for(int i=1; i<=numNodes; i++) {
				for(int j=1; j<=numNodes; j++) {					
					dist[i][j] = calWeight(nodes[i], nodes[j]);					
				}
			}
			
			for(int i=0; i<numNodes; i++) {
				dist[i][i] = 0;
			}
			
			// Do floyd-Warshall
			for(int k=1; k<=numNodes; k++) {
				for(int i=1; i<=numNodes; i++) {
					for(int j=1; j<=numNodes; j++) {
						dist[i][j] = Math.min(dist[i][j], Math.max(dist[i][k], dist[k][j]));
					}
				}
			}
			
			// Get result
			out.write(String.format("Scenario #%d\n", scenarioCount++));
			out.write(String.format("Frog Distance = %.3f\n\n", dist[1][2]));		
			
			// Read empty line
			in.readLine();
			
		}	
		
		in.close();		
		out.close();		
		
	}
	
	static double calWeight(Node src, Node dest) {
		return Math.sqrt( Math.pow(Math.abs(src.x-dest.x),2) + Math.pow(Math.abs(src.y-dest.y),2) );
	}

}

class Node {
	int x, y;
	public Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}
