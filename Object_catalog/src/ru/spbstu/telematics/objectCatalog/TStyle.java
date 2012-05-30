package ru.spbstu.telematics.objectCatalog;

public class TStyle {
	private int styleId;
	private String family;
	private boolean isMandatory;
	private boolean isMultiple;
	public TStyle(int styleId, String family, boolean isMandatory, boolean isMultiple) {
		this.setStyleId(styleId);
		this.setFamily(family);
		this.setMandatory(isMandatory);
		this.setMultiple(isMultiple);
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}
	public int getStyleId() {
		return styleId;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getFamily() {
		return family;
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
	
}
