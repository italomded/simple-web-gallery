package br.com.italomdd.gallery.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.italomdd.gallery.model.Image;
import br.com.italomdd.gallery.model.User;
import br.com.italomdd.gallery.repository.ImageRepository;
import br.com.italomdd.gallery.repository.UserRepository;

@Controller
@RequestMapping("home")
public class HomeController {

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public String home(Model model, Principal principal) {
		List<Image> images = imageRepository.findWithAll();
		if (principal != null) {
			images.removeIf(img -> img.getUser().getUsername().equals(principal.getName()));
		}
		model.addAttribute("images", images);
		return "home";
	}
	
	@PostMapping("view")
	public String view(String id) {
		Long idToSearch = Long.parseLong(id);
		Optional<Image> imageSearched = imageRepository.findById(idToSearch);
		if (imageSearched.isEmpty()) {
			return "home";
		}
		
		Image image = imageSearched.get();
		if (image.getViews() == null) {
			image.setViews(1);
		} else {
			image.setViews(image.getViews() + 1);
		}
		
		imageRepository.save(image);
		
		return "redirect:" + image.getFont();
	}
	
	@PostMapping("favorite")
	public String favorite(String id, Principal principal) {
		Long idToSearch = Long.parseLong(id);
		Optional<Image> imageSearched = imageRepository.findById(idToSearch);
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
			image.getUsersFavorited().add(user);	
			userRepository.save(user);
		}

		return "redirect:/home";
	}
	
}
