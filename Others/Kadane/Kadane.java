public class Kadane {
	
	public static void main(String[] args) {
		
		int[] data = new int[]{4, 3, -10, 3, -1, 2, 0, -3, 5, 7, -4, -8, -10, 4, 7, -30};
		kadane( data );
		
	}

	public static void kadane(int[] A) {

		int maxSum = Integer.MIN_VALUE;
		int maxLeft = 0, maxRight = 0;  
		int left = 0, right = 0;
		int currentMax = 0;

		for(int i=0; i<A.length; i++) {
			currentMax += A[i];

			if( currentMax > maxSum ) {
				maxSum = currentMax;
				right = i;
				maxLeft = left;
				maxRight = right;
			}

			if( currentMax < 0 ) {
				currentMax = 0;
				left = i+1;
				right = i+1;
			}

		}
		
		System.out.println( maxLeft + " " + maxRight + " " + maxSum );

	}
}
