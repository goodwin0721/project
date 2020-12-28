package pers.goodwin.shopSystem.model;

//@Controller("user")
//@Scope("prototype")
public class User {
	private int id;
	private String username;
	private String password;
	private String birthdate;
	private int gender;// 0男，1女

	public User() {
		super();
	}

	public User(int id, String username, String password, String birthdate, int gender) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthdate = birthdate;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", birthdate=" + birthdate
				+ ", gender=" + gender + "]";
	}

}
