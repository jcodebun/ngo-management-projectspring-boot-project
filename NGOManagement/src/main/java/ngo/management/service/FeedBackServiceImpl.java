package ngo.management.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ngo.management.dao.FeedbackDAOInt;
import ngo.management.dto.FeedBackDTO;
import ngo.management.exception.DuplicateRecordException;


@Service
public class FeedBackServiceImpl implements FeedBackServiceInt{
	
	private static Logger log = Logger.getLogger(DonateThingServiceImpl.class.getName());

	@Autowired
	private FeedbackDAOInt dao;

	@Override
	@Transactional
	public long add(FeedBackDTO dto) throws DuplicateRecordException {
		log.info("CategoryServiceImpl Add method start");
		FeedBackDTO existDTO = dao.findByName(dto.getDonorName());
		if (existDTO != null)
			throw new DuplicateRecordException("Application Already Exist");
		long pk = dao.add(dto);
		log.info("CategoryServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(FeedBackDTO dto) {
		log.info("CategoryServiceImpl Delete method start");
		dao.delete(dto);
		log.info("CategoryServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public FeedBackDTO findBypk(long pk) {
		log.info("CategoryServiceImpl findBypk method start");
		FeedBackDTO dto = dao.findBypk(pk);
		log.info("CategoryServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public FeedBackDTO findByName(String name) {
		log.info("CategoryServiceImpl findByCategoryName method start");
		FeedBackDTO dto = dao.findByName(name);
		log.info("CategoryServiceImpl findByCategoryName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(FeedBackDTO dto) throws DuplicateRecordException {
		log.info("CategoryServiceImpl update method start");
		FeedBackDTO existDTO = dao.findByName(dto.getDonorName());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.update(dto);
		log.info("CategoryServiceImpl update method end");
		
	}

	@Override
	@Transactional
	public List<FeedBackDTO> list() {
		log.info("CategoryServiceImpl list method start");
		List<FeedBackDTO> list = dao.list();
		log.info("CategoryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<FeedBackDTO> list(int pageNo, int pageSize) {
		log.info("CategoryServiceImpl list method start");
		List<FeedBackDTO> list = dao.list(pageNo, pageSize);
		log.info("CategoryServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<FeedBackDTO> search(FeedBackDTO dto) {
		log.info("CategoryServiceImpl search method start");
		List<FeedBackDTO> list = dao.search(dto);
		log.info("CategoryServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<FeedBackDTO> search(FeedBackDTO dto, int pageNo, int pageSize) {
		log.info("CategoryServiceImpl search method start");
		List<FeedBackDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("CategoryServiceImpl search method end");
		return list;
	}

}
