package parse;

import java.util.HashSet;
import java.util.Set;

public class App {

	public static void main(String[] args) {
		Set<String> challengeStrings = new HashSet<String>();
		challengeStrings.add("Hallo, bitte f�r zwei Personen einen Tisch am 19.3. um 20:00 Uhr, Vielen Dank Klaus M�ller");
		challengeStrings.add("Sehr geehrte Damen Herren, wir w�rden gern am 9.April 9:45 Uhr mit sechs Leuten zum Brunch kommen, Mit freundlichen Gr��en Maria Meier");
		challengeStrings.add("Guten Tag, einen Tisch f�r 8 Mann am 1.5. 9 Uhr abends, Gru� Franz Schulze");
		
		for (String string : challengeStrings) {
			String parsedString = ChallengeParser.parseChallengeString(string);
			System.out.println(string + "  ->  " + parsedString);
		}
		
	}

}
