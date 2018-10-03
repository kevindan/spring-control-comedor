package com.springcontrolcomedor.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springcontrolcomedor.app.dao.IProductoDao;
import com.springcontrolcomedor.app.entity.Producto;

@Service
public class IProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public Long cantidadProductos() {

		return productoDao.countByEliminado(0);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByActivos() {

		return productoDao.findByEliminado(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findByActivos(Pageable pageable) {

		return productoDao.findByEliminado(0, pageable);
	}

	@Override
	@Transactional
	public void save(Producto producto) {

		productoDao.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findOne(Long idProducto) {

		return productoDao.findByIdProductoAndEliminado(idProducto, 0);
	}

	@Override
	@Transactional
	public void eliminarProducto(Long idProducto) {
		productoDao.eliminaProducto(1, idProducto);
	}

	@Override
	@Transactional
	public void actualizaAlerta(int alerta, Long idProducto) {
		productoDao.actualizaAlerta(alerta, idProducto);

	}

	@Override
	@Transactional
	public void actualizaStock(int stockActual, Long idProducto) {
		productoDao.actualizaStock(stockActual, idProducto);
	}

	@Override
	@Transactional
	public void guardarImagen(String imagen, Long idProducto) {

		productoDao.guardarImagen(imagen, idProducto);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findByDescripcion(String descripcion, Pageable pageable) {

		return productoDao.findByDescripcionLikeIgnoreCaseAndEliminado("%" + descripcion + "%", 0, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findByDescripcion(String descripcion) {

		return productoDao.findByDescripcionLikeIgnoreCaseAndEliminado(descripcion, 0);
	}

}
