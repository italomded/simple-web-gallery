package br.com.italomdd.gallery.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.italomdd.gallery.dto.ImageDTO;
import br.com.italomdd.gallery.model.Image;
import br.com.italomdd.gallery.repository.ImageRepository;
import br.com.italomdd.gallery.repository.UserRepository;

@Controller
@RequestMapping
public class VisualizationController {
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("home")
	public String home(Model model, Principal principal) {
		List<Image> images;
		if (principal != null) {
			images = imageRepository.findAllImagesExceptUser(principal.getName());
		} else {
			images = imageRepository.findWithAll();
		}
		model.addAttribute("images", new ImageDTO().converter(images));
		return "home";
	}
	
	@GetMapping("likeds")
	public String list(Model model, Principal principal) {
		List<Image> images = userRepository.getFavoriteImages(principal.getName());
		model.addAttribute("images", new ImageDTO().converter(images));
		return "likeds";
	}
	
	@GetMapping("uploads")
	public String personal(Model model, Principal principal) {
		List<Image> images = imageRepository.findByUserUsername(principal.getName());
		model.addAttribute("images", new ImageDTO().converter(images));
		return "uploads";
	}
}
