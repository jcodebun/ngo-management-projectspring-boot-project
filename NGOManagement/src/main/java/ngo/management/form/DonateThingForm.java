package ngo.management.form;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import ngo.management.dto.BaseDTO;
import ngo.management.dto.DonateThingDTO;

@Setter
@Getter
public class DonateThingForm extends BaseForm{
	

	private String donorName;

	@NotEmpty(message = "Donate Things is required")
	private String donateThing;
	
	private String login;

	private long donorId;
	
	@Override
	public BaseDTO getDTO() {
		DonateThingDTO bean = new DonateThingDTO();
		bean.setDonateThing(donateThing);
		bean.setDonorName(donorName);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		DonateThingDTO dto = (DonateThingDTO) bDto;
		donateThing = dto.getDonateThing();
		donorName = dto.getDonorName();

	}

}
