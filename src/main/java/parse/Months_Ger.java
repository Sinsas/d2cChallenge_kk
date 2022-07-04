package parse;

/**
 * Months Enum with Methods to parse a German Month representation into the corresponding integer value
 * @author kkolodziej
 *
 */
public enum Months_Ger {
	//@formatter:off
	JANUARY(1, "JANUAR", "JAN", "1"), 
	FEBRUARY(2, "FEBRUAR", "FEB", "2"), 
	MARCH(3, "MÄRZ", "MÄR", "3"), 
	APRIL(4, "APRIL", "APR", "4"),
	MAY(5, "MAI", "5"), 
	JUNE(6, "JUNI, JUN", "6"), 
	JULY(7, "JULI", "JUL", "7"), 
	AUGUST(8, "AUGUST", "AUG", "8"),
	SEPTEMBER(9, "SEPTEMBER", "SEP", "9"), 
	OCTOBOR(10, "OKTOBER", "OKT", "10"), 
	NOVEMBER(11, "NOVMEBER", "NOV", "11"),
	DECEMBER(12, "DEZEMBER", "DEZ", "12");
	//@formatter:on
	
	private final String[] german_names;
	private final int month_int;
	public static final Months_Ger[] MONTHS_GER = { JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER,
			OCTOBOR, NOVEMBER, DECEMBER };

	private Months_Ger(int monthNumber, String... germanNames) {
		this.month_int = monthNumber;
		this.german_names = germanNames;
	}

	public int getMonthInt() {
		return month_int;
	}

	/**
	 * Converts a string representative of a German Month to the corresponding
	 * integer
	 * 
	 * @param parseMonth string representative of a German Month
	 * @return corresponding integer of the month if it is passed successfully, -1
	 *         otherwise
	 */
	public static int parseGermanMonthToInt(String parseMonth) {
		for (Months_Ger months : MONTHS_GER) {
			int monthInt = tryParseToInt(parseMonth, months);
			if (monthInt != -1)
				return monthInt;
		}
		return -1;
	}

	private static int tryParseToInt(String parseMonth, Months_Ger month) {
		for (String name : month.german_names)
			if (parseMonth.equalsIgnoreCase(name)) {
				return month.getMonthInt();
			}
		return -1;
	}
}
