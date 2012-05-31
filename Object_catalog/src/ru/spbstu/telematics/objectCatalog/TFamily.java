package ru.spbstu.telematics.objectCatalog;

public class TFamily {
	private int familyId;
	private String name;
	private String description;
	public TFamily(int familyId, String name, String description) {
		this.setFamilyId(familyId);
		this.setName(name);
		this.setDescription(description);
	}
	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}
	public int getFamilyId() {
		return familyId;
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
