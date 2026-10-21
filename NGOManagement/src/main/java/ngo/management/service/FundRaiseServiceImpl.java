package ngo.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ngo.management.dao.FundRaiseDAOInt;
import ngo.management.dto.FundRaiseDTO;
import ngo.management.exception.DuplicateRecordException;


@Service
public class FundRaiseServiceImpl implements FundRaiseServiceInt{
	
	@Autowired
	private FundRaiseDAOInt dao;

	@Override
	@Transactional
	public long add(FundRaiseDTO dto) throws DuplicateRecordException {
//		DonateThingDTO existDTO = dao.findByName(dto.getDonorName());
//		if (existDTO != null)
//			throw new DuplicateRecordException("Application Already Exist");
		long pk = dao.add(dto);
		return pk;
	}

	@Override
	@Transactional
	public void delete(FundRaiseDTO dto) {
		dao.delete(dto);		
	}

	@Override
	@Transactional
	public FundRaiseDTO findBypk(long pk) {
		FundRaiseDTO dto = dao.findBypk(pk);
		return dto;
	}

	@Override
	@Transactional
	public FundRaiseDTO findByName(String name) {
		FundRaiseDTO dto = dao.findByName(name);
		return dto;
	}

	@Override
	@Transactional
	public void update(FundRaiseDTO dto) throws DuplicateRecordException {
		FundRaiseDTO existDTO = dao.findByName(dto.getVolunteerName());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.update(dto);
		
	}

	@Override
	@Transactional
	public List<FundRaiseDTO> list() {
		List<FundRaiseDTO> list = dao.list();
		return list;
	}

	@Override
	@Transactional
	public List<FundRaiseDTO> list(int pageNo, int pageSize) {
		List<FundRaiseDTO> list = dao.list(pageNo, pageSize);
		return list;
	}

	@Override
	@Transactional
	public List<FundRaiseDTO> search(FundRaiseDTO dto) {
		List<FundRaiseDTO> list = dao.search(dto);
		return list;
	}

	@Override
	@Transactional
	public List<FundRaiseDTO> search(FundRaiseDTO dto, int pageNo, int pageSize) {
		List<FundRaiseDTO> list = dao.search(dto, pageNo, pageSize);
		return list;
	}

}
