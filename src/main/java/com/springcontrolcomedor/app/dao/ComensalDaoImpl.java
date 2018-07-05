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
		// TODO Auto-generated method stub
		return em.createQuery("from Comensal").getResultList();
	}

	@Override
	@Transactional
	public void save(Comensal comensal) {
		em.persist(comensal);
	}

}
