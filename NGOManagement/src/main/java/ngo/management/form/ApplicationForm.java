package ngo.management.form;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import ngo.management.dto.ApplicationDTO;
import ngo.management.dto.BaseDTO;


@Setter
@Getter
public class ApplicationForm extends BaseForm{
	
	
	private String volunteerName;

	@NotEmpty(message = "Decription is required")
	private String decription;
	
	@NotEmpty(message = "Address is required")
	private String address;
	
	private String login;
	
	@NotEmpty(message = "Qualification is required")
	private String qualification;
	
	private String status;
	
	private long volunteerId;

	@Override
	public BaseDTO getDTO() {
		ApplicationDTO bean = new ApplicationDTO();
		bean.setQualification(qualification);
		bean.setAddress(address);
		bean.setDescription(decription);
		bean.setVolunteerName(volunteerName);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		ApplicationDTO dto = (ApplicationDTO) bDto;
		qualification = dto.getQualification();
		address = dto.getAddress();
		decription = dto.getDescription();
		volunteerName = dto.getVolunteerName();
	}

}
