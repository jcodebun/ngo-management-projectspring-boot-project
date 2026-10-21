package ngo.management.form;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import ngo.management.dto.BaseDTO;
import ngo.management.dto.UserDTO;
@Setter
@Getter
public class LoginForm extends BaseForm{
	@NotEmpty(message = "Login is required")
	private String login;
	@NotEmpty(message = "Password is required")
	private String password;

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		UserDTO bean = new UserDTO();
		bean.setLogin(login);
		bean.setPassword(password);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean = (UserDTO)getDTO();
		login = bean.getLogin();
		password = bean.getPassword();
	}

}
