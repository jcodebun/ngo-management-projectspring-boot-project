package ngo.management.ctl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ngo.management.dto.ApplicationDTO;
import ngo.management.dto.UserDTO;
import ngo.management.exception.DuplicateRecordException;
import ngo.management.form.ApplicationForm;
import ngo.management.service.ApplicationServiceInt;


@Controller
@RequestMapping("/ctl/application")
public class ApplicationCtl extends BaseCtl{
	
	
	@Autowired
	private ApplicationServiceInt service;

	@ModelAttribute
	public void preload(Model model) {

	}

	@GetMapping
	public String display(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") ApplicationForm form, HttpSession session, Model model) {
		if (form.getId() > 0) {
			ApplicationDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "application";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") ApplicationForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/application";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "application";
				}
				ApplicationDTO bean = (ApplicationDTO) form.getDTO();
				UserDTO uDto = (UserDTO) session.getAttribute("user");
				bean.setVolunteerName(uDto.getFirstName());
				bean.setVolunteerId(uDto.getId());
				bean.setLogin(uDto.getLogin());
				bean.setStatus("sent");
				service.add(bean);
				model.addAttribute("success", "Apply Application Successfully!!!!");
				return "application";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "application";
		}
		return "application";
	}

	@GetMapping("/update")
	public String findByPk(@ModelAttribute("form") ApplicationForm form,
			@RequestParam(required = false) String operation, Long id, HttpSession session, Model model) {
		ApplicationDTO bean = service.findBypk(id);
		form.populate(bean);
		return "application";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ApplicationForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/application/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/application";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ApplicationDTO dto = new ApplicationDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ApplicationDTO dto = (ApplicationDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");

		if (uDto != null) {
			if (uDto.getRoleId() == 1) {

			} else {
				 dto.setVolunteerId(uDto.getId());
			}

		}

		List<ApplicationDTO> list = service.search(dto, pageNo, pageSize);
		List<ApplicationDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "applicationList";
	}
	
	@GetMapping("/cancle")
	public String Cancle(@ModelAttribute("form") ApplicationForm form,
			@RequestParam(required = false) String operation, Long id, String status ,HttpSession session, Model model) {
		ApplicationDTO bean =  service.findBypk(id);
		service.Cancle(id,bean.getStatus());
		model.addAttribute("success", "Cancle Your Application");
		
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		ApplicationDTO dto = (ApplicationDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");

		if (uDto != null) {
			if (uDto.getRoleId() == 1) {

			} else {
				 dto.setVolunteerId(uDto.getId());
			}

		}

		List<ApplicationDTO> list = service.search(dto, pageNo, pageSize);
		List<ApplicationDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "applicationList";
	}
	
	@GetMapping("/confirm")
	public String Confirm(@ModelAttribute("form") ApplicationForm form,
			@RequestParam(required = false) String operation, Long id, String status ,HttpSession session, Model model) {
		System.out.println("Confirm");
		ApplicationDTO bean =  service.findBypk(id);
		service.Confirm(id,bean.getStatus());
		model.addAttribute("success", "Confirm Your Application Successfully!!!");
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		ApplicationDTO dto = (ApplicationDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");

		if (uDto != null) {
			if (uDto.getRoleId() == 1) {

			} else {
				 dto.setVolunteerId(uDto.getId());
			}

		}

		List<ApplicationDTO> list = service.search(dto, pageNo, pageSize);
		List<ApplicationDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "applicationList";
	

}
}
