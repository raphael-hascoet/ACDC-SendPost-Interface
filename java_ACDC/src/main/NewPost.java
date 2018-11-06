package main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the main class, it allows a person to create a new article
 * @author Axel COUDRAY
 * @date 20/10/2018
 * @version 1.2
 */

public class NewPost {

	/** Method used to verify if the entered date verify the imposed format */
	public static boolean isDateValid(String d) 
	{
		if (d.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) 
			return true;
		else {
			System.out.println("Wrong format");
			return false;
		}	
	}
	
	public static void main(String[] args) {

		System.out.println("########## Creating a new article ##########");
		
		// Variables
		Scanner sc = new Scanner(System.in);
		String title = null;
		String date = null; 
		String categories = null;
		String content = null;
		String author = null;
		
		// Creating the object
		Post p = new Post(title, date, categories, content, author);
		
		/* Asking the user to give the different entries	*/
		// Ask for the author's name
		System.out.println("What is your name ?");
		p.setAuthor(sc.nextLine());
		
		// Give a title
		System.out.println("Please give a title to your article");
		// .trim() : clean the title's " " to avoid problem in the file path
		// .replaceAll(" ", "-") : changes the " " in the title by "-"
		p.setTitle(sc.nextLine().trim().replaceAll(" ", "-"));
		
		// Give a date
		do {
			System.out.println("Now enter the date with the format : YYYY-MM-DD");
			p.setDate(sc.nextLine());
		} while(isDateValid(p.getDate()) == false);
		
		// Give a category
		System.out.println("Please enter the category of this article");
		List<String> cat = p.getCat();
		System.out.println(cat + " or add new");	// prints the different categories that already exists
		p.setCategories(sc.nextLine());
		p.addCategory(p.getCategories()); // if the category written doesn't exists, write the new category in the file categories.txt
		
		// Write content
		System.out.println("Now, you can write your article");
		p.setContent(sc.nextLine());
		
		// Resume
		//System.out.println(p.toString());
		//System.out.println(p.toMarkdown());
		
		// Writing the file in .markdown file
		p.writeFile(p.toMarkdown());
		
		// Build and serve jekyll to generate website with the new article
		try {
			p.seeDemo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Write your commit for GitHub");
		String commit = sc.nextLine();
		try {
			p.commitGit(commit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("What is your GitHub's username ?");
		String username = sc.nextLine();
		System.out.println("What is your GitHub's password ?");
		String password = sc.nextLine();
		try {
			p.pushGit(username, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sc.close();
		System.out.println("END");
	}

}
