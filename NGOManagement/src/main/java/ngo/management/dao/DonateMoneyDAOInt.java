package ngo.management.dao;

import java.util.List;

import ngo.management.dto.DonateMoneyDTO;
import ngo.management.exception.DuplicateRecordException;

public interface DonateMoneyDAOInt {
	
	public long add(DonateMoneyDTO dto) throws DuplicateRecordException;

	public void delete(DonateMoneyDTO dto);

	public DonateMoneyDTO findBypk(long pk);

	public DonateMoneyDTO findByName(String name);

	public void update(DonateMoneyDTO dto) throws DuplicateRecordException;

	public List<DonateMoneyDTO> list();

	public List<DonateMoneyDTO> list(int pageNo, int pageSize);

	public List<DonateMoneyDTO> search(DonateMoneyDTO dto);

	public List<DonateMoneyDTO> search(DonateMoneyDTO dto, int pageNo, int pageSize);
	

}
