package main;

import java.io.File;
import java.util.List;
/**
 * This is the main class
 * @author Axel COUDRAY
 * @date 17/11/2018
 * @version 1.4
 */
public class Main {

	public static void main(String[] args) {
		
		// CREATION DES OBJETS
		// Uncomment the next line if you want to try without doing .jar file
		// Change the name of the path so the program can acces to your '/project-ACDC/BLOG/'
		Tools outil = new Tools("D:/Main/IMT/A1/AC_DC/workspace/Projet/web-master/BLOG");
		
		// Comment the two next lines to try without doing .jar file
		//String currentDirectory = System.getProperty("user.dir");
		//Tools outil = new Tools(currentDirectory + File.separator + "BLOG" + File.separator);

		// définition des variables necessaire à la classe "post"
		String title = null; 
		String date = null;
		String categories = null;
		String content = null; 
		String author = null; 
		Post post = new Post(title, date, categories, content, author);
		List<String> cat = outil.getCategory();
		
		// DEMANDE D'INFORMATIONS A L'UTILISATEUR
		System.out.println("Auteur de la publication");
		author = outil.getUserInput();
		post.setAuthor(author);
		
		System.out.println("Titre de la publication");
		title = outil.getUserInput().trim().replaceAll(" ", "-");	// .trim().replaceAll(" ", "-") pour éviter les erreurs de mise en forme
		post.setTitle(title);
		
		System.out.println("Date de la publication (de la forme YYYY-MM-DD)");
		date = outil.getUserInput();
		post.setDate(date);
		
		System.out.println("Catégorie de la publication :\n" + cat + " ou ajouter une catégorie");
		categories = outil.getUserInput();
		outil.addCategory(categories);
		post.setCategories(categories);
		
		System.out.println("Contenu de la publication");
		content = outil.getUserInput();
		post.setContent(content);

		// ECRIRE LE FICHIER .MARKDOWN
		String markdownFilePath = outil.getLocalRepository() + "_posts" + File.separator;
		outil.writeFile(post.toMarkdown(), post.getTitle(), post.getDate(), markdownFilePath);
		
		// AFFICHAGE DE LA DEMO
		outil.execJekyll();
		
		// GIT PUSH THE BLOG FILE 
		outil.gitPush();
		
		// ENDING THE PROGRAM
		System.exit(0);
	}

}
