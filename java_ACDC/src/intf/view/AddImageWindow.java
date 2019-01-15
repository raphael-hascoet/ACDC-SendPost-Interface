package intf.view;

import java.io.File;
import java.util.HashMap;

import intf.controller.CancelHandler;
import intf.controller.ChangeLocalImageHandler;
import intf.controller.ImageType;
import intf.controller.OkImageHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddImageWindow extends Stage {
	
	private TextField localImageField;

	private TextField URLImageField;
	
	private ToggleGroup imageTypeGroup;
	
	private HashMap<ImageType, Node> imageNodes;
	
	private ImageType currentImageType;

	public AddImageWindow(){

		imageNodes= new HashMap<>();
		
		this.setTitle("Manage categories");

		VBox root = new VBox();

		imageTypeGroup = new ToggleGroup();

		imageTypeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if(old_toggle != null){
					ImageType oldImageType = (ImageType) old_toggle.getUserData();
					ImageType newImageType = (ImageType) new_toggle.getUserData();
					imageNodes.get(oldImageType).setDisable(true);
					imageNodes.get(newImageType).setDisable(false);
					
					currentImageType = newImageType;
				}
			}

		});

		HBox buttons = new HBox();

		Button buttOk = new Button("OK");
		buttOk.setPrefWidth(80);
		buttOk.setOnAction(new OkImageHandler(this));

		Button buttCancel = new Button("Cancel");
		buttCancel.setPrefWidth(80);
		buttCancel.setOnAction(new CancelHandler(this));

		buttons.getChildren().addAll(buttOk, buttCancel);

		buttons.setSpacing(5);
		buttons.setAlignment(Pos.BOTTOM_RIGHT);

		root.getChildren().addAll(createLocalImagePart(), createURLImagePart(), buttons);

		root.setPadding(new Insets(10));
		root.setSpacing(25);

		Scene scene = new Scene(root);
		this.setScene(scene);

		this.setMinWidth(300);

		this.sizeToScene();
	}

	private Node createLocalImagePart(){

		RadioButton localImageRB = new RadioButton("Local image");
		localImageRB.setToggleGroup(imageTypeGroup);
		localImageRB.setSelected(true);

		Label localImageLbl = new Label("Image path :");
		localImageLbl.setStyle("-fx-font-weight: bold;");

		localImageField = new TextField();
		localImageField.setMinWidth(350);

		Button localImageButt = new Button("Change image path");
		localImageButt.setOnAction(new ChangeLocalImageHandler(this));

		VBox localImageNodes = new VBox();
		localImageNodes.getChildren().addAll(localImageLbl, localImageField, localImageButt);

		localImageRB.setUserData(ImageType.LOCAL);
		
		imageNodes.put(ImageType.LOCAL, localImageNodes);

		VBox localImagePart = new VBox();
		localImagePart.setSpacing(10);
		localImagePart.getChildren().addAll(localImageRB, localImageNodes);
		
		currentImageType = ImageType.LOCAL;

		return localImagePart;
	}

	private Node createURLImagePart(){

		RadioButton fromURLImageRB = new RadioButton("Image from URL:");
		fromURLImageRB.setToggleGroup(imageTypeGroup);

		Label URLImageLbl = new Label("Image URL :");
		URLImageLbl.setStyle("-fx-font-weight: bold;");

		TextField URLImageField = new TextField();
		URLImageField.setMinWidth(350);

		VBox URLImageNodes = new VBox();
		URLImageNodes.getChildren().addAll(URLImageLbl, URLImageField);
		URLImageNodes.setDisable(true);

		fromURLImageRB.setUserData(ImageType.FROM_URL);
		
		imageNodes.put(ImageType.FROM_URL, URLImageNodes);

		VBox URLImagePart = new VBox();
		URLImagePart.setSpacing(10);
		URLImagePart.getChildren().addAll(fromURLImageRB, URLImageNodes);

		return URLImagePart;
	}
	
	public void setLocalImage(String localImage){
		localImageField.setText(localImage);
	}
	
	public String getLocalImage(){
		return localImageField.getText();
	}
	
	public ImageType getCurrentImageType(){
		return currentImageType;
	}

	public String getCurrentImage(){
		switch(currentImageType){
		case LOCAL:
			return localImageField.getText();
		case FROM_URL:
			if(new File(URLImageField.getText()).exists())
				return URLImageField.getText();
			else
				throw new Error();
		}
		return "";
	}
}
