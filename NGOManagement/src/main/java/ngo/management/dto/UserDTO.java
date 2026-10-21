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
@Table(name = "user")
public class UserDTO extends BaseDTO {
	
	@Column(name = "first_name", length = 255)
	private String firstName;
	
	@Column(name = "last_name", length = 255)
	private String lastName;
	@Column(name = "gender", length = 255)
	private String gender;
	@Column(name = "contactNo", length = 255)
	private String contactNo;
	@Column(name = "login", length = 255)
	private String login;
	@Column(name = "password", length = 255)
	private String password;
	@Column(name = "role_id", length = 255)
	private long roleId;
	@Column(name = "id_proof", length = 255)
	private String idProof;
	@Column(name = "ROLE_NAME",length = 225)
	private String roleName;
	@Column(name="IMAGE",columnDefinition = "LONGBLOB")
	private byte[] image;
	
	private Date date;
	
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return firstName + lastName;
	}

}
