package parse;

/**
 * German Numbers Enum with Methods to parse a German string representative into the integer 
 * @author kkolodziej
 *
 */
public enum Numbers_Ger {
	//formatter:off
	ONE(1, "1", "EINS"),
	TWO(2, "2", "ZWEI"),
	THREE(3, "3", "DREI"),
	FOUR(4, "4", "VIER"),
	FIVE(5, "5", "FÜNF"),
	SIX(6, "6", "SECHS"),
	SEVEN(7, "7", "SIEBEN"),
	EIGHT(8, "8", "ACHT"),
	NINE(9, "9", "NEUN"),
	TEN(10, "10", "ZEHN");
	//...
	//formatter:on
	
	public final static Numbers_Ger[] NUMBERS_GER = {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN};
	
	private final int number;
	private final String[] string_representatives;
	
	private Numbers_Ger(int number, String... string_representatives) {
		this.number = number;
		this.string_representatives = string_representatives;
	}
	
	public static int tryParseGermanNumberString(String numberString) {
		for (Numbers_Ger number_ger : NUMBERS_GER) {
			int numberParse = tryParse(numberString, number_ger);
			if(numberParse != -1)
				return numberParse;
		}
		return -1;
	}
	
	private static int tryParse(String numberString, Numbers_Ger number_ger) {
		for (String string_representative : number_ger.string_representatives) {
			if(numberString.equalsIgnoreCase(string_representative))
				return number_ger.number;
		}
		return -1;
	}
}
