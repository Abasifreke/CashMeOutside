package bean;

import java.sql.Timestamp;

public class Client {

	private int userid;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String type;
	private Timestamp last_time;
	private int isActive;

	public Client() {

	}

	public Client(int userid, String username, String password, String fname, String lname, String type, int isActive) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.type = type;
		this.isActive = isActive;
	}

	public Client(int userid, String username, String fname, String lname, String type) {
		this.userid = userid;
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.type = type;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getLast_time() {
		return last_time;
	}

	public void setLast_time(Timestamp last_time) {
		this.last_time = last_time;
	}

	public int isActive() {
		return isActive;
	}

	public void setActive(int isActive) {
		this.isActive = isActive;
	}

}
