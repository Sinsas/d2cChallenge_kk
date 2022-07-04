package parse;

import java.util.HashSet;
import java.util.Set;

public class App {

	public static void main(String[] args) {
		Set<String> challengeStrings = new HashSet<String>();
		challengeStrings.add("Hallo, bitte für zwei Personen einen Tisch am 19.3. um 20:00 Uhr, Vielen Dank Klaus Müller");
		challengeStrings.add("Sehr geehrte Damen Herren, wir würden gern am 9.April 9:45 Uhr mit sechs Leuten zum Brunch kommen, Mit freundlichen Grüßen Maria Meier");
		challengeStrings.add("Guten Tag, einen Tisch für 8 Mann am 1.5. 9 Uhr abends, Gruß Franz Schulze");
		
		for (String string : challengeStrings) {
			String parsedString = ChallengeParser.parseChallengeString(string);
			System.out.println(string + "  ->  " + parsedString);
		}
		
	}

}
