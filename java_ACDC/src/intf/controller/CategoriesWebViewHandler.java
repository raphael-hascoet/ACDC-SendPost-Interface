package intf.controller;

import com.google.gson.Gson;

import intf.Main;
import intf.model.JSONTranslator;
import intf.model.JavascriptExecution;
import intf.view.CategoriesWindow;
import intf.view.MainWindow;

public class CategoriesWebViewHandler extends WebViewHandler {
	
	private CategoriesWindow categoriesWindow;
	
	public CategoriesWebViewHandler(CategoriesWindow categoriesWindow) {
		this.categoriesWindow = categoriesWindow;
	}
	
	public String getHTMLFile(){
		return "categories.html";
	}
	
	public String getCategories(){
		Gson gson = new Gson();
		return gson.toJson(Main.tools.getCategory());
	}
	
	public void sendCategories(String categoriesJSON){
		Main.tools.setCategories(JSONTranslator.getStringListFromJSON(categoriesJSON));
		
		categoriesWindow.close();
		
		MainWindow mainWindow = (MainWindow) categoriesWindow.getOwner();
		JavascriptExecution.exec("updateCategories()", mainWindow.getWebViewHandler().getWebview());
	}
	
	public void cancel(){
		categoriesWindow.close();
	}
	
}
