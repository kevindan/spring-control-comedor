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
	@Transactional(readOnly = true)
	public List<Comensal> findAll() {

		return (List<Comensal>) comensalDao.findAll();
	}

	@Override
	@Transactional
	public void save(Comensal comensal) {
		comensalDao.save(comensal);

	}

	@Override
	public Comensal finOne(Long id) {

		return comensalDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		comensalDao.deleteById(id);

	}

	@Override
	public Page<Comensal> findAll(Pageable pageable) {

		return comensalDao.findAll(pageable);
	}

	@Override
	public Page<Comensal> findbySurname(String apellidoPaterno, int eliminado,Pageable pageable) {

		return comensalDao.findByApellidoPaternoLikeIgnoreCaseAndEliminado("%" + apellidoPaterno + "%", eliminado,pageable);
	}

	@Override
	public Comensal findByDni(String dni) {

		return comensalDao.findByDni(dni);
	}

	@Override
	public Page<Comensal> findByActivos(int eliminado, Pageable pageable) {
		
		return comensalDao.findByEliminado(eliminado, pageable);
	}

	@Override
	@Transactional
	public void eliminarComensal(int eliminar, Long idComensal) {
		
		comensalDao.eliminaComensal(eliminar, idComensal);		
	}

}
