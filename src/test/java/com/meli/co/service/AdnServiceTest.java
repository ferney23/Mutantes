package com.meli.co.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.co.dtos.AdnDto;
import com.meli.co.model.Dna;
import com.meli.co.repository.AdnRepository;



@ExtendWith(MockitoExtension.class)
 class AdnServiceTest {

	@InjectMocks
	private AdnService adnService;
	@Mock
	private AdnRepository adnRepository;
	
	@Test
	 void isMutante() {	
		String[] vector = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		assertTrue(adnService.isMutante(vector));
	}
	
	@Test
	 void existsRegister() {
		String  vector = "ATGCGA";
		assertFalse(adnService.existsAdn(vector));
	}
	
	@Test
	 void findAll() {
		assertNotNull(adnService.stats());
	}
	
	@Test
	 void save() {
		String code = "ATGCGA";
		boolean isMutante = true;
		Dna adn = new Dna();
		adn.setId((long) 1);
		adn.setMutante(false);
		adn.setCode(code);
		
		adnService.save(code, isMutante);
	}
	
	@Test
	 void saveNull() {
		String sangre = "ATGCGA";
		boolean isMutante = true;
		adnService.save(sangre, isMutante);
	}
	
	@Test
	void Transaccion() throws Exception {
		String[] vector = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		AdnDto adn = new AdnDto();
		adn.setDna(vector);
		assertTrue(adnService.transaccion(adn));
	
		;
	}
	
}
