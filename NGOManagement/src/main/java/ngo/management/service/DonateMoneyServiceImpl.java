package ngo.management.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ngo.management.dao.DonateMoneyDAOInt;
import ngo.management.dto.DonateMoneyDTO;
import ngo.management.exception.DuplicateRecordException;


@Service
public class DonateMoneyServiceImpl implements DonateMoneyServiceInt {

	private static Logger log = Logger.getLogger(DonateMoneyServiceImpl.class.getName());

	@Autowired
	private DonateMoneyDAOInt dao;


	@Override
	@Transactional
	public long add(DonateMoneyDTO dto) throws DuplicateRecordException {
		log.info("CategoryServiceImpl Add method start");
		DonateMoneyDTO existDTO = dao.findByName(dto.getDonorName());
		if (existDTO != null)
			throw new DuplicateRecordException("Application Already Exist");
		long pk = dao.add(dto);
		log.info("CategoryServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(DonateMoneyDTO dto) {
		log.info("CategoryServiceImpl Delete method start");
		dao.delete(dto);
		log.info("CategoryServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public DonateMoneyDTO findBypk(long pk) {
		log.info("CategoryServiceImpl findBypk method start");
		DonateMoneyDTO dto = dao.findBypk(pk);
		log.info("CategoryServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public DonateMoneyDTO findByName(String name) {
		log.info("CategoryServiceImpl findByCategoryName method start");
		DonateMoneyDTO dto = dao.findByName(name);
		log.info("CategoryServiceImpl findByCategoryName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(DonateMoneyDTO dto) throws DuplicateRecordException {
		log.info("CategoryServiceImpl update method start");
		DonateMoneyDTO existDTO = dao.findByName(dto.getDonorName());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.update(dto);
		log.info("CategoryServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<DonateMoneyDTO> list() {
		log.info("CategoryServiceImpl list method start");
		List<DonateMoneyDTO> list = dao.list();
		log.info("CategoryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<DonateMoneyDTO> list(int pageNo, int pageSize) {
		log.info("CategoryServiceImpl list method start");
		List<DonateMoneyDTO> list = dao.list(pageNo, pageSize);
		log.info("CategoryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<DonateMoneyDTO> search(DonateMoneyDTO dto) {
		log.info("CategoryServiceImpl search method start");
		List<DonateMoneyDTO> list = dao.search(dto);
		log.info("CategoryServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<DonateMoneyDTO> search(DonateMoneyDTO dto, int pageNo, int pageSize) {
		log.info("CategoryServiceImpl search method start");
		List<DonateMoneyDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("CategoryServiceImpl search method end");
		return list;
	}
	
	
}
