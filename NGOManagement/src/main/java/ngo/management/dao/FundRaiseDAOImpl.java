package ngo.management.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ngo.management.dto.FundRaiseDTO;
import ngo.management.exception.DuplicateRecordException;


@Repository
public class FundRaiseDAOImpl implements FundRaiseDAOInt{
	
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(FundRaiseDTO dto) throws DuplicateRecordException {
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		return pk;
	}

	@Override
	public void delete(FundRaiseDTO dto) {
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}

	@Override
	public FundRaiseDTO findBypk(long pk) {
		Session session = entityManager.unwrap(Session.class);
		FundRaiseDTO dto = (FundRaiseDTO) session.get(FundRaiseDTO.class, pk);
		return dto;
	}

	@Override
	public FundRaiseDTO findByName(String name) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FundRaiseDTO.class);
		criteria.add(Restrictions.eq("volunteerName", name));
		FundRaiseDTO dto = (FundRaiseDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(FundRaiseDTO dto) throws DuplicateRecordException {
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
	}

	@Override
	public List<FundRaiseDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<FundRaiseDTO> list(int pageNo, int pageSize) {
		Session session = entityManager.unwrap(Session.class);
		Query<FundRaiseDTO> query = session.createQuery("from FundRaiseDTO", FundRaiseDTO.class);
		List<FundRaiseDTO> list = query.getResultList();
		return list;
	}

	@Override
	public List<FundRaiseDTO> search(FundRaiseDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<FundRaiseDTO> search(FundRaiseDTO dto, int pageNo, int pageSize) {
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from FundRaiseDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getVolunteerName() != null && dto.getVolunteerName().length() > 0) {
				hql.append("and u.volunteerName like '%" + dto.getVolunteerName() + "%'");
			}
			

		}
		Query<FundRaiseDTO> query = session.createQuery(hql.toString(), FundRaiseDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<FundRaiseDTO> list = query.getResultList();
		return list;
	}

}
