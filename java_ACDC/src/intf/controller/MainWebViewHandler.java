package intf.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import com.google.gson.Gson;

import intf.Main;
import intf.model.JSONTranslator;
import intf.view.AddImageWindow;
import intf.view.AlertDisplay;
import intf.view.CategoriesWindow;
import intf.view.MainWindow;
import intf.view.SettingsWindow;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import main.Post;

public class MainWebViewHandler extends WebViewHandler {

	private MainWindow mainWindow;
		
	public MainWebViewHandler(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public String getHTMLFile(){
		return "text_area.html";
	}
	
	public void openSettings(){
		SettingsWindow setWindow = new SettingsWindow();
		setWindow.initModality(Modality.WINDOW_MODAL);
		setWindow.initOwner(mainWindow);
		setWindow.show();
	}
	
	public String getCategories(){
		Gson gson = new Gson();
		return gson.toJson(Main.tools.getCategory());
	}
	
	
	public void manageCategories(){
		CategoriesWindow categoriesWindow = new CategoriesWindow();

		categoriesWindow.initModality(Modality.WINDOW_MODAL);
		categoriesWindow.initOwner(mainWindow);

		categoriesWindow.show();
	}
	
	public String getImageUrl(){
		/*AddImageWindow addImageWindow = new AddImageWindow();
		
		addImageWindow.initModality(Modality.WINDOW_MODAL);
		addImageWindow.initOwner(mainWindow);
		
		addImageWindow.showAndWait();
		
		return addImageWindow.getCurrentImage();*/
		return AlertDisplay.enterImageUrl(mainWindow).get();
	}
	
	public void openLink(String href){
		System.out.println(href);
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
		    try {
				Desktop.getDesktop().browse(new URI(href));
			} catch (IOException e) {
				System.out.println("Couldn't open link: " + href);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		System.out.println("out");
	}
	
	public void sendPost(String postJSON){		
		Post post = JSONTranslator.getPostFromJSON(postJSON);
		
		String markdownFilePath = Main.tools.getLocalRepository() + File.separator + "_posts" + File.separator;
		Main.tools.writeFile(post.toMarkdown(), post.getTitle(), post.getDate(), markdownFilePath);
		
		Optional<ButtonType> result = AlertDisplay.confirmPreview(mainWindow);
		System.out.println("Oui");
		if(result.get() == ButtonType.OK){		
			Main.tools.execJekyll();
			AlertDisplay.previewDisplay(mainWindow);
			Main.tools.killJekyllProcess();
		}
		
		result = AlertDisplay.confirmGitPost(mainWindow);
		if(result.get() == ButtonType.OK){
			Main.tools.gitPush();
			AlertDisplay.postSentToGit(mainWindow);
		}
	}
	
	public void exit(){
		System.exit(0);
	}
	
}
