package com.springcontrolcomedor.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springcontrolcomedor.app.entity.Comensal;

public interface IComensalDao extends PagingAndSortingRepository<Comensal, Long> {

}
