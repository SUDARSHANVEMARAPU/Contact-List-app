package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Contacts.Contact;

public class DialogueController {
    @FXML
    private TextField last_name_field;
    @FXML
    private TextField first_name_field;

    @FXML
    private TextField phone_number_field;



    @FXML
    public Contact AddNewContact(){
        Contact contact=new Contact();
        if(first_name_field.getText()==null){
            first_name_field.setText(" ");
        }
        if(last_name_field.getText()==null){
            last_name_field.setText(" ");
        }
        if(phone_number_field.getText()==null){
            phone_number_field.setText(" ");
        }
        contact.setFirst_name(first_name_field.getText());
        contact.setLast_name(last_name_field.getText());
        contact.setPhone_number(phone_number_field.getText());

        
        return  contact;

    }

    public void EditContact(Contact contact){
        first_name_field.setText(contact.getFirst_name());
        last_name_field.setText(contact.getLast_name());
        phone_number_field.setText(contact.getPhone_number());
    }
    public void UpdateContact(Contact contact){
        if(first_name_field.getText()==null){
            first_name_field.setText(" ");
        }
        if(last_name_field.getText()==null){
            last_name_field.setText(" ");
        }
        if(phone_number_field.getText()==null){
            phone_number_field.setText(" ");
        }
        contact.setFirst_name(first_name_field.getText());
        contact.setLast_name(last_name_field.getText());
        contact.setPhone_number(phone_number_field.getText());

    }
}
