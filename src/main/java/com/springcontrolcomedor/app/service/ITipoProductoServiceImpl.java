package com.springcontrolcomedor.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springcontrolcomedor.app.dao.ITipoProductoDao;
import com.springcontrolcomedor.app.entity.TipoProducto;

public class ITipoProductoServiceImpl implements ITipoProductoService {

	@Autowired
	private ITipoProductoDao tipoProductoDao;

	@Override
	public List<TipoProducto> findByActivos() {

		return tipoProductoDao.findByEliminado(0);
	}

}
