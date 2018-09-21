package com.springcontrolcomedor.app.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springcontrolcomedor.app.entity.TipoProducto;

public interface ITipoProductoDao extends PagingAndSortingRepository<TipoProducto, Long> {
	// Método para listar todos los tipos de producto que se encuentren activos
	public List<TipoProducto> findByEliminado(int eliminado);

	public TipoProducto findByIdTipoProductoAndEliminado(Long idTipoProducto, int eliminado);

}
