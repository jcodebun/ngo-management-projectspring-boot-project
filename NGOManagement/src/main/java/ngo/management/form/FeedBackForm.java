package ngo.management.form;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import ngo.management.dto.BaseDTO;
import ngo.management.dto.FeedBackDTO;


@Setter
@Getter
public class FeedBackForm extends BaseForm{
	
	private String donorName;

	@NotEmpty(message = "Decription is required")
	private String decriptionOne;
	
	@NotEmpty(message = "Decription is required")
	private String decriptionTwo;
	
	@NotEmpty(message = "Decription is required")
	private String decriptionThree;
	
	@NotEmpty(message = "Decription is required")
	private String decriptionFourth;
	
	private long donorId;

	@Override
	public BaseDTO getDTO()  {
		FeedBackDTO bean = new FeedBackDTO();
		bean.setDecriptionOne(decriptionOne);
		bean.setDecriptionTwo(decriptionTwo);
		bean.setDecriptionThree(decriptionThree);
		bean.setDecriptionFourth(decriptionFourth);
		bean.setDonorName(donorName);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto)  {
		FeedBackDTO dto = (FeedBackDTO) bDto;
		decriptionOne = dto.getDecriptionOne();
		decriptionTwo = dto.getDecriptionTwo();
		decriptionThree = dto.getDecriptionThree();
		decriptionFourth = dto.getDecriptionFourth();
		donorName = dto.getDonorName();
	}

}
