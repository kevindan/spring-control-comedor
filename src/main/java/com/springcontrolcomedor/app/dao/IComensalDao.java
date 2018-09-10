package com.springcontrolcomedor.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springcontrolcomedor.app.entity.Comensal;

public interface IComensalDao extends PagingAndSortingRepository<Comensal, Long> {

	public Page<Comensal> findByEliminado(int eliminado, Pageable pageable);

	public Page<Comensal> findByApellidoPaternoLikeIgnoreCaseAndEliminado(String apellidoPaterno,int eliminado ,Pageable pageable);
	
	public Comensal findByDni(String dni);
	
	@Modifying
	@Query("update Comensal c set c.eliminado = ?1 where c.idComensal = ?2")
	public void eliminaComensal(int eliminar, Long idComensal);
}
