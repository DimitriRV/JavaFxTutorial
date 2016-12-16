package main;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller extends Application {
	//TUTORIAL FROM  http://code.makery.ch/library/javafx-8-tutorial/part1/
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<person.Model> personData;
	
	
	
	public Controller() {
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TimeTrackFx");
		initRootLayout();
		showPersonOverview();
	}

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void initRootLayout()
	{
		try
		{
			//Load rootLayout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("View.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//Show the scene containing the root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		}catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public void showPersonOverview()
	{
		person.Controller controller = new person.Controller();
		controller.setMainApp(this);
		controller.initLayout();
		AnchorPane personOverview = controller.getAnchorPane();
		rootLayout.setCenter(personOverview);
		personData = controller.getPersonData();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<person.Model> getPersonData() {
		return personData;
	}
}