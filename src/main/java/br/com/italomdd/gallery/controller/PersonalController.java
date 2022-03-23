package br.com.italomdd.gallery.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.italomdd.gallery.model.Image;
import br.com.italomdd.gallery.repository.ImageRepository;

@Controller
@RequestMapping("personal")
public class PersonalController {

	@Autowired
	private ImageRepository imageRepository;
	
	@GetMapping
	public String personal(Model model, Principal principal) {
		List<Image> images = imageRepository.findByUserUsername(principal.getName());
		model.addAttribute("images", images);
		return "personal";
	}
	
}
