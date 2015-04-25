import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
 
public class Main {
   
   static ArrayList<Corr> allOneCorr = new ArrayList<Corr>();
   static ArrayList<Corr> allThreeCorr = new ArrayList<Corr>();
   static ArrayList<Integer> minSteps = new ArrayList<Integer>();
   static int M, min, distance;
   static String line;
 
   public static void main(String[] args) throws IOException {
      
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      
      while( (line=in.readLine()) != null ) {
         
         // reset static things         
         allOneCorr.clear();
         allThreeCorr.clear();
         minSteps.clear();
         
         M = Integer.parseInt( line );
         
         // Get all ones and threes
         for(int i=0; i<M; i++) {
            line = in.readLine();
            allOneCorr.addAll( getOneCorr(i, line) );
            allThreeCorr.addAll( getThreeCorr(i, line) );
         }
         
         // Compute minimum steps from all ones to all threes
         for( Corr one : allOneCorr ) {
            min = Integer.MAX_VALUE;
            
            for(Corr three : allThreeCorr) {
               distance = Math.abs(one.x-three.x) + Math.abs(one.y-three.y);
               if( distance < min ) {
                  min = distance;
               }
            }
            
            // keep min distance for each ones
            minSteps.add( min );
         }
         
         // Get maximum of all min
         Collections.sort(minSteps);
         
         // Print result
         System.out.println( minSteps.get( minSteps.size()-1 ) );      
         
      }      
 
   }
   
   static ArrayList<Corr> getOneCorr(int xCorr, String line) {
      ArrayList<Corr> result = new ArrayList<Corr>();
      for(int i=0; i<line.length(); i++) {
         if( line.charAt(i) == '1' ) {
            result.add( new Corr(xCorr, i) );
         }
      }
      
      return result;
   }
   
   static ArrayList<Corr> getThreeCorr(int xCorr, String line) {
      ArrayList<Corr> result = new ArrayList<Corr>();
      for(int i=0; i<line.length(); i++) {
         if( line.charAt(i) == '3' ) {
            result.add( new Corr(xCorr, i) );
         }
      }
      
      return result;
   }
 
}
 
class Corr {
   int x,y;
   
   public Corr(int x, int y) {
      this.x = x;
      this.y = y;
   }
 
   @Override
   public String toString() {
      return "Corr [x=" + x + ", y=" + y + "]";
   }
   
}