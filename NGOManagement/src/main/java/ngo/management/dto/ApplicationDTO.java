package ngo.management.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "application")
public class ApplicationDTO extends BaseDTO{
	
	
	@Column(name = "volunteer_name", length = 255)
	private String volunteerName;
	
	@Column(name = "qualification", length = 255)
	private String qualification;
	
	@Column(name = "login", length = 255)
	private String login;
	
	@Column(name = "address", length = 3500)
	private String address;
	
	@Column(name = "description", length = 3500)
	private String description;
	
	@Column(name = "volunteer_id", length = 255)
	private long volunteerId;
	
	private String status;
	

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
