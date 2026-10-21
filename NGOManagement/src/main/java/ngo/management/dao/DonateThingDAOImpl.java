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

import ngo.management.dto.DonateThingDTO;


@Repository
public class DonateThingDAOImpl implements DonateThingDAOInt{
	
	private static Logger log = Logger.getLogger(DonateMoneyDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(DonateThingDTO dto) {
		log.info("CategoryDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("CategoryDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(DonateThingDTO dto) {
		log.info("CategoryDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("CategoryDAOImpl Delete method End");

	}

	@Override
	public DonateThingDTO findBypk(long pk) {
		log.info("CategoryDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		DonateThingDTO dto = (DonateThingDTO) session.get(DonateThingDTO.class, pk);
		log.info("CategoryDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public DonateThingDTO findByName(String name) {
		log.info("CategoryDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(DonateThingDTO.class);
		criteria.add(Restrictions.eq("donorName", name));
		DonateThingDTO dto = (DonateThingDTO) criteria.uniqueResult();
		log.info("CategoryDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(DonateThingDTO dto) {
		log.info("CategoryDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("CategoryDAOImpl update method End");
	}

	@Override
	public List<DonateThingDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<DonateThingDTO> list(int pageNo, int pageSize) {
		log.info("CategoryDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<DonateThingDTO> query = session.createQuery("from DonateThingDTO", DonateThingDTO.class);
		List<DonateThingDTO> list = query.getResultList();
		log.info("CategoryDAOImpl List method End");
		return list;
	}

	@Override
	public List<DonateThingDTO> search(DonateThingDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<DonateThingDTO> search(DonateThingDTO dto, int pageNo, int pageSize) {
		log.info("CategoryDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from DonateThingDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getDonorName() != null && dto.getDonorName().length() > 0) {
				hql.append("and u.donorName like '%" + dto.getDonorName() + "%'");
			}
			

		}
		Query<DonateThingDTO> query = session.createQuery(hql.toString(), DonateThingDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<DonateThingDTO> list = query.getResultList();
		log.info("CategoryDAOImpl Search method End");
		return list;
	}




}
