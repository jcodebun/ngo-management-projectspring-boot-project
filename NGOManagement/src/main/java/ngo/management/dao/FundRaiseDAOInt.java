package ngo.management.dao;

import java.util.List;

import ngo.management.dto.FundRaiseDTO;
import ngo.management.exception.DuplicateRecordException;

public interface FundRaiseDAOInt {
	
	public long add(FundRaiseDTO dto) throws DuplicateRecordException;

	public void delete(FundRaiseDTO dto);

	public FundRaiseDTO findBypk(long pk);

	public FundRaiseDTO findByName(String name);

	public void update(FundRaiseDTO dto) throws DuplicateRecordException;

	public List<FundRaiseDTO> list();

	public List<FundRaiseDTO> list(int pageNo, int pageSize);

	public List<FundRaiseDTO> search(FundRaiseDTO dto);

	public List<FundRaiseDTO> search(FundRaiseDTO dto, int pageNo, int pageSize);

}
