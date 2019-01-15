package intf.controller;

import java.io.File;

import intf.view.SettingsWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;

public class ChangeRepoHandler implements EventHandler<ActionEvent> {
	
	private SettingsWindow settingsWindow;
	
	public ChangeRepoHandler(SettingsWindow settingsWindow) {
		this.settingsWindow = settingsWindow;
	}

	@Override
	public void handle(ActionEvent arg0) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(settingsWindow);
		
		if(selectedDirectory != null){
			settingsWindow.setRepoFieldValue(selectedDirectory.getAbsolutePath());
		}
	}
	
	
	
}
