package com.reedcons.demo.model.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reedcons.demo.business.BusinessException;
import com.reedcons.demo.model.Rubro;

@Repository
public interface RubroRepository extends JpaRepository<Rubro, Integer>{
	
	public List<Rubro> findByRubroContaining(String q) throws BusinessException;

}
