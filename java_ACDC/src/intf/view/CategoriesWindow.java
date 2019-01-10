package intf.view;

import intf.controller.CategoriesWebViewHandler;
import intf.model.WebViewGenerator;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class CategoriesWindow extends Stage {
		
	private CategoriesWebViewHandler webviewHandler = new CategoriesWebViewHandler(this);
	
	public CategoriesWindow(){
		this.setTitle("Manage categories");

		WebView browser = WebViewGenerator.generate(webviewHandler);
		browser.setPrefSize(400, 390);
		
		
		Scene scene = new Scene(browser);
		this.setScene(scene);
		this.sizeToScene();
	}

}
