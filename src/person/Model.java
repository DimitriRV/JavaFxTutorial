package person;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
	
	private final StringProperty firstName;
  private final StringProperty lastName;
  private final StringProperty street;
  private final IntegerProperty postalCode;
  private final StringProperty city;
  private final ObjectProperty<LocalDate> birthday;
	
	public Model() {
		this(null, null);
	}

	public Model(String firstName, String lastName) 
	{
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
	// Some initial dummy data, just for convenient testing.
    this.street = new SimpleStringProperty("some street");
    this.postalCode = new SimpleIntegerProperty(1234);
    this.city = new SimpleStringProperty("some city");
    this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}

	public StringProperty getFirstName() {
		return firstName;
	}

	public StringProperty getLastName() {
		return lastName;
	}

	public StringProperty getStreet() {
		return street;
	}

	public IntegerProperty getPostalCode() {
		return postalCode;
	}

	public StringProperty getCity() {
		return city;
	}

	public ObjectProperty<LocalDate> getBirthday() {
		return birthday;
	}
}