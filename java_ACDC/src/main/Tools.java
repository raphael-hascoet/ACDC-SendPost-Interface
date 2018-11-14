package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * This class provides the methods needed to setup our application
 * @author Axel COUDRAY
 * @date 08/11/2018
 * @version 1.3
 */
public class Tools {
	// Attributes
	private String Path;
	
	// Constructor
	public Tools(String p) {
		this.Path = p;
	}
	
	// Methods
	/**
	 * gives the path
	 * @return path
	 */
	public String getPath() {
		return Path;
	}

	/**
	 * set a new path
	 * @param path
	 */
	public void setPath(String path) {
		Path = path;
	}
	
	/** 
	 * Method to write a .markdown file 
	 */
	public void writeFile(String content, String title, String date) {
		// Writing of the .markdown file's path
		try{
			File f = new File(this.Path + File.separator + "_posts" + File.separator + date + "-" + title + ".markdown"); 
			f.createNewFile();	
			// edition of the file created
			FileWriter fw = new FileWriter(f);
			fw.write(content);
			fw.flush();
			fw.close();
			System.out.println("Your file has been created at the path : \"" + this.Path + "\"");
		} catch(IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	/*
	 * In order to execute a command we need the code below
	 */
	// Consumes an input stream
		private static class StreamGobbler implements Runnable {
		    private InputStream inputStream;
		    private Consumer<String> consumer;
		 
		    public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
		        this.inputStream = inputStream;
		        this.consumer = consumer;
		    }
		 
		    @Override
		    public void run() {
		        new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
		    }
		}
		
	/**
	 * Method to execute a command depending of the OS
	 * @throws InterruptedException 
	 */
	public void executeCommand(String commande) throws InterruptedException {
		final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");	// To verify if the OS is windows or another
		ProcessBuilder builder = new ProcessBuilder();
		try {
			if (isWindows) {
				// if os = windows 
				builder.command("cmd.exe", "/c", commande);	// create a local address to visualize the web site
			} else {
				// else
				builder.command("sh", "-c", commande);	// create a local address to visualize the web site   
			}
			builder.directory(new File(this.Path));		// defines the directory where we should run the commands
			Process process = builder.start();		// start the process
			// prints on the terminal what we get after running the commands
			StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);		
			Executors.newSingleThreadExecutor().submit(streamGobbler);
			TimeUnit.SECONDS.sleep(5);
			System.out.println("Press enter to stop demo");
			System.in.read();
			System.out.println("Demo killed");
			process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to jekyll serve
	 */
	public void seeDemo() {
		try {
			executeCommand("bundle exec jekyll serve -o");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
