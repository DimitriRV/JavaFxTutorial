package person;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class Controller {
	
	private ObservableList<person.Model> personData = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Model> personTable;
	
	@FXML
	private TableColumn<Model,String> firstNameColumn;
	
	@FXML
	private TableColumn<Model,String> lastNameColumn;
	
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
	
	
	public Controller() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	private void initialize()
	{
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		
		personData.add(new person.Model("Hans","Muster"));
		personData.add(new person.Model("Ruth","Mueller"));
		personData.add(new person.Model("Heinz","Kurz"));
		personData.add(new person.Model("Cornelia","Meier"));
		personData.add(new person.Model("Werner","Meyer"));
		personData.add(new person.Model("Lydia","Kunz"));
		personData.add(new person.Model("Anna","Best"));
		personData.add(new person.Model("Stefan","Meier"));
		personData.add(new person.Model("Martin","Mueller"));
		personTable.setItems(personData);
	}

	public void initLayout()
	{
		try {
			//Load person overview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("View.fxml"));
			anchorPane = (AnchorPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AnchorPane getAnchorPane()
	{
		return anchorPane;
	}

	public ObservableList<person.Model> getPersonData() {
		return personData;
	}

	public void setPersonData(ObservableList<person.Model> personData) {
		this.personData = personData;
	}
}
