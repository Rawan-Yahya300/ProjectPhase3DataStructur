package application;





public class District implements Comparable<District> {

	

	private String district; // name of district
	private LinkedList<Martyr> martyrs;
	private LinkedList<Location> locatons;

	public District(String district) { // constructor

		this.district = district;
		martyrs = new LinkedList<>();
		locatons = new LinkedList<>();
		
	}
 
	// getters and setters
	
	public String getDistrict() { 
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	

	

	@Override
	public int compareTo(District o) { // compare to according to the name of district

		return district.compareToIgnoreCase(o.getDistrict());
	
	}

	public String toString() {
		return district;
	}

	public LinkedList<Martyr> getMartyrs() {
		return martyrs;
	}

	public void setMartyrs(LinkedList<Martyr> martyrs) {
		this.martyrs = martyrs;
	}

	public LinkedList<Location> getLocatons() {
		return locatons;
	}

	public void setLocatons(LinkedList<Location> locatons) {
		this.locatons = locatons;
	}


}
