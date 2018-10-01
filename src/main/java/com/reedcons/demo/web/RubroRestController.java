package com.reedcons.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reedcons.demo.business.BusinessException;
import com.reedcons.demo.business.IRubroBusiness;
import com.reedcons.demo.model.Rubro;
import com.reedcons.demo.model.exception.NotFoundException;

@RestController
@RequestMapping("/rubros")
public class RubroRestController {

	@Autowired
	private IRubroBusiness rubroBusiness;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Rubro>> all() {
		try {
			return new ResponseEntity(rubroBusiness.getAll(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<Object> add(@RequestBody Rubro rubro) {
		try {
			Rubro p = rubroBusiness.add(rubro);
			HttpHeaders headers = new HttpHeaders();
			headers.add("location", "/rubros/" + p.getId());
			return new ResponseEntity(headers, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Rubro> load(@PathVariable("id") int id) {
		try {
			return new ResponseEntity(rubroBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Rubro rubro) {
		try {
			rubroBusiness.update(rubro);
			return new ResponseEntity(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Rubro> delete(@PathVariable("id") int id) {
		try {
			Rubro r = new Rubro();
			r.setId(id);
			rubroBusiness.delete(r);
			return new ResponseEntity(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

}
