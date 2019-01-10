package intf.view;

import intf.controller.CancelHandler;
import intf.controller.ChangeRepoHandler;
import intf.controller.OkSettingsHandler;
import intf.model.PropertiesAccess;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsWindow extends Stage {
	
	public StringProperty tempRepo;
	
	public SettingsWindow(){
		tempRepo = new SimpleStringProperty(PropertiesAccess.getInstance().getLocalRepo());
		
		this.setTitle("Settings");
		
		VBox root = new VBox();
		
		Label lblRepo = new Label("Current local repository :");
		lblRepo.setStyle("-fx-font-weight: bold;");
		
		Label lblCurrRepo = new Label(tempRepo.get());
		lblCurrRepo.textProperty().bind(tempRepo);
		
		Button buttRepo = new Button("Change local repository");
		buttRepo.setOnAction(new ChangeRepoHandler(this));
		
		HBox buttons = new HBox();
		
		Button buttOk = new Button("OK");
		buttOk.setPrefWidth(80);
		buttOk.setOnAction(new OkSettingsHandler(this));
		
		Button buttCancel = new Button("Cancel");
		buttCancel.setPrefWidth(80);
		buttCancel.setOnAction(new CancelHandler(this));
		
		buttons.getChildren().addAll(buttOk, buttCancel);
		
		buttons.setSpacing(5);
		buttons.setAlignment(Pos.BOTTOM_RIGHT);
		
		root.getChildren().addAll(lblRepo, lblCurrRepo, buttRepo, buttons);

		root.setPadding(new Insets(10));
		root.setSpacing(5);
		
		Scene scene = new Scene(root);
		this.setScene(scene);
		
		this.setMinWidth(300);
		
		this.sizeToScene();
	}
	
}
