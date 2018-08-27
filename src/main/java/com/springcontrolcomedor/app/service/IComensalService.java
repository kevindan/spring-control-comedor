package com.springcontrolcomedor.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springcontrolcomedor.app.entity.Comensal;

public interface IComensalService {

	public List<Comensal> findAll();

	public Page<Comensal> findAll(Pageable pageable);

	public void save(Comensal comensal);

	public Comensal finOne(Long id);

	public void delete(Long id);

	public Page<Comensal> findbySurname(String apellidoPaterno, Pageable pageable);

}
