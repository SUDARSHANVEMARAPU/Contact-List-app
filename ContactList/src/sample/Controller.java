package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import sample.Contacts.Contact;
import sample.Contacts.ContactData;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private TextField search_field;
    @FXML
    private Button search_button;
    @FXML
    private BorderPane border_pane;
    @FXML
    private TableView<Contact> table_view;



    public void initialize(){

        ContactData.getInstance().loadContacts();
        table_view.setItems(ContactData.getInstance().getContacts());
        table_view.getSelectionModel().selectFirst();
        table_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);




    }


    /**
     * This method is an event handler for edit menu item in context menu declared in initialize method
     * When this method is called, it opens a dialogue, for editing selected contact
     * This loads scene graph from addNewContactDialogue.fxml and uses method in Dialogue controller to create a new Contact item
     * deletes the selected contact and adds this new contact.
     * */
    public void ShowAddDialogue(){
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setTitle("Add Contact");
        dialog.initOwner(border_pane.getScene().getWindow()); // 1
        FXMLLoader fxmlLoader=new FXMLLoader();

        fxmlLoader.setLocation(getClass().getResource("addNewContactDialogue.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }


        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result= dialog.showAndWait();
        if(result.isPresent() && result.get().equals(ButtonType.OK)){
            DialogueController controller=fxmlLoader.getController(); //
            ContactData.getInstance().addContact(controller.AddNewContact());
            ContactData.getInstance().saveContacts();
        }

    }
    public void handleDeleteKey(KeyEvent keyEvent){
        if(keyEvent.getCode().equals(KeyCode.DELETE)){
            DeleteContact();
        }

    }


    public void DeleteContact(){
        Contact selectedContact=table_view.getSelectionModel().getSelectedItem();
        if(selectedContact==null){
            Alert warning=new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Warning");
            warning.setContentText("Please select a contact");
            warning.getButtonTypes().remove(ButtonType.OK);
            warning.getButtonTypes().add(ButtonType.CLOSE);
            warning.showAndWait();
        }else {
            Alert confirmation=new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Delete");
            confirmation.setContentText("Click ok to delete\n"+selectedContact.getFirst_name()+" "+selectedContact.getLast_name());
            Optional<ButtonType> result=confirmation.showAndWait();
            if(result.isPresent() && result.get().equals(ButtonType.OK)){
                ContactData.getInstance().deleteContact(selectedContact);
                ContactData.getInstance().saveContacts();
            }
        }

    }

    public void EditContact(){
        Contact selectedContact=table_view.getSelectionModel().getSelectedItem();
        if(selectedContact==null){
            Alert warning=new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Warning");
            warning.setContentText("Please select a contact");
            warning.getButtonTypes().add(ButtonType.CLOSE);
            warning.showAndWait();
        }else{
            Dialog<ButtonType> editDialog=new Dialog<>();
            editDialog.setTitle("Edit Contact");
            FXMLLoader fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addNewContactDialogue.fxml"));
            try {
                editDialog.getDialogPane().setContent(fxmlLoader.load());
            } catch(IOException e) {
                e.printStackTrace();
                return;
            }
            editDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            editDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            DialogueController controller=fxmlLoader.getController(); //
            controller.EditContact(selectedContact);
            editDialog.initOwner(border_pane.getScene().getWindow());
            Optional<ButtonType> result=editDialog.showAndWait();
            if(result.isPresent() && result.get().equals(ButtonType.OK)){
                controller.UpdateContact(selectedContact);
                ContactData.getInstance().saveContacts();
            }
        }
    }
    public void SearchContact(){
        ObservableList<Contact> searchList= FXCollections.observableArrayList();
        table_view.setItems(searchList);
        if(!search_field.getText().isEmpty() || !search_field.getText().trim().isEmpty()) {
            for (Contact c : ContactData.getInstance().getContacts()) {
                if (c.getFirst_name().equals(search_field.getText())) {
                    searchList.add(c);
                } else if (c.getLast_name().equals(search_field.getText())) {
                    searchList.add(c);
                } else if (c.getPhone_number().equals(search_field.getText())) {
                    searchList.add(c);
                }
            }
        }else{
            searchList.addAll(ContactData.getInstance().getContacts());
        }
        table_view.setItems(searchList);

    }
    public void Refill(){
        table_view.setItems(ContactData.getInstance().getContacts());
    }


    /**
     * This method is declared for MenuBar item exit
     **/
    @FXML
    public void handleExit(){
        Platform.exit();
    }



}
