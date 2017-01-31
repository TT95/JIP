package task2;

import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private String firstName;
	private String lastName;

	
	
	private static List<String> popularNames = new ArrayList<String>();
	

	public Person(String firstName, String lastName) throws WrongNameException, WrongSurnameException {
		if(popularNames.isEmpty()) {
			popularNames.addAll(PopularNames.getPopularNames()); 
		}
		if(!popularNames.contains(firstName)) {
			throw new WrongNameException(firstName,"Not popular name!");
		}
		if(checkSurname(lastName)) {
			throw new WrongSurnameException(lastName,"Not good surname!");
		}
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	private boolean checkSurname(String surname) {
		if(surname.contains("-")) {
			return false;
		}
		return true;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}

	
	
	

}
