package ngo.management.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "donateMoney")
public class DonateMoneyDTO extends BaseDTO {
	
	
	@Column(name = "donar_name", length = 255)
	private String donorName;
	
	@Column(name = "bank_name", length = 255)
	private String bankName;
	
	@Column(name = "ifscCode", length = 255)
	private String ifscCode;
	
	@Column(name = "accounttNo", length = 255)
	private String accountNo;
	
	@Column(name = "amount", length = 255)
	private String amount;
	
	@Column(name = "login", length = 255)
	private String login;
	
	@Column(name = "donor_id", length = 255)
	private long donorId;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return donorName;
	}
	
	

}
