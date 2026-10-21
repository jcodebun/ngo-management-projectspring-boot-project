package ngo.management.service;

import java.util.List;

import ngo.management.dto.ApplicationDTO;
import ngo.management.exception.DuplicateRecordException;

public interface ApplicationServiceInt {
	
	
	public long add(ApplicationDTO dto) throws DuplicateRecordException;

	public void delete(ApplicationDTO dto);

	public ApplicationDTO findBypk(long pk);

	public ApplicationDTO findByName(String name);

	public void update(ApplicationDTO dto) throws DuplicateRecordException;

	public List<ApplicationDTO> list();

	public List<ApplicationDTO> list(int pageNo, int pageSize);

	public List<ApplicationDTO> search(ApplicationDTO dto);

	public List<ApplicationDTO> search(ApplicationDTO dto, int pageNo, int pageSize);
	
	public void Cancle(long id, String status);

	public void Confirm(long id, String status);

}
