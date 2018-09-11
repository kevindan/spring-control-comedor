package com.springcontrolcomedor.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springcontrolcomedor.app.entity.Comensal;

public interface IComensalService {

	//public List<Comensal> findAll();

	//public Page<Comensal> findAll(Pageable pageable);

	public Page<Comensal> findByActivos(Pageable pageable);

	public void save(Comensal comensal);

	public Comensal findOne(Long idComensal);

	//public void delete(Long id);
	
	public void eliminarComensal(Long idComensal);

	public Page<Comensal> findbySurname(String apellidoPaterno,Pageable pageable);

	public Comensal findByDni(String dni);
	
	public Comensal findByEmail(String email);

}
