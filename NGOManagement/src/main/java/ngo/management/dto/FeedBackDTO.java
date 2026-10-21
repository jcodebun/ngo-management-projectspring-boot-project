package ngo.management.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "feedback")
public class FeedBackDTO extends BaseDTO{
	
	@Column(name = "donar_name", length = 255)
	private String donorName;
	
	@Column(name = "donor_id", length = 255)
	private long donorId;
	
	@Column(name = "decriptionOne", length = 3500)
	private String decriptionOne;
	
	@Column(name = "decriptionTwo", length = 3500)
	private String decriptionTwo;
	
	@Column(name = "decriptionThree", length = 3500)
	private String decriptionThree;
	
	@Column(name = "decriptionFourth", length = 3500)
	private String decriptionFourth;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
