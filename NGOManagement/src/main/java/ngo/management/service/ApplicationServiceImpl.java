package ngo.management.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ngo.management.dao.ApplicationDAOInt;
import ngo.management.dto.ApplicationDTO;
import ngo.management.exception.DuplicateRecordException;

@Service
public class ApplicationServiceImpl implements ApplicationServiceInt{
	
	
	private static Logger log = Logger.getLogger(DonateThingServiceImpl.class.getName());

	@Autowired
	private ApplicationDAOInt dao;

	@Override
	@Transactional
	public long add(ApplicationDTO dto) throws DuplicateRecordException {
		log.info("CategoryServiceImpl Add method start");
		ApplicationDTO existDTO = dao.findByName(dto.getVolunteerName());
		if (existDTO != null)
			throw new DuplicateRecordException("Application Already Exist");
		long pk = dao.add(dto);
		log.info("CategoryServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ApplicationDTO dto) {
		dao.delete(dto);
		
	}

	@Override
	@Transactional
	public ApplicationDTO findBypk(long pk) {
		ApplicationDTO dto = dao.findBypk(pk);
		log.info("CategoryServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ApplicationDTO findByName(String name) {
		ApplicationDTO dto = dao.findByName(name);
		log.info("CategoryServiceImpl findByCategoryName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ApplicationDTO dto) throws DuplicateRecordException {
		ApplicationDTO existDTO = dao.findByName(dto.getVolunteerName());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.update(dto);
		
	}

	@Override
	@Transactional
	public List<ApplicationDTO> list() {
		List<ApplicationDTO> list = dao.list();
		log.info("CategoryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ApplicationDTO> list(int pageNo, int pageSize) {
		List<ApplicationDTO> list = dao.list(pageNo, pageSize);
		log.info("CategoryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ApplicationDTO> search(ApplicationDTO dto) {
		List<ApplicationDTO> list = dao.search(dto);
		log.info("CategoryServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ApplicationDTO> search(ApplicationDTO dto, int pageNo, int pageSize) {
		List<ApplicationDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("CategoryServiceImpl search method end");
		return list;
	}
	
	@Override
	@Transactional
	public void Cancle(long id, String status) {
		dao.Cancle(id,status);		
		
	}

	@Override
	@Transactional
	public void Confirm(long id, String status) {
		dao.Confirm(id, status);
		
	}

}
