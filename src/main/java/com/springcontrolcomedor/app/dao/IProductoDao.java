package com.springcontrolcomedor.app.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.springcontrolcomedor.app.entity.Producto;

public interface IProductoDao extends PagingAndSortingRepository<Producto, Long> {

	public Long countByEliminado(int eliminado);

	public List<Producto> findByEliminado(int eliminado);

	public Page<Producto> findByEliminado(int eliminado, Pageable pageable);

	public Page<Producto> findByDescripcionLikeIgnoreCaseAndEliminado(String descripcion, int eliminado,
			Pageable pageable);

	public Producto findByIdProductoAndEliminado(Long idProducto, int eliminado);

	public Producto findByDescripcionLikeIgnoreCaseAndEliminado(String descripcion, int eliminado);

	@Modifying
	@Query("update Producto p set p.eliminado = ?1 where p.idProducto = ?2")
	public void eliminaProducto(int eliminar, Long idProducto);
	
	@Modifying
	@Query("update Producto p set p.alerta = ?1 where p.idProducto = ?2")
	public void actualizaAlerta(int alerta, Long idProducto);

	@Modifying
	@Query("update Producto p set p.stockActual = ?1 where p.idProducto = ?2")
	public void actualizaStock(int stockActual, Long idProducto);
	
	@Modifying
	@Query("update Producto p set p.imagen = ?1 where p.idProducto = ?2")
	public void guardarImagen(String imagen, Long idProducto);
}
