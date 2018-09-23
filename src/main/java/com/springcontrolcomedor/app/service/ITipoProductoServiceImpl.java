package com.springcontrolcomedor.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcontrolcomedor.app.dao.ITipoProductoDao;
import com.springcontrolcomedor.app.entity.TipoProducto;

@Service
public class ITipoProductoServiceImpl implements ITipoProductoService {

	@Autowired
	private ITipoProductoDao tipoProductoDao;

	@Override
	@Transactional(readOnly = true)
	public List<TipoProducto> findByActivos() {

		return tipoProductoDao.findByEliminado(0);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoProducto finByIdTipoProducto(Long idTipoProducto) {

		return tipoProductoDao.findByIdTipoProductoAndEliminado(idTipoProducto, 0);
	}

}
