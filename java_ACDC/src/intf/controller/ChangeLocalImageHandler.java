package intf.controller;

import java.io.File;

import intf.view.AddImageWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

public class ChangeLocalImageHandler implements EventHandler<ActionEvent>{

	private AddImageWindow addImageWindow;

	public ChangeLocalImageHandler(AddImageWindow addImageWindow) {
		this.addImageWindow = addImageWindow;
	}

	public void handle(ActionEvent arg0) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png")
				);
		File selectedImageFile = fileChooser.showOpenDialog(addImageWindow);

		if(selectedImageFile != null){
			addImageWindow.setLocalImage(selectedImageFile.getAbsolutePath());
		}
	}

}
