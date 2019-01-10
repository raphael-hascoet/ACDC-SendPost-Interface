package intf.view;

import intf.controller.MainWebViewHandler;
import intf.model.WebViewGenerator;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MainWindow extends Stage {
	
	private WebView browser;
	
	private MainWebViewHandler webviewHandler = new MainWebViewHandler(this);

	public MainWindow(){
		this.setTitle("Send a post");
		BorderPane root = new BorderPane();
		
		//root.setTop(createTop());
		
		root.setCenter(createWebView());
		
		Scene scene = new Scene(root);
		this.setScene(scene);
		this.sizeToScene();
		
	}
	
	private Node createWebView(){
		browser = WebViewGenerator.generate(webviewHandler);
		
		return browser;
	}
	
	public MainWebViewHandler getWebViewHandler() {
		return webviewHandler;
	}
	
}
