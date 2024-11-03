package application;




public class Location implements Comparable<Location> {

	private String location; // name of location
	private LinkedList<Martyr> martyrs;

	public Location(String location) { // constructor
		this.location = location;
		martyrs = new LinkedList<>();
		
	}

	@Override
	public int compareTo(Location o) { // CopmpareTo according to name of location
		return location.compareToIgnoreCase(o.getLocation());
	
	}

	public String toString() { // toString
		return location;
	}
	


	public LinkedList<Martyr> getMartyrs() {
		return martyrs;
	}

	public void setMartyrs(LinkedList<Martyr> martyrs) {
		this.martyrs = martyrs;
	}

	// getters and setters
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	

}
