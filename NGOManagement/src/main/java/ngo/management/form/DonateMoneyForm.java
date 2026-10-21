package ngo.management.form;


import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import ngo.management.dto.BaseDTO;
import ngo.management.dto.DonateMoneyDTO;


@Setter
@Getter
public class DonateMoneyForm extends BaseForm {
	
	
	private String donorName;

	@NotEmpty(message = "Bank Name is required")
	private String bankName;
	
	@NotEmpty(message = "IFSCCode is required")
	private String ifscCode;
	
	@NotEmpty(message = "Account No is required")
	private String accountNo;
	
	private String login;
	
	@NotEmpty(message = "Amount is required")
	private String amount;

	private long donorId;

	@Override
	public BaseDTO getDTO() {
		DonateMoneyDTO bean = new DonateMoneyDTO();
		bean.setBankName(bankName);
		bean.setIfscCode(ifscCode);
		bean.setAccountNo(accountNo);
		bean.setAmount(amount);
		bean.setDonorName(donorName);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		DonateMoneyDTO dto = (DonateMoneyDTO) bDto;
		bankName = dto.getBankName();
		ifscCode = dto.getIfscCode();
		accountNo = dto.getAccountNo();
		amount = dto.getAmount();
		donorName = dto.getDonorName();
	}

}
