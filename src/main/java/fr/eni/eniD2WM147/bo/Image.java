package fr.eni.eniD2WM147.bo;

public class Image {

	private String picture;
	private String name;

	
	

	public Image(String picture, String name) {
		super();
		this.picture = picture;
		this.setName(name);
	}

	public Image() {
		
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Image [picture=" + picture + ", name=" + name + "]";
	}


	
	
	

}
