package intf.model;

import com.sun.javafx.webkit.WebConsoleListener;

import intf.controller.WebViewHandler;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class WebViewGenerator {

	public static WebView generate(WebViewHandler webviewHandler){
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
						
		webEngine.load(ResourcesAccess.getInstance().getWebView(webviewHandler.getHTMLFile()).toString());			
		JSObject window = (JSObject) webEngine.executeScript("window");
		window.setMember("app", webviewHandler);
		
		WebConsoleListener.setDefaultListener(new WebConsoleListener(){
		    @Override
		    public void messageAdded(WebView webView, String message, int lineNumber, String sourceId) {
		        System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message);
		    }
		});
		
		webviewHandler.setWebview(browser);
		
		return browser;
	}
	
}
