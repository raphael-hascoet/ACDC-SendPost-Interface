package intf;

import intf.model.PropertiesAccess;
import intf.view.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import main.Tools;

public class Main extends Application{
			
	public static Tools tools = new Tools(PropertiesAccess.getInstance().getLocalRepo());
	
	public void start(Stage primaryStage){
		primaryStage = new MainWindow();
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
