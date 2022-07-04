/**
 * 
 */
package parse;

/**
 * @author kkolodziej
 * 
 *         A Parser for the D2C_Valantic_Challenge that provides static methods
 *         to parse the ChallengeStrings
 */
public class ChallengeParser {

	private ChallengeParser() {
	}

	/**
	 * parse the challenge string
	 * @param challengeString
	 * @return the extracted information as "([name], [date], [time], [numberOfPersons])"
	 */
	public static String parseChallengeString(String challengeString) {

		Reservation reservation = new Reservation();

		// The challenge Strings are separated in salutation, main_part, conclusion
		String[] sentenceParts = challengeString.split(",");

		// the conclusion contains the name
		String[] conclusionSplit = sentenceParts[2].split(" ");

		// the main part contains the number of people, time and date
		String[] mainPartSplit = sentenceParts[1].split(" ");

		extractDataFromConclusion(conclusionSplit, reservation);
		extractDataFromMainPart(challengeString, mainPartSplit, reservation);

		return reservation.toString();
	}

	/**
	 * extracts the data (at the moment the name) from the conclusion part of the string and sets it in the reservation.
	 * @param conclusionSplit
	 * @param outReservation
	 */
	private static void extractDataFromConclusion(String[] conclusionSplit, Reservation outReservation) {
		// the 2 last words of the challenge strings always represented the name
		int lastEntryIndex = conclusionSplit.length;

		String firstName = conclusionSplit[lastEntryIndex - 2];
		String lastName = conclusionSplit[lastEntryIndex - 1];

		outReservation.setName(firstName + " " + lastName);
	}
	
	/**
	 * extracts the number of persons, date and time from the main part of the challenge string and sets it in the reservation
	 * @param challengeString
	 * @param mainSplit
	 * @param outReservation
	 * @return true, if all data has been set. false otherwise
	 */
	private static boolean extractDataFromMainPart(String challengeString, String[] mainSplit, Reservation outReservation) {
		boolean numberOfPersonsParsed = false;
		boolean dateParsed = false;
		boolean timeParsed = false;

		String currentString = "";
		for (int i = 0; i < mainSplit.length; i++) {
			currentString = mainSplit[i];

			if (!numberOfPersonsParsed) {
				String nextString = i < mainSplit.length - 1 ? mainSplit[i + 1] : "";
				if (tryParseNumberOfPersons(currentString, nextString, outReservation)) {
					numberOfPersonsParsed = true;
					continue;
				}
			}
			if (!dateParsed)
				if(tryParseDate(currentString, outReservation))
				{
					dateParsed = true;
					continue;
				}
			if (!timeParsed) {
				String priorString = i == 0 ? "" : mainSplit[i - 1];
				if(tryTimeParse(currentString, priorString, challengeString, outReservation))
				{
					timeParsed = true;
					continue;
				}
			}
		}
		return numberOfPersonsParsed && dateParsed && timeParsed;
	}

	/**
	 * try to parse the number of persons of a given string and the following string of the challenge string
	 * @param string
	 * @param followingString required to determine if it is indeed the number of persons and not the time
	 * @param outReservation
	 * @return true, if number of persons could be parsed and has been set. False otherwise.
	 */
	private static boolean tryParseNumberOfPersons(String string, String followingString, Reservation outReservation) {
		int tryParseGermanNumber = Numbers_Ger.tryParseGermanNumberString(string);
		if (tryParseGermanNumber != -1 && !isUhrString(followingString)) { // if "Uhr" follows this number must be a time
			outReservation.setNumberOfPersons(tryParseGermanNumber);
			return true;
		}
		return false;
	}
	
	/**
	 * try to parse the date out of a given string. Sets the value to the reservation if succeeded
	 * @param string
	 * @param outReservation
	 * @return true, if the date could be parsed and set to the reservation. False otherwise.
	 */
	private static boolean tryParseDate(String string, Reservation outReservation) {
		// The date is either represented by [number].[number] or [number].[Month]
		String dateSeparator = ".";
		if (string.contains(dateSeparator)) { 	//must be a date String if it contains "."
			String dayString = string.substring(0, string.indexOf(dateSeparator));
			int day = Integer.parseInt(dayString);

			String monthString = string.substring(string.indexOf(dateSeparator) + 1,
					string.length());
			if (monthString.contains(dateSeparator))
				monthString = monthString.substring(0, monthString.indexOf(dateSeparator));

			int month = Months_Ger.parseGermanMonthToInt(monthString);

			if (month != -1) {
				outReservation.setDate(day, month);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * try to parse the time out of a the given strings.
	 * @param parseString the string that determines if it is a time
	 * @param priorString 
	 * @param wholeString the whole challenge string
	 * @param outReservation
	 * @return True, if the time could be parsed and was set to the reservation, False otherwise.
	 */
	private static boolean tryTimeParse(String parseString, String priorString, String wholeString, Reservation outReservation) {
		if (isUhrString(parseString)) {
			String timeString = priorString;
			int indexOfSeparator = timeString.indexOf(':');
			if (indexOfSeparator == -1) {
				timeString = timeString + ":00";
				indexOfSeparator = 1;
			}
			Integer hours = Integer.parseInt(timeString.substring(0, indexOfSeparator));
			if (hours < 12 && containsEveningString(wholeString)) {
				hours += 12;
			}
			timeString = hours.toString() + ":"
					+ timeString.substring(indexOfSeparator + 1, timeString.length());
			outReservation.setTime(timeString);
			return true;
		}
		return false;
	}

	/**
	 * checks if the string contains the German word "abend";
	 * @param string
	 * @return
	 */
	private static boolean containsEveningString(String string) {
		return string.contains("Abend") || string.contains("abend");
	}

	/**
	 * checks if the string is the German word "Uhr"
	 * @param string
	 * @return
	 */
	private static boolean isUhrString(String string) {
		final String uhr_str = "Uhr";
		return string.equalsIgnoreCase(uhr_str);
	}
}
