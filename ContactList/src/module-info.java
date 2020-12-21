module ContactList {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;
   // requires javafx.base;
    opens sample.Contacts;
    opens sample;
}