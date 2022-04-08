package br.com.italomdd.gallery.dto;

import javax.validation.constraints.NotBlank;

import br.com.italomdd.gallery.model.Image;

public class ImageForm {

	@NotBlank
	private String author;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String url;
	
	@NotBlank
	private String font;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}
	
	public Image toImage() {
		Image image = new Image();
		image.setAuthor(this.author);
		image.setUrlFont(this.font);
		image.setTitle(this.title);
		image.setUrlImage(this.url);
		return image;
	}
	
}
