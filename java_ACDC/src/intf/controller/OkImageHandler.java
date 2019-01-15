package intf.controller;

import intf.view.AddImageWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OkImageHandler implements EventHandler<ActionEvent>{
	
	private AddImageWindow addImageWindow;
	
	public OkImageHandler(AddImageWindow addImageWindow) {
		this.addImageWindow = addImageWindow;
	}

	@Override
	public void handle(ActionEvent event) {
		addImageWindow.close();
	}

}
