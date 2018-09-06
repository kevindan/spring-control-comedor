package com.springcontrolcomedor.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springcontrolcomedor.app.entity.Comensal;

public interface IComensalDao extends PagingAndSortingRepository<Comensal, Long> {

	public Page<Comensal> findByApellidoPaternoLikeIgnoreCase(String apellidoPaterno, Pageable pageable);

	public Comensal findByDni(String dni);

}
