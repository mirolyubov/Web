package ru.spbstu.telematics.objectCatalog;

public class TObject {
	private int id;
	private String name;
	private String className;
	
	public TObject(int id, String name, String className) {
		setId(id);
		setName(name);
		setClassName(className);
	}

	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassName() {
		return className;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
