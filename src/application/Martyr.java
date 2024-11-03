

package application;




import java.util.Comparator;

import javafx.beans.property.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Martyr implements Comparable<Martyr> , Comparator<Martyr> {

	private SimpleStringProperty name;
	private SimpleStringProperty district;
	private SimpleStringProperty location;
	private SimpleIntegerProperty age;
	private SimpleObjectProperty<Character> gender;
	

	
	Martyr(String name,int age,String location,String district ,char gender){
		this.name=new SimpleStringProperty (name);
		this.district = new SimpleStringProperty (district);;
		this.location = new SimpleStringProperty (location);
		this.age=new SimpleIntegerProperty(age);
		this.gender=new SimpleObjectProperty<Character> (gender);
	}
	public int compareTo(Martyr o) { 
	int compare =  district.get().compareToIgnoreCase(o.getDistrict());
	if(compare != 0)
		return compare;
	return name.get().compareToIgnoreCase(o.getName());
		
	
	
}
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name =  new SimpleStringProperty (name);
	}

	public int getAge() {
		return age.get();
	}

	public void setAge(int age) {
		this.age = new  SimpleIntegerProperty (age);
	}

	public char getGender() {
		return gender.get();
	}

	public void setGender(char gender) {
		this.gender = new SimpleObjectProperty<>(gender) ;
	}

	
	
	public String getDistrict() {
		return district.get();
	}
	public void setDistrict(String district) {
		this.district = new SimpleStringProperty(district);
	}
	public String getLocation() {
		return location.get();
	}
	public void setLocation(SimpleStringProperty location) {
		this.location = location;
	}
	@Override
	public String toString() { // toString for martyr information
		return name.get();//+ "," + age.get() ;//+ "," + age.get() + "," + gender.get();
	}
	

	
	
	@Override
	public int compare(Martyr t1, Martyr t2) {
		return t1.getAge() - t2.getAge();
	}
	
}
