package com.springcontrolcomedor.app.dao;

import java.util.List;

import com.springcontrolcomedor.app.entity.Comensal;

public interface IComensalDao {

	public List<Comensal> findAll();

	public void save(Comensal comensal);

}
