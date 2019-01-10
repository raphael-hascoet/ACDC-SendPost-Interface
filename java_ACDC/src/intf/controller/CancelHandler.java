package intf.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class CancelHandler implements EventHandler<ActionEvent> {

	private Stage window;
	
	public CancelHandler(Stage window) {
		this.window = window;
	}

	@Override
	public void handle(ActionEvent event) {
		window.close();
	}

}
