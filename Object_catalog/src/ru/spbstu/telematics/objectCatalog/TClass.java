package ru.spbstu.telematics.objectCatalog;

public class TClass {
	private int id;
	private String name;
	private String description;
	public TClass(int id, String name, String description) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
}
