package com.springcontrolcomedor.app.service;

import java.util.List;

import com.springcontrolcomedor.app.entity.Comensal;

public interface IComensalService {
	
	public List<Comensal> findAll();

	public void save(Comensal comensal);
	
	public Comensal finOne(Long id);
	
	public void delete(Long id);


}
