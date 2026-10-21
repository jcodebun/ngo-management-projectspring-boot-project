package ngo.management.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ngo.management.dto.UserDTO;
import ngo.management.exception.DuplicateRecordException;
import ngo.management.form.ChangePasswordForm;
import ngo.management.form.LoginForm;
import ngo.management.form.MyProfileForm;
import ngo.management.form.UserRegistrationForm;
import ngo.management.service.UserServiceImpl;
import ngo.management.service.UserServiceInt;
import ngo.management.util.DataUtility;





@Controller
@RequestMapping
public class LoginCtl extends BaseCtl {

	private Logger log = Logger.getLogger(LoginCtl.class.getName());

	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_SIGNUP = "SignUp";
	protected static final String OP_LOGOUT = "Logout";

	@Autowired
	private UserServiceInt service;

	@Autowired
	private UserServiceImpl serviceimpl;
	
	@GetMapping("/login")
	public String display(@ModelAttribute("form") LoginForm form, @RequestParam(required = false) Long iId,
			HttpSession session, Model model) {
		log.info("LoginCtl login display method start");
		if (session.getAttribute("user") != null) {
			session.invalidate();
			model.addAttribute("success", "You have logout Successfully!!!");
		}
		if (DataUtility.getLong(String.valueOf(iId)) > 0) {
			session.setAttribute("iId", iId);
		}
		log.info("LoginCtl login display method End");
		return "login";
	}

	@ModelAttribute
	public void preload(Model model) {

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Male", "Male");
		map2.put("Female", "Female");
		model.addAttribute("gender", map2);
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("Volunteer", "Volunteer");
		map1.put("Donor", "Donor");
		model.addAttribute("roleName", map1);

	}

	@PostMapping("/login")
	public String submit(@RequestParam String operation, HttpSession session,
			@Valid @ModelAttribute("form") LoginForm form, BindingResult result, Model model) {
		log.info("LoginCtl login submit method start");
		System.out.println("In dopost  LoginCtl");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/login";
		}

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/signUp";
		}

		if (result.hasErrors()) {
			System.out.println(result);
			return "login";
		}

		UserDTO bean = service.authentication((UserDTO) form.getDTO());

		if (bean != null) {
			System.out.println(bean.toString());
			session.setAttribute("user", bean);
			return "redirect:/";
		}
		// System.out.println("status "+bean.getStatus());

		if (bean == null) {

			model.addAttribute("error", "Login Id & Password Invalid");
		}
		log.info("LoginCtl login submit method End");
		return "login";
	}

	@GetMapping("/signUp")
	public String display(@ModelAttribute("form") UserRegistrationForm form, Model model) {
		log.info("LoginCtl signUp display method start");
		log.info("LoginCtl signUp display method End");
		return "SignUp";
	}

	@PostMapping("/signUp")
	public String submit(@RequestParam("image")MultipartFile file, String operation, @Valid @ModelAttribute("form") UserRegistrationForm form,
			BindingResult bindingResult, Model model) throws IOException {

		log.info("LoginCtl signUp submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:signUp";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "SignUp";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				UserDTO entity = (UserDTO) form.getDTO();
				entity.setImage(file.getBytes());
				if (form.getRoleName().equalsIgnoreCase("Donor")) {
					entity.setRoleName("Donor");
					entity.setRoleId(2);
				}else {
					entity.setRoleName("Volunteer");
					entity.setRoleId(3);
				}
				service.add(entity);
				model.addAttribute("success", "User Registerd Successfully!!!!");
				return "SignUp";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "SignUp";
		}

		log.info("LoginCtl signUp submit method end");
		return "SignUp";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String displayProfile(HttpSession session, @ModelAttribute("form") MyProfileForm form, Model model) {
		UserDTO entity = (UserDTO) session.getAttribute("user");
		form.populate(entity);
		System.out.println("/Myprofile");
		return "myprofile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String submitProfile(HttpSession session, @ModelAttribute("form") @Valid MyProfileForm form,
			BindingResult bindingResult, @RequestParam(required = false) String operation, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/profile";
		}

		if (bindingResult.hasErrors()) {
			return "myprofile";
		}
		UserDTO entity = (UserDTO) session.getAttribute("user");
		entity = service.findBypk(entity.getId());
		entity.setFirstName(form.getFirstName());
		entity.setLastName(form.getLastName());
		try {
			service.update(entity);
		} catch (DuplicateRecordException e) {

		}
		model.addAttribute("success", "Profile Update successfully");

		return "myprofile";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String displayChangePassword(@ModelAttribute("form") ChangePasswordForm form, Model model) {
		return "changePassword";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String submitChangePassword(HttpSession session, @ModelAttribute("form") @Valid ChangePasswordForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "changePassword";
		}
		if (form.getNewPassword().equalsIgnoreCase(form.getConfirmPassword())) {

			UserDTO dto = (UserDTO) session.getAttribute("user");
			dto = service.findBypk(dto.getId());

			if (service.changePassword(dto.getId(), form.getOldPassword(), form.getNewPassword())) {
				model.addAttribute("success", "Password changed Successfully");
			} else {
				model.addAttribute("error", "Old Passowors Does not Matched");
			}
		} else {
			model.addAttribute("error", "New Password and confirm password does not matched");
		}
		return "changePassword";
	}

	@GetMapping("/userlist")
	public String userList(@ModelAttribute("form") UserRegistrationForm form, Model model) {
		List<UserDTO> list = service.list();
		model.addAttribute("list",list);
		return "userList";
	}
	
	@GetMapping("/home/Delete")
	public String Delete(@ModelAttribute("form") UserRegistrationForm form,
			@RequestParam(required = false) String operation, Long id, HttpSession session, Model model) {
		service.Delete(id);
		model.addAttribute("success", "Deleted Successfully!!!");
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		List<UserDTO> list = service.list();
		model.addAttribute("list", list);
		return "userList";
	}
	
	@GetMapping("/getImage/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");

		Blob blb=service.getImageById(id);
		
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping(value = "/home/login/myprofile", method = RequestMethod.GET)
	public String displayMyProfile(@ModelAttribute("form") MyProfileForm form, HttpSession session, Model model) {
		UserDTO dto = (UserDTO)session.getAttribute("user");
		long id = dto.getId();
		UserDTO bean =  service.findBypk(id);
		form.populate(bean);
		return "myProfile";
	}
	
	@RequestMapping(value = "/home/login/myprofile", method = RequestMethod.POST)
	public String submitMyProfile(HttpSession session, @ModelAttribute("form") @Valid MyProfileForm form,
			BindingResult bindingResult, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/myprofile";
		}
		
		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {				
//			if (bindingResult.hasErrors()) {
//				return "myProfile";
//			}
			System.out.println("I am heer");
			UserDTO bean = (UserDTO)(form.getDTO());
			UserDTO uDto=(UserDTO)session.getAttribute("user");
			long id = uDto.getId();
			System.out.println("bean id: "+id);
			try {
			if(id>0) {
				uDto.setFirstName(bean.getFirstName());
				uDto.setLastName(bean.getLastName());
				uDto.setLogin(bean.getLogin());
				uDto.setGender(bean.getGender());
				uDto.setContactNo(bean.getContactNo());
				uDto.setPassword(bean.getPassword());
			
				service.update(uDto);
				model.addAttribute("success", "Profile Updated Successfully!!!!");
				} }catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			return "myProfile";
	}
		return "";
	}

}
