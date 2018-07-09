package com.springcontrolcomedor.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcontrolcomedor.app.entity.Comensal;

@Repository
public class ComensalDaoImpl implements IComensalDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override

	public List<Comensal> findAll() {

		return em.createQuery("from Comensal").getResultList();
	}

	@Override
	@Transactional
	public void save(Comensal comensal) {

		if (comensal.getIdComensal() != null && comensal.getIdComensal() > 0) {

			em.merge(comensal);

		} else {

			em.persist(comensal);
		}
	}

	@Override
	public Comensal finOne(Long id) {

		return em.find(Comensal.class, id);
	}

	@Override
	public void delete(Long id) {
		em.remove(finOne(id));
	}

}
