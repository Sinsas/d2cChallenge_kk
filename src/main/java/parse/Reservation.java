package parse;

public class Reservation {
	private String name;
	private String date;
	private String time;
	private int numberOfPersons;
	
	public Reservation() {}

	public Reservation(String name, String date, String time, int numberOfPersons) {
		super();
		this.name = name;
		this.date = date;
		this.time = time;
		this.numberOfPersons = numberOfPersons;
	}

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

	public void setTime(String time) {
		this.time = time;
	}

	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}
	
	
}
