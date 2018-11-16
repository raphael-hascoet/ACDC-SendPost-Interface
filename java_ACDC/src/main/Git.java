package main;

public class Git {

	// ATTRIBUTES
	private String username;
	private String password; 
	private String localRepository; 
	private String githubRepository;
	
	// METHODS
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLocalRepository() {
		return localRepository;
	}
	
	public void setLocalRepository(String localRepository) {
		this.localRepository = localRepository;
	}
	
	public String getGithubRepository() {
		return githubRepository;
	}
	
	public void setGithubRepository(String githubRepository) {
		this.githubRepository = githubRepository;
	} 
}
