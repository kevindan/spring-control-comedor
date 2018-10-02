package com.springcontrolcomedor.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springcontrolcomedor.app.entity.Producto;

public interface IProductoService {
	// Método para contar todos los comensales activos
	public Long cantidadProductos();

	// Metodo para listar todos los comensales activos sin paginar
	public List<Producto> findByActivos();

	// public Page<Comensal> findAll(Pageable pageable);
	public Page<Producto> findByActivos(Pageable pageable);

	public void save(Producto producto);

	public Producto findOne(Long idProducto);

	// public void delete(Long id);
	public void eliminarProducto(Long idProducto);

	public void actualizaAlerta(int alerta, Long idProducto);
	
	public void actualizaStock(int stockActual, Long idProducto);
	
	public Page<Producto> findByDescripcion(String descripcion, Pageable pageable);

	public Producto findByDescripcion(String descripcion);

}
