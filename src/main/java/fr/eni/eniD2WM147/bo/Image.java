package fr.eni.eniD2WM147.bo;

public class Image {

	private String picture;
	private int id;

	public Image(String picture) {
		super();
		this.picture = picture;
	}

	public Image(String picture, int id) {
		super();
		this.picture = picture;
		this.setId(id);
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
