package ngo.management.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import ngo.management.dto.BaseDTO;
import ngo.management.dto.FundRaiseDTO;

@Setter
@Getter
public class FundRaiseForm extends BaseForm{
	
	
	private String volunteerName;
	
	@NotEmpty(message = "Amount is required")
	private String amount;
	
	private Date date;
	
	private long volunteerId;

	@Override
	public BaseDTO getDTO() {
		FundRaiseDTO bean = new FundRaiseDTO();
		bean.setVolunteerName(volunteerName);
		bean.setAmount(amount);
		bean.setDate(date);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		FundRaiseDTO dto = (FundRaiseDTO) bDto;
		volunteerName = dto.getVolunteerName();
		amount = dto.getAmount();
		date = dto.getDate();
	}

}
