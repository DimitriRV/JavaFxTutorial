package person;

import java.io.IOException;

//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

//import javafx.util.Callback;
import util.DateTimeUtil;

public class Controller {

	private ObservableList<Model> personData = FXCollections.observableArrayList();

	@FXML
	private TableView<Model> personTable;

	@FXML
	private TableColumn<Model, String> firstNameColumn;

	@FXML
	private TableColumn<Model, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;

	@FXML
	private Label lastNameLabel;

	@FXML
	private Label streetLabel;

	@FXML
	private Label postalCodeLabel;

	@FXML
	private Label cityLabel;

	@FXML
	private Label birthdayLabel;

	private AnchorPane anchorPane;
	
	private main.Controller mainApp;

	public Controller() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private void initialize() {
		/**
		 * old style without Lambdas
		 */
		// firstNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Model,
		// String>, ObservableValue<String>>() {
		// public ObservableValue<String> call(CellDataFeatures<Model, String> p) {
		// // p.getValue() returns the Person instance for a particular TableView
		// row
		// return p.getValue().firstNameProperty();
		// }
		// });

		/**
		 * new style with Lambdas (better to read)
		 */
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		showPersonDetails(null);

		/**
		 * add Listener function to selectionModel selectedItemProperty. if change
		 * show it new style with Lambdas
		 */
		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldV, newV) -> showPersonDetails(newV));
		
		/**
		 * old style without Lambdas
		 */
		// personTable.getSelectionModel().selectedItemProperty().addListener(new
		// ChangeListener<Model>() {
		// @Override
		// public void changed(ObservableValue<? extends Model> observable, Model
		// oldValue, Model newValue) {
		// // TODO Auto-generated method stub
		// showPersonDetails(newValue);
		// }
		// });
		
		
		personTable.setOnMousePressed(event -> handleTableRowDoublecklicked(event));
		
		personData.add(new person.Model("Hans", "Muster"));
		personData.add(new person.Model("Ruth", "Mueller"));
		personData.add(new person.Model("Heinz", "Kurz"));
		personData.add(new person.Model("Cornelia", "Meier"));
		personData.add(new person.Model("Werner", "Meyer"));
		personData.add(new person.Model("Lydia", "Kunz"));
		personData.add(new person.Model("Anna", "Best"));
		personData.add(new person.Model("Stefan", "Meier"));
		personData.add(new person.Model("Martin", "Mueller"));

		personTable.setItems(personData);
	}

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}
	
	public void setAnchorPane(AnchorPane pane)
	{
		anchorPane = pane;
	}

	public ObservableList<person.Model> getPersonData() {
		return personData;
	}

	public void setPersonData(ObservableList<person.Model> personData) {
		this.personData = personData;
	}

	private void showPersonDetails(Model person) {
		if (person != null) {
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());

			// TODO: We need a way to convert the birthday into a String!
			birthdayLabel.setText(DateTimeUtil.format(person.getBirthday()));
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Selected");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");
			alert.showAndWait();

		}
	}
	
	/**
	 * 
	 * @param event
	 */
	private void handleTableRowDoublecklicked(MouseEvent event)
	{
		if(event.isPrimaryButtonDown() && event.getClickCount() == 2)
		{
			handleEditPerson();
		}
	}
	
	@FXML
	private void handleEditPerson() {
    Model selectedPerson = personTable.getSelectionModel().getSelectedItem();
    if (selectedPerson != null) 
    {
      boolean okClicked = showPersonEditDialog(selectedPerson);
      if (okClicked) 
      {
        showPersonDetails(selectedPerson);
      }
    } 
    else 
    {
        // Nothing selected.
      Alert alert = new Alert(AlertType.WARNING);
      alert.initOwner(mainApp.getPrimaryStage());
      alert.setTitle("No Selection");
      alert.setHeaderText("No Person Selected");
      alert.setContentText("Please select a person in the table.");
      alert.showAndWait();
    }
	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
	    Model tempPerson = new Model();
	    boolean okClicked = showPersonEditDialog(tempPerson);
	    if (okClicked) {
	        personData.add(tempPerson);
	    }
	}

	public boolean showPersonEditDialog(Model p) 
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(person.dialog.Controller.class.getResource("View.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			person.dialog.Controller controller = loader.getController();
			controller.setMainApp(mainApp);
			controller.initLayout(pane);
			controller.setPerson(p);
			controller.show();
			return controller.isOkClicked();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return false;
	}

	public void setMainApp(main.Controller mainApp) {
		this.mainApp = mainApp;
	}	
}