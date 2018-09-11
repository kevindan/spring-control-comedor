package com.springcontrolcomedor.app.service;

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
	public Comensal findOne(Long idComensal) {

		return comensalDao.findByIdComensalAndEliminado(idComensal, 0);
	}

	@Override
	public Page<Comensal> findbySurname(String apellidoPaterno, Pageable pageable) {

		return comensalDao.findByApellidoPaternoLikeIgnoreCaseAndEliminado("%" + apellidoPaterno + "%", 0, pageable);
	}

	@Override
	public Comensal findByDni(String dni) {

		return comensalDao.findByDniAndEliminado(dni, 0);
	}

	@Override
	public Page<Comensal> findByActivos(Pageable pageable) {

		return comensalDao.findByEliminado(0, pageable);
	}

	@Override
	public Comensal findByEmail(String email) {

		return comensalDao.findByEmailAndEliminado(email, 0);
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<Comensal> findAll() {
//
//		return (List<Comensal>) comensalDao.findAll();
//	}

//	@Override
//	@Transactional
//	public void delete(Long id) {
//		comensalDao.deleteById(id);
//
//	}

//	@Override
//	public Page<Comensal> findAll(Pageable pageable) {
//
//		return comensalDao.findAll(pageable);
//	}

}
