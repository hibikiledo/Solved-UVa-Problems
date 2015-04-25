import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int TC,FBN;
	static int startNum, endNum;
	static boolean[] arrVisited = new boolean[ 9999 + 1 ];
	static ArrayDeque<Node> queue = new ArrayDeque<Node>();
	static String line;
	static String[] lineSplit;
	static int[] rootNode = new int[4], endNode = new int[4], tempNode = new int[4];
	static Node currentNode, nextStateNode;

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner( System.in );

		TC = in.nextInt();

		while( TC > 0 ) {

			// reset static things
			Arrays.fill(arrVisited, false);
			queue.clear();

			// Get initial sequence and Final Sequence
			rootNode[0] = in.nextInt();
			rootNode[1] = in.nextInt();
			rootNode[2] = in.nextInt();
			rootNode[3] = in.nextInt();
			startNum = parseAsInt( rootNode );

			endNode[0] = in.nextInt();
			endNode[1] = in.nextInt();
			endNode[2] = in.nextInt();
			endNode[3] = in.nextInt();
			endNum = parseAsInt( endNode );

			// Get forbidden pattern and mark as Visited
			FBN = in.nextInt();
			for(int i=0; i<FBN; i++) {
				tempNode[0] = in.nextInt();
				tempNode[1] = in.nextInt();
				tempNode[2] = in.nextInt();
				tempNode[3] = in.nextInt();
				arrVisited[ parseAsInt( tempNode ) ] = true;
			}

			// Add root node to the queue
			queue.add( new Node( rootNode, 0) );

			// Perform BFS search
			while( true ) {

				currentNode = queue.poll();				

				// stop when answer is found or queue is empty ( no answer )
				if( currentNode == null || parseAsInt(currentNode.numInArr) == endNum ) {
					break;
				}

				// generate next states for ''increment'' set, and add to queue if not visited
				for(int i=0; i<4; i++) {
					nextStateNode = new Node(incrementAtPos(currentNode.numInArr, i), currentNode.opCount+1);
					if( !arrVisited[parseAsInt(nextStateNode.numInArr)] ) {
						queue.add(nextStateNode);
						arrVisited[parseAsInt(nextStateNode.numInArr)] = true;
					}
				}

				// generate next states for ''decrement'' set, and add to queue if not visited
				for(int i=0; i<4; i++) {
					nextStateNode = new Node(decrementAtPos(currentNode.numInArr, i), currentNode.opCount+1);
					if( !arrVisited[parseAsInt(nextStateNode.numInArr)] ) {
						queue.add(nextStateNode);
						arrVisited[parseAsInt(nextStateNode.numInArr)] = true;
					}
				}

			}

			if( currentNode == null ) {
				System.out.println("-1");
			} else {
				System.out.println( currentNode.opCount );
			}			

			// Read input case separator, and decrement TC
			if( !in.hasNext() || (line=in.nextLine()).isEmpty() ) {
				TC--;
			}

		}

		in.close();
	}

	static int parseAsInt(int[] arrOfInt) {
		int result = 0;

		result += arrOfInt[0] * 1000;
		result += arrOfInt[1] * 100;
		result += arrOfInt[2] * 10;
		result += arrOfInt[3];

		return result;
	}

	static int[] parseAsIntArr(String[] arrOfString) {
		int[] result = new int[4];

		result[0] = Integer.parseInt( arrOfString[0] );
		result[1] = Integer.parseInt( arrOfString[1] );
		result[2] = Integer.parseInt( arrOfString[2] );
		result[3] = Integer.parseInt( arrOfString[3] );

		return result;
	}

	static int[] decrementAtPos(int[] arrOfInt, int pos) {
		int[] result = arrOfInt.clone();

		if( result[pos] - 1 < 0 ) result[pos] = 9;
		else --result[pos];

		return result;
	}

	static int[] incrementAtPos(int[] arrOfInt, int pos) {
		int[] result = arrOfInt.clone();

		if( result[pos] + 1 > 9 ) result[pos] = 0;
		else ++result[pos];

		return result;
	}

}

class Node {

	int opCount;
	int[] numInArr;

	public Node(int[] numInArr, int opCount) {
		this.opCount = opCount;
		this.numInArr = numInArr;
	}

}