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
@RequestMapping("favorite")
public class FavoriteController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@GetMapping
	public String favorite(Model model, Principal principal) {
		List<Image> images = userRepository.findByUsername(principal.getName());
		model.addAttribute("images", images);
		return "favorite";
	}
	
	@PostMapping("remove")
	public String removeFavorite(String id, Principal principal) {
		Long idToSearch = Long.parseLong(id);
		Optional<Image> imageSearched = imageRepository.findById(idToSearch);
		Optional<User> userSearched = userRepository.findById(principal.getName());
		if (imageSearched.isEmpty() || userSearched.isEmpty()) {
			return "/home";
		}
		
		User user = userSearched.get();
		Image image = imageSearched.get();
		
		user.getFavoriteImages().removeIf(im -> im.getId() == image.getId());
		image.getUsersFavorited().removeIf(usr -> usr.getUsername() == user.getUsername());
		
		userRepository.save(user);

		return "redirect:/favorite";
	}
	
}
