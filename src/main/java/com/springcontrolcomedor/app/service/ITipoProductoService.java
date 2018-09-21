package com.springcontrolcomedor.app.service;

import java.util.List;

import com.springcontrolcomedor.app.entity.TipoProducto;

public interface ITipoProductoService {
	//Método para listar los tipo de productos que se encuentren activos
	public List<TipoProducto> findByActivos();
	
	public TipoProducto finByIdTipoProducto(Long idTipoProducto);

}
