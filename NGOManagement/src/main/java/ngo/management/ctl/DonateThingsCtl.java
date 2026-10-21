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

import ngo.management.dto.DonateThingDTO;
import ngo.management.dto.UserDTO;
import ngo.management.exception.DuplicateRecordException;
import ngo.management.form.DonateThingForm;
import ngo.management.service.DonateThingServiceInt;


@Controller
@RequestMapping("/ctl/donateThings")
public class DonateThingsCtl extends BaseCtl{
	
	@Autowired
	private DonateThingServiceInt service;

	@ModelAttribute
	public void preload(Model model) {

	}

	@GetMapping
	public String display(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") DonateThingForm form, HttpSession session, Model model) {
		if (form.getId() > 0) {
			DonateThingDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "donateThings";
	}

	@PostMapping
	public String submit(@Valid @ModelAttribute("form") DonateThingForm form, BindingResult bindingResult,
			HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/ctl/donateThings";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "donateThings";
				}
				DonateThingDTO bean = (DonateThingDTO) form.getDTO();
				UserDTO uDto = (UserDTO) session.getAttribute("user");
				bean.setDonorName(uDto.getFirstName());
				bean.setDonorId(uDto.getId());
				bean.setLogin(uDto.getLogin());

				service.add(bean);
				model.addAttribute("success", "Donate Things Successfully!!!!");
				return "donateThings";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "donateThings";
		}
		return "donateThings";
	}

	@GetMapping("/update")
	public String findByPk(@ModelAttribute("form") DonateThingForm form,
			@RequestParam(required = false) String operation, Long id, HttpSession session, Model model) {
		DonateThingDTO bean = service.findBypk(id);
		form.populate(bean);
		return "donateThings";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") DonateThingForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/donateThings/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/ctl/donateThings";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					DonateThingDTO dto = new DonateThingDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		DonateThingDTO dto = (DonateThingDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");

		if (uDto != null) {
			if (uDto.getRoleId() == 1) {

			} else {
				// dto.setUserId(uDto.getLogin());
			}

		}

		List<DonateThingDTO> list = service.search(dto, pageNo, pageSize);
		List<DonateThingDTO> totallist = service.search(dto);
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
		return "donateThingsList";
	}

}
