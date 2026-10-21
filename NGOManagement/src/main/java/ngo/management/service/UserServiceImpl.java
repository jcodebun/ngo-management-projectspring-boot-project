package ngo.management.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ngo.management.dao.UserDAOInt;
import ngo.management.dto.UserDTO;
import ngo.management.exception.DuplicateRecordException;


@Service
public class UserServiceImpl implements UserServiceInt {

	private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserDAOInt dao;


	@Override
	@Transactional
	public long add(UserDTO dto) throws DuplicateRecordException {
		log.info("UserServiceImpl Add method start");
		UserDTO existDTO = dao.findByLogin(dto.getLogin());
		if (existDTO != null)
			throw new DuplicateRecordException("Login Id Already Exist");
		long pk = dao.add(dto);
		log.info("UserServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void Delete(long id) {
		log.info("UserServiceImpl Delete method start");
		dao.Delete(id);
		log.info("UserServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public UserDTO findBypk(long pk) {
		log.info("UserServiceImpl findBypk method start");
		UserDTO dto = dao.findBypk(pk);
		log.info("UserServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public UserDTO findByLogin(String login) {
		log.info("UserServiceImpl findByUserName method start");
		UserDTO dto = dao.findByLogin(login);
		log.info("UserServiceImpl findByUserName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(UserDTO dto) throws DuplicateRecordException {
		log.info("UserServiceImpl update method start");
		UserDTO existDTO = dao.findByLogin(dto.getLogin());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Login Id Already Exist");
		dao.update(dto);
		log.info("UserServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<UserDTO> list() {
		log.info("UserServiceImpl list method start");
		List<UserDTO> list = dao.list();
		log.info("UserServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<UserDTO> list(int pageNo, int pageSize) {
		log.info("UserServiceImpl list method start");
		List<UserDTO> list = dao.list(pageNo, pageSize);
		log.info("UserServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<UserDTO> search(UserDTO dto) {
		log.info("UserServiceImpl search method start");
		List<UserDTO> list = dao.search(dto);
		log.info("UserServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		log.info("UserServiceImpl search method start");
		List<UserDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("UserServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public UserDTO authentication(UserDTO dto) {
		log.info("UserServiceImpl authentication method start");
		dto = dao.authentication(dto);
		log.info("UserServiceImpl authentication method end");
		return dto;
	}

	@Override
	@Transactional
	public boolean changePassword(Long id, String oldPassword, String newPassword) {
		log.info("UserServiceImpl  changePassword method start");
		UserDTO dtoExist = findBypk(id);
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
			dtoExist.setPassword(newPassword);
			try {
				dao.update(dtoExist);
			} catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
			log.info("UserServiceImpl  changePassword method end");
			return true;
		} else {
			return false;
		}
	}


	
	@Override
	@Transactional
	public Blob getImageById(long id) throws SerialException, SQLException {
		return dao.getImageById(id);
	}

	
}
