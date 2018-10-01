package com.reedcons.demo.business;

import java.util.List;

import com.reedcons.demo.model.Rubro;
import com.reedcons.demo.model.exception.NotFoundException;

public interface IRubroBusiness {

	public List<Rubro> getAll() throws BusinessException;

	public Rubro add(Rubro rubro) throws BusinessException;

	public List<Rubro> search(String parte) throws BusinessException;
	
	public Rubro load(int id) throws BusinessException, NotFoundException;
	
	public Rubro update(Rubro rubro) throws BusinessException, NotFoundException;
	
	public void delete(Rubro rubro) throws BusinessException, NotFoundException;
	

}
