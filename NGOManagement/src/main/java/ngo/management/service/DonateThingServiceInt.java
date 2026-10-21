package ngo.management.service;

import java.util.List;


import ngo.management.dto.DonateThingDTO;
import ngo.management.exception.DuplicateRecordException;



public interface DonateThingServiceInt {

	public long add(DonateThingDTO dto) throws DuplicateRecordException;

	public void delete(DonateThingDTO dto);

	public DonateThingDTO findBypk(long pk);

	public DonateThingDTO findByName(String name);

	public void update(DonateThingDTO dto) throws DuplicateRecordException;

	public List<DonateThingDTO> list();

	public List<DonateThingDTO> list(int pageNo, int pageSize);

	public List<DonateThingDTO> search(DonateThingDTO dto);

	public List<DonateThingDTO> search(DonateThingDTO dto, int pageNo, int pageSize);
	
}
