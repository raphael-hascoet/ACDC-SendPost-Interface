package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * This class provides the methods needed to setup our application
 * @author Axel COUDRAY
 */
public class Tools {
	
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
	public void executeCommand(String commande, String Path) {
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
			StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);		
			Executors.newSingleThreadExecutor().submit(streamGobbler);
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Press enter to stop demo");
			System.in.read();
			System.out.println("process destroyed");
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
