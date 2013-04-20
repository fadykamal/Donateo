package net.codejava.springmodels;

public class Admins 
{
	private int	admin_id;
	private String admin_email;
	private String password;
	
	public Admins(int admin_id, String admin_email, String admin_password) {
		this.admin_id = admin_id;
		this.admin_email = admin_email;
		this.password = admin_password;
	}
	
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
