package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	/* Getters & setters */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/** Method to add a category to a .txt File */
	public void addCategory(String c) {
		List<String> cat = this.getCat();
		if(cat.contains(c) == false) {
			try
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter(".." + File.separator + "BLOG" + File.separator + "categories.txt", true));
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
		List<String> cat = new ArrayList<String>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(".." + File.separator + "BLOG" + File.separator + "categories.txt"));
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
	
	/** Method to write a .markdown file */
	public void writeFile(String content) {
		// Writing of the .markdown file's path
		String PATH = ".." + File.separator + "BLOG" + File.separator + "_posts" + File.separator + this.date + "-" + this.title + ".markdown";
		try{
			File f = new File(PATH); 
			f.createNewFile();	
			// edition of the file created
			FileWriter fw = new FileWriter(f);
			fw.write(content);
			fw.flush();
			fw.close();
			System.out.println("Your file has been created at the path : \"" + PATH + "\"");
		} catch(IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	/** Method to build and serve the site with jekyll */
	public void seeDemo() {
		String[] commands = {"bundle exec jekyll build", "bundle exec jekyll serve"};
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec(commands);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Method to push the files on the git */ 
	public void pushGit() {
		
	}
	
	/* toString */
	public String toString() {
		return "News [title=" + title + ", date=" + date + ", categories=" + categories + ", content=" + content + "]";
	}
}
