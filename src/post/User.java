package post;

public class User {
	private String name;

	User(String name) {
		setName(name);
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}