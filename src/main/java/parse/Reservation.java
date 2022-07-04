package parse;

/**
 * Holds the data of the reservation
 * @author kkolodziej
 *
 */
public class Reservation {
	private String name;
	private String date;
	private String time;
	private int numberOfPersons;
	
	public Reservation() {}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public int getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setDate(int day, int month) {
		String dateSeparator = ".";
		this.setDate(String.format("%02d", day) + dateSeparator + String.format("%02d", month) + dateSeparator);
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	@Override
	public String toString() {
		return "(" + name + ", " + date + ", " + time + ", " + numberOfPersons + ")";
	}
	
	
	
	
}
