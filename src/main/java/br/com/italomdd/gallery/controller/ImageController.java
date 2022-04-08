package br.com.italomdd.gallery.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.italomdd.gallery.model.Image;
import br.com.italomdd.gallery.model.User;
import br.com.italomdd.gallery.repository.ImageRepository;
import br.com.italomdd.gallery.repository.UserRepository;

@Controller
@RequestMapping
public class ImageController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@PostMapping("like")
	public String like(String id, Principal principal) {
		Long idToSearch = Long.parseLong(id);
		Optional<Image> imageSearched = imageRepository.findByIdAndUserUsername(idToSearch, principal.getName());
		Optional<User> userSearched = userRepository.findById(principal.getName());
		if (imageSearched.isEmpty() || userSearched.isEmpty()) {
			return "/home";
		}
		
		User user = userSearched.get();
		Image image = imageSearched.get();
		
		boolean alreadyFavorited = false;
		for (Image userImage : user.getFavoriteImages()) {
			if (userImage.getId() == image.getId()) {
				alreadyFavorited = true;
			}
		}
		
		if (!alreadyFavorited) {
			user.getFavoriteImages().add(image);	
			userRepository.save(user);
		}

		return "redirect:/home";
	}
	
	@PostMapping("unlike")
	public String unlike(String id, Principal principal) {
		Long idToSearch = Long.parseLong(id);
		Optional<Image> imageSearched = imageRepository.findById(idToSearch);
		Optional<User> userSearched = userRepository.findById(principal.getName());
		if (imageSearched.isEmpty() || userSearched.isEmpty()) {
			return "/home";
		}
		
		User user = userSearched.get();
		Image image = imageSearched.get();
		
		user.getFavoriteImages().removeIf(im -> im.getId() == image.getId());
		
		userRepository.save(user);

		return "redirect:/likeds";
	}
	
	@PostMapping("view")
	public String view(String id) {
		Long idToSearch = Long.parseLong(id);
		Optional<Image> imageSearched = imageRepository.findById(idToSearch);
		if (imageSearched.isEmpty()) {
			return "home";
		}
		
		Image image = imageSearched.get();
		image.setViews(image.getViews() + 1);
		
		imageRepository.save(image);
		
		return "redirect:" + image.getUrlFont();
	}
	
}
