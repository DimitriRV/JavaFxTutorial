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
	//beachte sehr wichtig!
	//http://stackoverflow.com/questions/32075725/javafx-trying-to-instantiate-a-stage-inside-a-controller-does-not-retreive-valu
	//->The instance of ControllerA on which you invoke create_ConfigStage() is created by the line
	
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
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(person.Controller.class.getResource("View.fxml"));
			AnchorPane anchorPane = (AnchorPane) loader.load();
			person.Controller controller = loader.getController();
			controller.setMainApp(this);
			rootLayout.setCenter(anchorPane);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<person.Model> getPersonData() {
		return personData;
	}
}