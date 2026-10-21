package ngo.management.form;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import ngo.management.dto.BaseDTO;


@Getter
@Setter
public abstract class BaseForm {

	protected long id;

	protected int pageNo = 1;

	protected int pageSize = 10;

	protected long[] ids;

	protected int listsize;

	protected int total;

	private String operation;
	private int pagenosize;

	

	public abstract BaseDTO getDTO();

	public abstract void populate(BaseDTO bDto);

}
