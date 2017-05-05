package ch.makery.address.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.makery.address.util.LocalDateAdapter;
import domain.PersonDomainModel;

public class Person extends PersonDomainModel {

	public Person() {
		this(null, null, null, (Integer) null, null, null);
	}

	public Person(String firstName, String lastName, String street, int postalCode, String city, Object birthday) {
		this.setFirstName(firstName);
		this.setLastName(lastName);

		// Some initial dummy data, just for convenient testing.
		this.setStreet(street);
		this.setPostalCode(postalCode);
		this.setCity(city);

		Date dBirthDay = null;
		try {
			dBirthDay = new SimpleDateFormat("yyyy-mm-dd").parse(birthday.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBirthday(dBirthDay);
	}

	public StringProperty getFirstNameProperty() {
		return new SimpleStringProperty(getFirstName());
	}

	public StringProperty getLastNameProperty() {
		return new SimpleStringProperty(getLastName());
	}

	public StringProperty getStreetProperty() {
		return new SimpleStringProperty(getStreet());
	}

	public StringProperty getCityProperty() {
		return new SimpleStringProperty(getCity());
	}

	public IntegerProperty getPostalCodeProperty() {
		return new SimpleIntegerProperty(getPostalCode());
	}

}