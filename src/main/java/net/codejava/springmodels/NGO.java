package net.codejava.springmodels;

public class NGO {

	// foreign key
	private int admin_id;
	private int ngo_id;
	private String ngo_email;
	private String password;
	private String ngo_name;
	private String photo;
	private String description;
	private String website_link;
	private String phone;
	
	public NGO (int admin_id, int ngo_id, String ngo_email, String ngo_password, String ngo_name, String photo, String description, String website_link, String phone) {
		this.admin_id = admin_id;
		this.ngo_id = ngo_id;
		this.ngo_email = ngo_email;
		this.password = ngo_password;
		this.ngo_name = ngo_name;
		this.photo = photo;
		this.description = description;
		this.website_link = website_link;
		this.phone = phone;
	}

	public int getNgo_id() {
		return ngo_id;
	}

	public void setNgo_id(int ngo_id) {
		this.ngo_id = ngo_id;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getNgo_email() {
		return ngo_email;
	}

	public void setNgo_email(String ngo_email) {
		this.ngo_email = ngo_email;
	}

	public String getNgo_name() {
		return ngo_name;
	}

	public void setNgo_name(String ngo_name) {
		this.ngo_name = ngo_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite_link() {
		return website_link;
	}

	public void setWebsite_link(String website_link) {
		this.website_link = website_link;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
