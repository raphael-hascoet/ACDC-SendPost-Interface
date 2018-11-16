package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * This class represent an object 'Post' that has different entries and that describes an article
 * This article must have : a title, a date (YYYY-MM-DD), a category, and content
 * @author Axel COUDRAY
 * @date 20/10/2018
 */
public class Post {
	// Attributes
	private String title;
	private String date;
	private String categories;
	private String content; 
	private String author; 
	
	// Constructor
	public Post(String t, String d, String cat, String cont, String aut) {
		this.title = t;
		this.date = d;
		this.categories = cat;
		this.content = cont;
		this.author = aut;
	}

	
	// Methods
	/**
	 * gives the title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * set a new title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * gives the date
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * set a new date
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * gives the category
	 * @return categories
	 */
	public String getCategories() {
		return categories;
	}

	/**
	 * set a new category
	 * @param categories
	 */
	public void setCategories(String categories) {
		this.categories = categories;
	}

	/**
	 * gives the content
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * set a new content
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * gives the author
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * set a new author
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/** Method to add a category to a .txt File */
	public void addCategory(String c) {
		//String homeDirectory = System.getProperty("user.home");
		List<String> cat = this.getCat();
		if(cat.contains(c) == false) {
			try
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter(".." + /*homeDirectory +*/ File.separator + "BLOG" + File.separator + "categories.txt", true));
			    bw.append(c+"\n");
			    bw.close();
			}
			catch (Exception e)
			{
			    e.printStackTrace();
			}	
		}
	}
	
	/** Method to read the categories in the file categories.txt */
	public List<String> getCat() {
		//String homeDirectory = System.getProperty("user.home");
		List<String> cat = new ArrayList<String>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(".." + /*homeDirectory +*/ File.separator + "BLOG" + File.separator + "categories.txt"));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		    	cat.add(line);
		    }
		    reader.close();
		    return cat;
		  }
		  catch (Exception e)
		  {  
		  	e.printStackTrace();
		  	return null;
		  }
	}
	
	/** Method to write the content of the .markdown file */
	public String toMarkdown() {
		return "--- \nlayout: post \ntitle: \"" + this.title + "\" \ndate: " + this.date + " \ncategories: " + this.categories + "\nauthor: " + this.author + " \n---" + "\n" + this.content;
	}
}
