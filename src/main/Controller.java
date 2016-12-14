package main;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller extends Application {
	//TUTORIAL FROM  http://code.makery.ch/library/javafx-8-tutorial/part1/
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
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
			//Load person overview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Controller.class.getResource("../person/View.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			rootLayout.setCenter(personOverview);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
