package ngo.management.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ngo.management.dto.ApplicationDTO;
import ngo.management.dto.FeedBackDTO;
import ngo.management.exception.DuplicateRecordException;


@Repository
public class ApplicationDAOImpl implements ApplicationDAOInt{
	
	private static Logger log = Logger.getLogger(DonateMoneyDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(ApplicationDTO dto) throws DuplicateRecordException {
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("CategoryDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ApplicationDTO dto) {
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		
	}

	@Override
	public ApplicationDTO findBypk(long pk) {
		Session session = entityManager.unwrap(Session.class);
		ApplicationDTO dto = (ApplicationDTO) session.get(ApplicationDTO.class, pk);
		log.info("CategoryDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ApplicationDTO findByName(String name) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ApplicationDTO.class);
		criteria.add(Restrictions.eq("volunteerName", name));
		ApplicationDTO dto = (ApplicationDTO) criteria.uniqueResult();
		log.info("CategoryDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ApplicationDTO dto) throws DuplicateRecordException {
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
	}

	@Override
	public List<ApplicationDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ApplicationDTO> list(int pageNo, int pageSize) {
		Session session = entityManager.unwrap(Session.class);
		Query<ApplicationDTO> query = session.createQuery("from ApplicationDTO", ApplicationDTO.class);
		List<ApplicationDTO> list = query.getResultList();
		log.info("CategoryDAOImpl List method End");
		return list;
	}

	@Override
	public List<ApplicationDTO> search(ApplicationDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ApplicationDTO> search(ApplicationDTO dto, int pageNo, int pageSize) {
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from ApplicationDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getVolunteerName() != null && dto.getVolunteerName().length() > 0) {
				hql.append("and u.volunteerName like '%" + dto.getVolunteerName() + "%'");
			}
			if (dto.getVolunteerId()> 0) {
				hql.append("and u.volunteerId = " + dto.getVolunteerId());
			}

			

		}
		Query<ApplicationDTO> query = session.createQuery(hql.toString(), ApplicationDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<ApplicationDTO> list = query.getResultList();
		log.info("CategoryDAOImpl Search method End");
		return list;
	}
	
	@Override
	@Transactional
	public void Cancle(long id, String status) {
		Session session = (Session) entityManager.unwrap(Session.class);
		Query<ApplicationDTO> query = session.createQuery("update ApplicationDTO set status = 'cancle'  where id= "+id+"");
	    query.executeUpdate();	
	}

	@Override
	@Transactional
	public void Confirm(long id, String status) {
		Session session = (Session) entityManager.unwrap(Session.class);
		Query<ApplicationDTO> query = session.createQuery("update ApplicationDTO set status = 'confirm'  where id= "+id+"");
	    query.executeUpdate();	
		
	}

}
