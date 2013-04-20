package net.codejava.springmodels;

public class Urgency {
	private int urgency_id;
	private String urgency_name;
	private String description;

	public int getUrgency_id() {
		return urgency_id;
	}

	public void setUrgency_id(int urgency_id) {
		this.urgency_id = urgency_id;
	}

	public String getUrgency_name() {
		return urgency_name;
	}

	public void setUrgency_name(String urgency_name) {
		this.urgency_name = urgency_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
