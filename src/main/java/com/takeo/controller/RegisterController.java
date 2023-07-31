package com.takeo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.takeo.Entity.Register;
import com.takeo.service.RegisterServiceImpl;

@Controller
public class RegisterController {
	@Autowired
	private RegisterServiceImpl registerService;

	@RequestMapping("/")
	public String homePage() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model, Register register) {

		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(Model model, Register register) {

		return "register";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashBoard(Model model, @ModelAttribute("user") Register user) {
		// Retrieve the user's first name

		model.addAttribute("user", user);

		return "dashboard";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
public String profile(@RequestParam("email") String email, Model model) {
    Register user = registerService.getUserByEmail(email);
    model.addAttribute("user", user);
    return "profile";
}


	@RequestMapping(value = "/register/insert", method = RequestMethod.POST)
	public String register(@ModelAttribute Register register, Model model, RedirectAttributes redirectAttributes) {

		boolean addUser = registerService.addUser(register);

		if (addUser) {
			redirectAttributes.addFlashAttribute("successMessage", "Registration successful! You can now log in.");
			return "redirect:/login";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Email Already exists!!!");
			return "redirect:/register";
		}
	}

	@RequestMapping(value = "/login/insert", method = RequestMethod.GET)
	public String login(@ModelAttribute Register register, Model model, RedirectAttributes redirectAttributes) {

		Register verifyUser = registerService.verifyUser(register);
		final String user = "admin@gmail.com";
		final String pass = "admin";

		if (register.getEmail().equals(user) && register.getPassword().equals(pass)) {
			return "redirect:/inventory";
		} else if (verifyUser != null) {
			redirectAttributes.addFlashAttribute("user", verifyUser);
			return "redirect:/dashboard";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Username and Password doesnot match");
			return "redirect:/login";
		}
	}

}