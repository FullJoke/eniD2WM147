package fr.eni.eniD2WM147.bo;

public class Image {

	private String picture;
	private int noArt;

	
	
	public Image(String picture) {
		super();
		this.picture = picture;
	}

	public Image(String picture, int noArt) {
		super();
		this.picture = picture;
		this.setId(noArt);
	}
	public Image() {
		
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getId() {
		return noArt;
	}

	public void setId(int id) {
		this.noArt = id;
	}

	@Override
	public String toString() {
		return "Image [picture=" + picture + ", noArt=" + noArt + "]";
	}
	
	
	
	

}
