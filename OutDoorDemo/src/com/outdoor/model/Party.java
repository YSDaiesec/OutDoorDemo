package com.outdoor.model;

public class Party {

	private String partyCatagoryName;
	private String partyName;
	private String partyUserBy = null;
	private String partyPosition;
	private String partyTime;

	public Party(String partyCatagoryName, String partyName,
			String partyPosition, String partyTime) {
		super();
		this.partyName = partyName;
		this.partyCatagoryName = partyCatagoryName;
		this.partyPosition = partyPosition;
		this.partyTime = partyTime;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyCatagoryName() {
		return partyCatagoryName;
	}

	public void setPartyCatagoryName(String partyCatagoryName) {
		this.partyCatagoryName = partyCatagoryName;
	}

	public String getPartyPosition() {
		return partyPosition;
	}

	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
	}

	public String getPartyTime() {
		return partyTime;
	}

	public void setPartyTime(String partyTime) {
		this.partyTime = partyTime;
	}

}
