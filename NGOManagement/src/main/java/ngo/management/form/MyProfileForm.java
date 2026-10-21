package ngo.management.form;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import ngo.management.dto.BaseDTO;
import ngo.management.dto.UserDTO;
@Setter
@Getter
public class MyProfileForm extends BaseForm {
	@NotEmpty(message = "First Name is required")
	private String firstName;
	@NotEmpty(message = "Last Name is required")
	private String lastName;
	@NotEmpty(message = "Login is required")
	private String login;
	@NotEmpty(message = "Password is required")
	private String password;
	@NotEmpty(message = "Mobile No. is required")
	private String mobile;
	@NotEmpty(message = "Gender is required")
	private String gender;
	@NotEmpty(message = "Unique Id Proof is required")
	private String uniqueId;

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setLogin(login);
		bean.setPassword(password);
		bean.setContactNo(mobile);
		bean.setGender(gender);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean = (UserDTO)bDto;
		firstName = bean.getFirstName();
		lastName = bean.getLastName();
		login = bean.getLogin();
		password = bean.getPassword();
		mobile = bean.getContactNo();
		gender = bean.getGender();
		}

}
