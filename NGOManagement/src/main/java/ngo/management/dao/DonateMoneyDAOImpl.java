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

import ngo.management.dto.DonateMoneyDTO;


@Repository
public class DonateMoneyDAOImpl implements DonateMoneyDAOInt {

	private static Logger log = Logger.getLogger(DonateMoneyDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(DonateMoneyDTO dto) {
		log.info("CategoryDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("CategoryDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(DonateMoneyDTO dto) {
		log.info("CategoryDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("CategoryDAOImpl Delete method End");

	}

	@Override
	public DonateMoneyDTO findBypk(long pk) {
		log.info("CategoryDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		DonateMoneyDTO dto = (DonateMoneyDTO) session.get(DonateMoneyDTO.class, pk);
		log.info("CategoryDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public DonateMoneyDTO findByName(String name) {
		log.info("CategoryDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(DonateMoneyDTO.class);
		criteria.add(Restrictions.eq("donorName", name));
		DonateMoneyDTO dto = (DonateMoneyDTO) criteria.uniqueResult();
		log.info("CategoryDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(DonateMoneyDTO dto) {
		log.info("CategoryDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("CategoryDAOImpl update method End");
	}

	@Override
	public List<DonateMoneyDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<DonateMoneyDTO> list(int pageNo, int pageSize) {
		log.info("CategoryDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<DonateMoneyDTO> query = session.createQuery("from DonateMoneyDTO", DonateMoneyDTO.class);
		List<DonateMoneyDTO> list = query.getResultList();
		log.info("CategoryDAOImpl List method End");
		return list;
	}

	@Override
	public List<DonateMoneyDTO> search(DonateMoneyDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<DonateMoneyDTO> search(DonateMoneyDTO dto, int pageNo, int pageSize) {
		log.info("CategoryDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from DonateMoneyDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			System.out.println("DonarNam :"+dto.getDonorName());
			if (dto.getDonorName() != null && dto.getDonorName().length() > 0) {
				hql.append("and u.donorName like '%" + dto.getDonorName() + "%'");
			}
			

		}
		Query<DonateMoneyDTO> query = session.createQuery(hql.toString(), DonateMoneyDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<DonateMoneyDTO> list = query.getResultList();
		log.info("CategoryDAOImpl Search method End");
		return list;
	}



}
