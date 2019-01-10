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
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * This class provides the methods needed to setup our application
 * @author Axel COUDRAY
 * @date 17/11/2018
 */
public class Tools {
	
	private String localRepository; 
		
	public Tools(String localRepo) {
		this.localRepository = localRepo;
	}
	
	/**
	 * Get the local repository 
	 * @return localRepository - String of the repository's path
	 */
	public String getLocalRepository() {
		return localRepository;
	}
	
	/**
	 * Set a new local repository
	 * @param localRepository - String of the repository's path
	 */
	public void setLocalRepository(String localRepository) {
		this.localRepository = localRepository;
	}
	
	/**
	 * Method to get the user input (Only Strings)
	 * @return userInput (String)
	 * @throws IOException 
	 */
	public String getUserInput() {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String userInput = null;
		try {
			userInput = r.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInput;
	}
	
	/** 
	 * Method to write a .markdown file 
	 * @param content - String of the content to write
	 * @param title - String of the title 
	 * @param date - String of the date
	 */
	public void writeFile(String content, String title, String date, String Path) {
		try{
			File f = new File(Path + date + "-" + title + ".markdown"); 
			f.createNewFile();	
			FileWriter fw = new FileWriter(f);
			fw.write(content);
			fw.flush();
			fw.close();
			System.out.println("Your file has been created at the path : \"" + Path + "\"");
		} catch(IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	public void setCategories(List<String> categories){
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.localRepository + File.separator + "categories.txt", false));
			for(String category: categories)
				bw.append(category+"\n");
		    bw.close();
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}	
	}
	/** 
	 * Method to add a category to a .txt File 
	 * @param c - String of the new category to add
	 */
	public void addCategory(String c) {
		List<String> cat = this.getCategory();
		if(cat.contains(c) == false) {
			try
			{
				// Uncomment the next line if you want to try without doing .jar file
				//BufferedWriter bw = new BufferedWriter(new FileWriter(".." + File.separator + "BLOG" + File.separator + "categories.txt", true));
				
				// Comment the next line if you want to try without doing .jar file
				BufferedWriter bw = new BufferedWriter(new FileWriter(this.localRepository + File.separator + "categories.txt", true));
				bw.append(c+"\n");
			    bw.close();
			}
			catch (Exception e)
			{
			    e.printStackTrace();
			}	
		}
	}
	
	/** 
	 * Method to read the categories in the file categories.txt 
	 * @return cat - list of the different categories
	 */
	public List<String> getCategory() {
		List<String> cat = new ArrayList<String>();
		try
		{
			// Uncomment the next line if you want to try without doing .jar file
			// BufferedReader reader = new BufferedReader(new FileReader(".." + File.separator + "BLOG" + File.separator + "categories.txt"));
			
			// Comment the next line if you want to try without doing .jar file
			BufferedReader reader = new BufferedReader(new FileReader(this.localRepository + File.separator + "categories.txt"));
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
	
	/*
	 * In order to execute a command we need the code below
	 */
	// Consumes an input stream
	private static class StreamGobbler implements Runnable {
	    private InputStream inputStream;
	    private InputStream errorStream;
	    private Consumer<String> consumer;
	    public StreamGobbler(InputStream inputStream, Consumer<String> consumer, InputStream errorStream) {
	        this.inputStream = inputStream;
	        this.consumer = consumer;
	        this.errorStream = errorStream;
	    }		 
	    @Override
	    public void run() {
	        new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
	    }
	}
		
	/**
	 * Method to execute a command depending of the OS
	 * @param commande - String of the command to execute
	 * @param path - String of the path where the command should be executed
	 * @param stopThread - Boolean used to know if we need to stop the process by ourself or not 
	 */
	public void executeCommand(String commande, String Path, boolean stopThread) {
		final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");	// To verify if the OS is windows or another
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
				// if os = windows 
			builder.command("cmd.exe", "/c", commande);	
		} else {
			// else
			builder.command("sh", "-c", commande);
		}
		builder.directory(new File(Path));
		try {
			Process process = builder.start();
			StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println, process.getErrorStream());		
			Executors.newSingleThreadExecutor().submit(streamGobbler);
			TimeUnit.SECONDS.sleep(3);
			if(stopThread == true) {
				/*System.out.println("Press enter to stop demo");
				System.in.read();
				System.out.println("process destroyed");
				process.destroy();*/
			} else {
				int exitCode = process.waitFor();
				assert exitCode == 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method using executeCommand() in order to execute jekyll serve -o
	 */
	public void execJekyll() {
		this.executeCommand("bundle exec jekyll serve -o", this.getLocalRepository(), true);
	}
	
	/**
	 * 
	 */
	public void gitPush() {
		this.executeCommand("git add .", this.getLocalRepository(), false);
		this.executeCommand("git commit -m \"new article\"", this.getLocalRepository(), false);
		this.executeCommand("git push", this.getLocalRepository(), false);
	}

	public void killJekyllProcess() {
		this.executeCommand("taskkill /IM \"ruby.exe\" /F", System.getProperty("user.dir"), false);
	}

}
