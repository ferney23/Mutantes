package com.meli.co.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.co.dtos.AdnDto;
import com.meli.co.service.AdnService;

@ExtendWith(MockitoExtension.class)
 class AdnControllerTest {

	@InjectMocks
	private AdnController adnController;
	
	@Mock
	private AdnService adnService;
	
	@Test
	 void isMutante() throws Exception {
		String[] dnaVector = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		AdnDto adnDto = new AdnDto();
		adnDto.setDna(dnaVector);
		when(adnService.transaccion(adnDto)).thenReturn(true);
		
		assertNotNull(adnController.isMutante(adnDto));
	}
	
	@Test 
	void stats() {
		assertNotNull(adnController.stats());
	}
}
