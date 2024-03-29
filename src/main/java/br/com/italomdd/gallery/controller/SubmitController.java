package br.com.italomdd.gallery.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.italomdd.gallery.dto.ImageForm;
import br.com.italomdd.gallery.model.Image;
import br.com.italomdd.gallery.model.User;
import br.com.italomdd.gallery.repository.ImageRepository;
import br.com.italomdd.gallery.repository.UserRepository;

@Controller
@RequestMapping("submit")
public class SubmitController {
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String submit(ImageForm imageForm) {
		return "forms/submitForm";
	}
	
	@PostMapping
	public String save(@Valid ImageForm imageForm, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return "forms/submitForm";
		}
		
		String username = principal.getName();
		User user = userRepository.getById(username);
		Image image = imageForm.toImage();
		
		image.setUser(user);
		user.getImages().add(image);
		
		imageRepository.save(image);
		
		return "redirect:/home";
	}
}
