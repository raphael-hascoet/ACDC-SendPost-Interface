package main;

import java.io.File;
import java.util.List;
/**
 * This class provides the methods needed to setup our application
 * @author Axel COUDRAY
 * @date 15/11/2018
 * @version 1.3
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// CREATION DES OBJETS
		Git gitUser = new Git();
		Tools outil = new Tools();
		
		// définition des variables necessaire à la classe "post"
		String title = null; 
		String date = null;
		String categories = null;
		String content = null; 
		String author = null; 
		Post post = new Post(title, date, categories, content, author);
		List<String> cat = post.getCat();
		
		// DEFINITION DES REPERTOIRES
		gitUser.setLocalRepository("/home/axelc/IMT-A/developpement/project-ACDC/BLOG/");
		gitUser.setGithubRepository("");
		/*
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
		post.setCategories(categories);
		
		System.out.println("Contenu de la publication");
		content = outil.getUserInput();
		post.setContent(content);

		// ECRIRE LE FICHIER .MARKDOWN
		String markdownFilePath = gitUser.getLocalRepository() + "_posts" + File.separator;
		outil.writeFile(post.toMarkdown(), post.getTitle(), post.getDate(), markdownFilePath);
		*/
		// AFFICHAGE DE LA DEMO
		outil.executeCommand("bundle exec jekyll serve -o", gitUser.getLocalRepository());
		
		
	}

}
