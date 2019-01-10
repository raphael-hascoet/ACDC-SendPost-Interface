package intf.controller;

import intf.model.PropertiesAccess;
import intf.view.SettingsWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OkSettingsHandler implements EventHandler<ActionEvent> {
	
	private SettingsWindow settingsWindow;
	
	public OkSettingsHandler(SettingsWindow settingsWindow){
		this.settingsWindow = settingsWindow;
	}

	@Override
	public void handle(ActionEvent event) {
		PropertiesAccess.getInstance().setLocalRepo(settingsWindow.tempRepo.get());
		settingsWindow.close();
	}

}
