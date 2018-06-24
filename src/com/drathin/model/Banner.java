package com.drathin.model;

public class Banner {
	private int bannerid;
	private String image;
	private int state;
	public Banner() {
		super();
	}
	public Banner(int bannerid, String image, int state) {
		super();
		this.bannerid = bannerid;
		this.image = image;
		this.state = state;
	}
	public int getBanarid() {
		return bannerid;
	}
	public void setBannerid(int bannerid) {
		this.bannerid = bannerid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
