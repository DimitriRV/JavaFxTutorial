package person.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DateTimeUtil;

public class Controller 
{
	@FXML
  private TextField firstNameField;
  @FXML
  private TextField lastNameField;
  @FXML
  private TextField streetField;
  @FXML
  private TextField postalCodeField;
  @FXML
  private TextField cityField;
  @FXML
  private TextField birthdayField;
  
  private Stage dialogStage;
  private person.Model person;
  private boolean okClicked = false;
  
  @FXML
  private void initialize() {
  }
  
  /**
   * Sets the stage of this dialog.
   * 
   * @param dialogStage
   */
  public void setDialogStage(Stage dialogStage) {
      this.dialogStage = dialogStage;
  }

	public void setPerson(person.Model person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName());
    lastNameField.setText(person.getLastName());
    streetField.setText(person.getStreet());
    postalCodeField.setText(Integer.toString(person.getPostalCode()));
    cityField.setText(person.getCity());
    birthdayField.setText(DateTimeUtil.format(person.getBirthday()));
    birthdayField.setPromptText("dd.mm.yyyy");
		
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {
		// TODO Auto-generated method stub
		
	}
  
    
}
