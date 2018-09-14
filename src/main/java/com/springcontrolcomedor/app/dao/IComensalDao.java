package com.springcontrolcomedor.app.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springcontrolcomedor.app.entity.Comensal;

public interface IComensalDao extends PagingAndSortingRepository<Comensal, Long> {
	/*
	 * Esta clase implementa los métodos save = grabar/guardar; merge = actualizar y
	 * la lista paginable;
	 */
	//Método para pasar contra todos los comensales activos
	public Long countByEliminado(int eliminado);
	// Mtetodo para listar los comensales activos sin paginar
	public List<Comensal> findByEliminado(int eliminado);

	// Método para listar comensales que se encuentran activos
	public Page<Comensal> findByEliminado(int eliminado, Pageable pageable);

	// Método para filtrar comensales por apellido paterno
	public Page<Comensal> findByApellidoPaternoLikeIgnoreCaseAndEliminado(String apellidoPaterno, int eliminado,
			Pageable pageable);

	public Comensal findByIdComensalAndEliminado(Long idComensal, int eliminado);

	// método para buscar la existencia de un dni
	public Comensal findByDniAndEliminado(String dni, int eliminado);

	// Método para buscar la existencia de un email
	public Comensal findByEmailAndEliminado(String email, int eliminado);

	// Método para eliminar comensal de manera lógica
	@Modifying
	@Query("update Comensal c set c.eliminado = ?1 where c.idComensal = ?2")
	public void eliminaComensal(int eliminar, Long idComensal);

}
