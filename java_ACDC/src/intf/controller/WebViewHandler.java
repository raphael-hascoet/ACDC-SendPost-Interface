package intf.controller;

import javafx.scene.web.WebView;

public abstract class WebViewHandler {

	private WebView webview;
	
	public abstract String getHTMLFile();
	
	public void log(String log){
		System.out.println("Log : " + log);
	}

	public WebView getWebview() {
		return webview;
	}

	public void setWebview(WebView webview) {
		this.webview = webview;
	}
	
}
