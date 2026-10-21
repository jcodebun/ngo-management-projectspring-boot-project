package ngo.management.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "fundRaise")
public class FundRaiseDTO extends BaseDTO {
	
	@Column(name = "volunteer_name", length = 255)
	private String volunteerName;
	

	@Column(name = "volunteer_id", length = 255)
	private long volunteerId;
	
	private Date date;

	@Column(name = "amount", length = 255)
	private String amount;
	

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
