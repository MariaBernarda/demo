package com.reedcons.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reedcons.demo.business.BusinessException;
import com.reedcons.demo.business.IUserBusiness;
import com.reedcons.demo.model.Producto;
import com.reedcons.demo.model.exception.NotFoundException;
import com.reedcons.demo.model.util.UserDTO;

@RestController
@RequestMapping(Constantes.URL_USERS)
public class UsersRestController {

	@Autowired
	private IUserBusiness userBusiness;

	
	//curl -X POST 'http://localhost:8080/dologin' -H 'Content-Type: application/x-www-form-urlencoded' -d 'username=admin&password=password' -c cookies.txt
	//curl 'http://localhost:8080/api/v1/users/sintetico/enabled' -b cookies.txt -v
	@GetMapping(value = "/sintetico/enabled")
	public ResponseEntity<List<UserDTO>> listSinteticoEnabled() {
		try {
			return new ResponseEntity<List<UserDTO>>(userBusiness.listUsersSintetico(true), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping(value = "/sintetico/disabled")
	public ResponseEntity<List<UserDTO>> listSinteticoDisabled() {
		try {
			return new ResponseEntity<List<UserDTO>>(userBusiness.listUsersSintetico(false), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
		
	@PreAuthorize("#username == authentication.principal.username")
	@RequestMapping(value = { "", "/setPassword" }, method = RequestMethod.PUT)	
	public ResponseEntity<Object> setPassword(@RequestParam("password")  String password, @RequestParam("username")  String username) {
		try {
			userBusiness.setPassword(password,username);
			return new ResponseEntity(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

}
