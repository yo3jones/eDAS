package edu.unlv.cs.edas.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.unlv.cs.edas.user.domain.UserAlreadyExistsException;
import edu.unlv.cs.edas.user.domain.UserManager;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired UserManager manager;
	@Autowired PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/new", method=GET)
	public ModelAndView getNewUser() {
		return getNewUserView(new NewUserModel());
	}
	
	@RequestMapping(value="/new", method=POST)
	public ModelAndView postNewUser(String name, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		try {
			manager.create(name, encodedPassword);
		} catch (UserAlreadyExistsException e) {
			return getNewUserView(new NewUserModel()
				.setMessage("A user already exists with that name."));
		}
		return getNewUserView(new NewUserModel()
			.setMessage("The user " + name + " was created.")
			.setShowCreate(false));
	}
	
	private ModelAndView getNewUserView(NewUserModel model) {
		ModelAndView view = new ModelAndView("newUser");
		view.addObject("model", model);
		return view;
	}
	
}
