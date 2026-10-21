package ngo.management.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "donateThings")
public class DonateThingDTO extends BaseDTO{
	
	@Column(name = "donar_name", length = 255)
	private String donorName;
	
	@Column(name = "donateThing", length = 255)
	private String donateThing;
	
	@Column(name = "login", length = 255)
	private String login;
	
	@Column(name = "donor_id", length = 255)
	private long donorId;

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
