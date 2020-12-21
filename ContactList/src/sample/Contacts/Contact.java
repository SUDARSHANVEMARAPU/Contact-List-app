package sample.Contacts;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
     private final SimpleStringProperty firstName=new SimpleStringProperty("");
     private final SimpleStringProperty lastName=new SimpleStringProperty("");
     private final SimpleStringProperty phoneNumber=new SimpleStringProperty("");

    public Contact(){
        this("","","");
    }

    public Contact(String first_name, String last_name, String phone_number) {
        this.firstName.set(first_name);
        this.lastName.set(last_name);
        this.phoneNumber.set(phone_number);

    }


    public Contact(String first_name, String phone_number) {
        this.firstName.set(first_name);
        this.phoneNumber.set(phone_number);
        this.lastName.set(" ");

    }



    public String getFirst_name() {
        return firstName.get();
    }

    public void setFirst_name(String first_name) {
         this.firstName.set(first_name);

    }

    public String getLast_name() {
        return lastName.get();
    }

    public void setLast_name(String second_name) {
        this.lastName.set(second_name);
    }

    public String getPhone_number() {
        return phoneNumber.get();
    }

    public void setPhone_number(String phone_number) {
        this.phoneNumber.set(phone_number);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", phoneNumber=" + phoneNumber +
                '}';
    }



    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }



    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }


    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }
}
