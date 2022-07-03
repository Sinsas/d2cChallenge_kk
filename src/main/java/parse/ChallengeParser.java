/**
 * 
 */
package parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kkolodziej
 * 
 *         A Parser for the D2C_Valantic_Challenge that. Provides static methods
 *         to parse the ChallengeStrings
 */
public class ChallengeParser {

	private ChallengeParser() {
	}

	public static String parseChallengeString(String challengeString) {

		Reservation reservation = new Reservation();
		String[] challengeStringSplit = challengeString.split(" ");

		extractName(challengeStringSplit, reservation);
		extractDate(challengeString, reservation);
		
		Pattern timePattern = Pattern.compile(
							"([01][0-9]|[2][0-4])" +	//00 to 24 
							"[:]" + 					//":"
							"[0-5][0-9]" + 				//00 to 59
							);
		Matcher timeMatcher;
		for(int i = 0; i < challengeStringSplit.length; i++) {
			timeMatcher = timePattern.matcher(challengeStringSplit[i]);
			if(timeMatcher.find())
			{
				String timeString = timeMatcher.group(1);
				if(Integer.parseInt(timeString.substring(0, 1))
				reservation.setTime(timeMatcher.group(1));
				
				break;
			}
		}
		
		
		
		return "";
	}

	private static void extractName(String[] challengeStringSplit, Reservation reservation) {

		// the 2 last words of the challenge strings always represented the name
		int lastEntryIndex = challengeStringSplit.length;
		String firstName = challengeStringSplit[lastEntryIndex - 1];
		String lastName = challengeStringSplit[lastEntryIndex];
		reservation.setName(firstName + " " + lastName);
	}

	private static void extractDate(String challengeString, Reservation reservation) {
		// The date is either represented by [number.number] or [number. Month]
		
		
		
		//REGEX SUX
		// dd.mm pattern with possible leading 0
		//@formatter: off
		Pattern dateWithTwoNumbers = Pattern.compile(
						"([1-9]|0[1-9]|[12][0-9]|3[01])" + 	//dd from 1 to 31 
						"[.]" + 							//"."
						"([1-9]|0[1-9]|1[012])"				//mm from 1 to 12
						);
		
		Pattern dateWithNumberAndMonth = Pattern.compile(
				"\\b" + 									//word boundary start
				"([1-9]|0[1-9]|[12][0-9]|3[01])" + 			//dd from 1 to 31 
				"[.]" +									 	//"."
				"[ ]" +										//" "
				"([JFSMAOND])" +							//"Months starting letter
				".*?" +										//(.) any symbol (*) previous character 0 or more times [so any character 0 or more times] (?) matches as few characters as it can instead of whole string
				"\\b"										//word boundary end
				);
		//formatter: on
		boolean found = false;
		Matcher dateMatcher = dateWithNumberAndMonth.matcher(challengeString);
		if(dateMatcher.find()) {
			reservation.setDate(dateMatcher.group(1));
			found = true;
		}
		Matcher dateMatcherWithMonth = dateWithNumberAndMonth.matcher(challengeString);
		if(dateMatcherWithMonth.find() && !found){
			reservation.setDate(dateMatcherWithMonth.group(1));
		}
		
		//REGEX STILL SUX (END)
	}

}
