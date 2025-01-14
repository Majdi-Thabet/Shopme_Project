package com.shopme.admin;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;


/* Le Controller gère les requêtes HTTP et renvoie des vues (comme des fichiers HTML ou des templates).
 * 
 * Le contrôleur appelle la méthode listAll() du service, qui utilise le repository pour obtenir tous les utilisateurs de la base de données.
 * 
 * La liste des utilisateurs est ajoutée au modèle sous le nom "listUsers"
 * */
@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model) {
		
		List<Role> listRoles = service.listRoles();
		
		User user = new User();
		user.setEnabled(true);
		
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		System.out.println(user);
		service.save(user);
		
		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully");
		return "redirect:/users";
	}
}
