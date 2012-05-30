package ru.spbstu.telematics.objectCatalog;

public class TObjectValue {
	private int id;
	private String value;
	private boolean isMandatory;
	private boolean isMultiple;
	private String objectName;
	
	public TObjectValue(int id, String value, boolean isMultiple, boolean isMandatory, String objectName) {
		setId(id);
		setValue(value);
		setMultiple(isMultiple);
		setMandatory(isMandatory);
		setObjectName(objectName);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMultiple(boolean isMultiple) {
		this.isMultiple = isMultiple;
	}

	public boolean isMultiple() {
		return isMultiple;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectName() {
		return objectName;
	}
}
