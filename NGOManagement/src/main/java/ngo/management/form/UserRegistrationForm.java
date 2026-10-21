package ngo.management.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import ngo.management.dto.BaseDTO;
import ngo.management.dto.UserDTO;

@Setter
@Getter
public class UserRegistrationForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$", message = "First Name is Invalid")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$", message = "Last Name is Invalid")
	private String lastName;
	@NotEmpty(message = "Gender is required")
	private String gender;
	@NotEmpty(message = "Phone No is required")
	@Pattern(regexp = "(^[7-9][0-9]{9})*$", message = "MobileNo is Invalid")
	private String contactNo;
	@NotEmpty(message = "Email Id is required")
	@Pattern(regexp = "(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))*$", message = "Email id is invalid")
	private String login;
	@NotEmpty(message = "Password is required")
	private String password;
	@NotEmpty(message = "Id Proof is required")
	private String idProof;

	private long roleId;
	private String roleName;

	private MultipartFile image;

	private Date date;

	@Override
	public BaseDTO getDTO() {
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setContactNo(contactNo);
		bean.setGender(gender);
		bean.setPassword(password);
		bean.setLogin(login);
	    bean.setDate(date);
	    bean.setIdProof(idProof);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean = (UserDTO) bDto;
		id = bean.getId();
		firstName = bean.getFirstName();
		lastName = bean.getLastName();
		contactNo = bean.getContactNo();
		gender = bean.getGender();
		password = bean.getPassword();
		login = bean.getLogin();
		date = bean.getDate();
		idProof = bean.getIdProof();

	}

}
