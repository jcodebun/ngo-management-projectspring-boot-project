package ngo.management.service;

import java.util.List;

import ngo.management.dto.FeedBackDTO;
import ngo.management.exception.DuplicateRecordException;

public interface FeedBackServiceInt {
	
	public long add(FeedBackDTO dto) throws DuplicateRecordException;

	public void delete(FeedBackDTO dto);

	public FeedBackDTO findBypk(long pk);

	public FeedBackDTO findByName(String name);

	public void update(FeedBackDTO dto) throws DuplicateRecordException;

	public List<FeedBackDTO> list();

	public List<FeedBackDTO> list(int pageNo, int pageSize);

	public List<FeedBackDTO> search(FeedBackDTO dto);

	public List<FeedBackDTO> search(FeedBackDTO dto, int pageNo, int pageSize);

}
