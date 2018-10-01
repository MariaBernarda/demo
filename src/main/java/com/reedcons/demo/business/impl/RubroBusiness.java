package com.reedcons.demo.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reedcons.demo.business.BusinessException;
import com.reedcons.demo.business.IRubroBusiness;
import com.reedcons.demo.model.Producto;
import com.reedcons.demo.model.Rubro;
import com.reedcons.demo.model.exception.NotFoundException;
import com.reedcons.demo.model.persistence.RubroRepository;

@Service
public class RubroBusiness implements IRubroBusiness {

	@Autowired
	private RubroRepository rubroDAO;

	@Override
	public List<Rubro> getAll() throws BusinessException {
		try {
			return rubroDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}



	@Override
	public Rubro add(Rubro rubro) throws BusinessException {
		try {
			return rubroDAO.save(rubro);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public List<Rubro> search(String parte) throws BusinessException {
		try {
			return rubroDAO.findByRubroContaining(parte);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Rubro load(int id) throws BusinessException, NotFoundException {
		Optional<Rubro> r = null;

		try {
			r = rubroDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

		if (!r.isPresent())
			throw new NotFoundException();
		return r.get();

	}
	
	@Override
	public Rubro update(Rubro rubro) throws BusinessException, NotFoundException {
		Optional<Rubro> r = null;

		try {
			r = rubroDAO.findById(rubro.getId());
		} catch (Exception e) {
			throw new BusinessException(e);
		}

		if (!r.isPresent())
			throw new NotFoundException();

		try {
			return rubroDAO.save(rubro);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public void delete(Rubro rubro) throws BusinessException, NotFoundException {
		Optional<Rubro> p = null;

		try {
			p = rubroDAO.findById(rubro.getId());
		} catch (Exception e) {
			throw new BusinessException(e);
		}

		if (!p.isPresent())
			throw new NotFoundException();

		try {
			rubroDAO.delete(rubro);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}
	


}
