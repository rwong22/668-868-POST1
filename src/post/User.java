package post;

/**
 * Abstract class to add general function
 * to all system users.
 *
 */
public abstract class User {

	private String name;

	public User(String name) {
		this.name = name;
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
