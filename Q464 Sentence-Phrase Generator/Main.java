import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int k = 0;
	
	public static enum Grammar {
		SENTENCE,
		TRANS_SENTENCE,
		INTRANS_SENTENCE,
		SUBJECT,
		OBJECT,
		NOUN_PHRASE,
		MODIFIED_NOUN,
		MODIFIER,VERB_PHRASE,INTRANS_VERB_PHRASE,
		PREP_PHRASE,
		NOUN,
		TRANS_VERB,
		INTRANS_VERB,
		ARTICLE,
		ADJECTIVE,
		ADVERB,
		PREPOSITION,
		EMPTY
	}
	
	static String generate( Grammar g ) {
		
		String a,b,c,d,e;
		
		switch( g ) {
			case SENTENCE:
				k++;
				if( (k % 2)+1 == 1 ) return generate( Grammar.TRANS_SENTENCE );
				else return generate( Grammar.INTRANS_SENTENCE );
				// break;
			case TRANS_SENTENCE:
				a = generate( Grammar.SUBJECT );
				b = generate( Grammar.VERB_PHRASE );
				c = generate( Grammar.OBJECT );
				d = generate( Grammar.PREP_PHRASE );
				return a + " " + b + " " + c + " " + d;
			case INTRANS_SENTENCE:
				a = generate( Grammar.SUBJECT );
				b = generate( Grammar.INTRANS_VERB_PHRASE );
				c = generate( Grammar.PREP_PHRASE );
				return a + " " + b + " " + c;
			case SUBJECT:
				return generate( Grammar.NOUN_PHRASE );
			case OBJECT:
				return generate( Grammar.NOUN_PHRASE );
			case NOUN_PHRASE:
				a = generate( Grammar.ARTICLE );
				b = generate( Grammar.MODIFIED_NOUN );
				return a + " " + b;
			case MODIFIED_NOUN:
				k++;
				if( (k%2)+1 == 1 ) {
					return generate( Grammar.NOUN );
				} else {
					a = generate( Grammar.MODIFIER );
					b = generate( Grammar.NOUN );
					return a + " " + b;
				}
			case MODIFIER:
				k++;
				if( (k%2)+1 == 1 ) {
					return generate( Grammar.ADJECTIVE );
				} else {
					a = generate( Grammar.ADVERB );
					b = generate( Grammar.ADJECTIVE );
					return a + " " + b;
				}
			case VERB_PHRASE:
				k++;
				if( (k%2)+1 == 1 ) {
					return generate( Grammar.TRANS_VERB );
				} else {
					a = generate( Grammar.ADVERB );
					b = generate( Grammar.TRANS_VERB );
					return a + " " + b;
				}
			case INTRANS_VERB_PHRASE:
				k++;
				if( (k%2)+1 == 1 ) {
					return generate( Grammar.INTRANS_VERB );
				} else {
					a = generate( Grammar.ADVERB );
					b = generate( Grammar.INTRANS_VERB );
					return a + " " + b;
				}
			case PREP_PHRASE:
				k++;
				if( (k%2)+1 == 1 ) {
					a = generate( Grammar.PREPOSITION );
					b = generate( Grammar.NOUN_PHRASE );
					return a + " " + b;
				} else {
					return generate( Grammar.EMPTY );
				}
			case NOUN:
				k++;
				switch ( (k%5) + 1 ) {
					case 1: return "man";
					case 2: return "dog";
					case 3: return "fish";
					case 4: return "computer";
					case 5: return "waves";
				}
			case TRANS_VERB:
				k++;
				switch ( (k%4) + 1 ) {
					case 1: return "struck";
					case 2: return "saw";
					case 3: return "bit";
					case 4: return "took";
				}
			case INTRANS_VERB:
				k++;
				switch ( (k%4)+1 ) {
				case 1: return "slept";
				case 2: return "jumped";
				case 3: return "walked";
				case 4: return "swam";
				}
			case ARTICLE:
				k++;
				if( (k%2)+1 == 1 ) {
					return "the";
				} else {
					return "a";
				}
			case ADJECTIVE:
				k++;
				switch ((k%4)+1) {
					case 1: return "green";
					case 2: return "small";
					case 3: return "rabid";
					case 4: return "quick";
				}
			case ADVERB:
				k++;
				switch ( (k%3)+1) {
					case 1: return "nearly";
					case 2: return "suddenly";
					case 3: return "restlessly";
				}
			case PREPOSITION:
				k++;
				switch ( (k%3) +1 ) {
					case 1: return "on";
					case 2: return "over";
					case 3: return "through";
				}
			case EMPTY:
				return "";	
			default: 
				return "-1";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line, output="";
		
		while( (line=in.readLine()) != null ) {
			
			switch( line ) {
				case "sentence":
					output = generate( Grammar.SENTENCE );
					break;
				case "trans-sentence":
					output = generate( Grammar.TRANS_SENTENCE );
					break;
				case "intrans-sentence":
					output = generate( Grammar.INTRANS_SENTENCE );
					break;
				case "subject":
					output = generate( Grammar.SUBJECT );
					break;
				case "object":
					output = generate( Grammar.OBJECT );
					break;
				case "noun-phrase":
					output = generate( Grammar.NOUN_PHRASE );
					break;
				case "modified-noun":
					output = generate( Grammar.MODIFIED_NOUN );
					break;
				case "modifier":
					output = generate( Grammar.MODIFIER );
					break;
				case "verb-phrase":
					output = generate( Grammar.VERB_PHRASE );
					break;
				case "intrans-verb-phrase":
					output = generate( Grammar.INTRANS_VERB_PHRASE );
					break;
				case "prep-phrase":
					output = generate( Grammar.PREP_PHRASE );
					break;
				case "noun":
					output = generate( Grammar.NOUN );
					break;
				case "trans-verb":
					output = generate( Grammar.TRANS_VERB );
					break;
				case "intrans-verb":
					output = generate( Grammar.INTRANS_VERB );
					break;
				case "article":
					output = generate( Grammar.ARTICLE );
					break;
				case "adjective":
					output = generate( Grammar.ADJECTIVE );
					break;
				case "adverb":
					output = generate( Grammar.ADVERB );
					break;
				case "preposition":
					output = generate( Grammar.PREPOSITION );
					break;
				case "empty":
					output = generate( Grammar.EMPTY );
					break;
			}
			
			// print result
			System.out.println( output.trim() );
			
		} // end while
		
	}

}
