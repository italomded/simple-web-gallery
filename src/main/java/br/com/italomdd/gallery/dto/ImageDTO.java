package br.com.italomdd.gallery.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.italomdd.gallery.model.Image;

public class ImageDTO {
	private Long id;
	private String author;
	private String title;
	private String url;
	private Long views;
	private String user;
	
	public ImageDTO() {}
	
	public ImageDTO(Image img) {
		this.id = img.getId();
		this.author = img.getAuthor();
		this.title = img.getTitle();
		this.url = img.getUrlImage();
		this.views = img.getViews();
		this.user = img.getUser().getUsername();
	}
	
	public Long getId() {
		return id;
	}
	public String getAuthor() { return author; }
	public String getTitle() {
		return title;
	}
	public String getUrl() { return url; }
	public Long getViews() { return views; }
	public String getUser() {
		return user;
	}

	public List<ImageDTO> converter(List<Image> listImages) {
		return listImages.stream().map(img -> new ImageDTO(img)).collect(Collectors.toList());
	}
}
