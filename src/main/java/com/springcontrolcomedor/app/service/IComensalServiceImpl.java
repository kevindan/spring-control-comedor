package com.springcontrolcomedor.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcontrolcomedor.app.dao.IComensalDao;
import com.springcontrolcomedor.app.entity.Comensal;

@Service
public class IComensalServiceImpl implements IComensalService {

	@Autowired
	private IComensalDao comensalDao;

	@Override
	@Transactional(readOnly = true)
	public List<Comensal> findAll() {

		return comensalDao.findAll();
	}

	@Override
	@Transactional
	public void save(Comensal comensal) {
		comensalDao.save(comensal);

	}

	@Override
	public Comensal finOne(Long id) {

		return comensalDao.finOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		comensalDao.delete(id);

	}

}
