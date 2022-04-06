package com.meli.co.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.co.dtos.AdnDto;
import com.meli.co.service.AdnService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Controller
@RequestMapping

public class AdnController {

	
	@Autowired
	public AdnService adnServices;
	
	
	
	@PostMapping("/mutant")
	@ApiOperation("Determina si es el ADN  es de un mutante ")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Este adn es de un mutante"),
		@ApiResponse(code = 403, message = "Este adn no es de un  mutante"),
		@ApiResponse(code = 400, message = "Este adn ya fue validado")
	})
	public ResponseEntity<Boolean> isMutante( @ApiParam(value = "Información del adn") @RequestBody AdnDto adnDto){
		boolean isMutante;
		ResponseEntity<Boolean>  response= new ResponseEntity<>(false,HttpStatus.FORBIDDEN);
		try {
		
			isMutante = adnServices.transaccion(adnDto);
			if (isMutante) {
				response = new ResponseEntity<>(true,HttpStatus.OK);
			}
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@GetMapping("/stats")
	public ResponseEntity<Map<String,Double>> stats(){
		return new ResponseEntity<>(adnServices.stats(),HttpStatus.OK);
	}

}
