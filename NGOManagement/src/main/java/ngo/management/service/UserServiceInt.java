package ngo.management.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import ngo.management.dto.UserDTO;
import ngo.management.exception.DuplicateRecordException;




public interface UserServiceInt {

	public long add(UserDTO dto) throws DuplicateRecordException;

	public void Delete(long id);

	public UserDTO findBypk(long pk);

	public UserDTO findByLogin(String login);

	public void update(UserDTO dto) throws DuplicateRecordException;

	public List<UserDTO> list();

	public List<UserDTO> list(int pageNo, int pageSize);

	public List<UserDTO> search(UserDTO dto);

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize);

	public UserDTO authentication(UserDTO dto);

	public boolean changePassword(Long id, String oldPassword, String newPassword);

	public Blob getImageById(long id) throws SerialException, SQLException;

}
