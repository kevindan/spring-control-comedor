package com.springcontrolcomedor.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcontrolcomedor.app.dao.IComensalDao;
import com.springcontrolcomedor.app.entity.Comensal;

@Service
public class IComensalServiceImpl implements IComensalService {

	@Autowired
	private IComensalDao comensalDao;

	@Override
	@Transactional
	public void save(Comensal comensal) {
		comensalDao.save(comensal);
	}

	@Override
	@Transactional
	public void eliminarComensal(Long idComensal) {

		comensalDao.eliminaComensal(1, idComensal);
	}

	@Override
	@Transactional(readOnly = true)
	public Long cantidadComensales() {

		return comensalDao.countByEliminado(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Comensal> findByActivos(Pageable pageable) {

		return comensalDao.findByEliminado(0, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comensal> findByActivos() {

		return comensalDao.findByEliminado(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Comensal findOne(Long idComensal) {

		return comensalDao.findByIdComensalAndEliminado(idComensal, 0);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Comensal> findbySurname(String apellidoPaterno, Pageable pageable) {

		return comensalDao.findByApellidoPaternoLikeIgnoreCaseAndEliminado("%" + apellidoPaterno + "%", 0, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Comensal findByDni(String dni) {

		return comensalDao.findByDniAndEliminado(dni, 0);
	}

	@Override
	@Transactional(readOnly = true)
	public Comensal findByEmail(String email) {

		return comensalDao.findByEmailAndEliminado(email, 0);

	}

}
