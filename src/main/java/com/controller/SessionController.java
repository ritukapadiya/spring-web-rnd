package com.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bean.CountryBean;
import com.bean.LoginBean;
import com.bean.UserBean;
import com.bean.UserCountryBean;
import com.dao.UserDao;
import com.google.protobuf.DescriptorProtos.FileOptions;

@Controller
public class SessionController {

	@Autowired
	UserDao userDao;

	@Autowired
	BCryptPasswordEncoder encoder;

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new UserBean());
//		List<CountryBean> countries = userDao.getAllCountries();
//		CountryBean cb = new CountryBean();
//		cb.setCode("-1");
//		cb.setName("Select country");
//		countries.add(0,cb);
//		model.addAttribute("countries",countries);
		model.addAttribute("countries2",userDao.getAllCountries());
		return "Signup";// jsp
	}

	@RequestMapping(value = "login") // defult it will take get
	public String login(Model model) {
		model.addAttribute("loginUser", new LoginBean());
		return "Login";
	}

	@GetMapping("forgetpassword") // direct get mapsping
	public String forgetPassword() {
		return "ForgetPassword";
	}

	@PostMapping("saveuser")
	public String saveUser(@ModelAttribute("user") @Valid UserBean user, BindingResult result, Model model) {
		// get
		// valid
		model.addAttribute("user", user);

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			model.addAttribute("countries2",userDao.getAllCountries());
			return "Signup";
		} else {
			// bean
			// dao -> db
			user.setPassword(encoder.encode(user.getPassword()));
			try {
				String masterPath = "D:\\Java\\Spring-Web\\src\\main\\webapp\\resources\\images";

				File f = new File(masterPath, user.getEmail());// email folder
				f.mkdir();

				File f2 = new File(f, user.getProfile().getOriginalFilename());

				FileUtils.writeByteArrayToFile(f2, user.getProfile().getBytes());
			
				user.setProfilePath("resources/images/"+user.getEmail()+"/"+user.getProfile().getOriginalFilename()); 
			
			} catch (Exception e) {
				e.printStackTrace();
			}

			userDao.addUser(user);
			System.out.println(user.getEmail());
			return "Home";// jsp
		}
	}

	@PostMapping("/authenticate")
	public String authenticate(LoginBean login, Model model, HttpSession session) { // email and password
		UserBean user = userDao.getUserByEmail(login.getEmail());
		if (user != null) {
			System.out.println(" Email Found " + login.getEmail());

			if (encoder.matches(login.getPassword(), user.getPassword())) {
				session.setAttribute("user", user);

				if (user.getRole() == null) {
					model.addAttribute("user", login);
					return "Login";
				} else if (user.getRole().equals("USER")) {
					return "Home";

				} else if (user.getRole().contentEquals("ADMIN")) {
					return "redirect:/dashboard"; // do not open jsp - redirect to dashboard url
				}
			}
		} else {
			System.out.println("No Email Found " + login.getEmail());

		}
		model.addAttribute("loginUser", login);
		return "Login";
	}
	// method -> model->

//	@PostMapping("dashboard")
//	public String dashboard() {
//		return "Dashboard";
//	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@GetMapping("/profile")
	public String profile() {
		return "Profile";
	}

	@PostMapping("/profile")
	public String profileSave(@RequestParam("profile") MultipartFile file,Model model) {
		String masterPath = "D:\\Java\\Spring-Web\\src\\main\\webapp\\resources\\images";

		try {
			File f = new File(masterPath, file.getOriginalFilename());
			FileUtils.writeByteArrayToFile(f, file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(file.getOriginalFilename());
		return "redirect:/login";
	}

}
