package intf.model;

import javafx.scene.web.WebView;

public class JavascriptExecution {

	public static void exec(String command, WebView webView){
		webView.getEngine().executeScript(command);
	}
	
	
}
