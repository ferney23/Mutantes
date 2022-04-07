package com.meli.co.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.co.dtos.AdnDto;
import com.meli.co.model.Dna;
import com.meli.co.repository.AdnRepository;


@Service
public class AdnService {
	@Autowired
	private AdnRepository adnRepository;

	public Map<String,Double> stats(){
		Map<String, Double> result = new HashMap<>();
		
		double humanos  =adnRepository.findAll().size();
		double mutante = adnRepository.getIsMutante(true);
		
		//Radio es igual a ejemplo de 100 humanos guardados 40 de ellos son mutantes
		double radio =  mutante/humanos;
		result.put("count_mutant_dna",mutante );
		result.put("count_humanos_dna",  humanos);
		result.put("ratio", radio);
		return result;
	}

	public boolean transaccion(AdnDto adnDto) throws Exception{
		//Convertimos a String el vector de String que entra esto se hace en el metodo converterString
		
		String dnaCode = converterString( adnDto.getDna());
		
		boolean isMutante =false;
		//Verificamos que solo esten estas letras [ATCG] dnaCode.matches("[ATCG]+")
		if (dnaCode.matches("[ATCG]+")) {
		// Verificamos que el registro no exista en la base de datos 
			boolean exists = existsAdn(dnaCode);
			if (!exists) {
				// Si no existe en base de datos , verificamos que si es mutante o no 
				// Metodo solicitado por magneto isMutante = isMutante(adnDto.getDna());
				isMutante = isMutante(adnDto.getDna());
				save(dnaCode,isMutante);
			}else {
				throw new Exception("Registro ya existe");
			}
		}
		return isMutante;
	}


	public String converterString(String [] dnaCode) {
		StringBuilder cadena = new StringBuilder();
		for (int x=0;x<dnaCode.length;x++){
			cadena =cadena.append(dnaCode[x]);
		}
		return cadena.toString();
	}

	public Dna save(String  codeDna ,boolean isMutante) {
		Dna adn = new Dna();
		adn.setCode(codeDna);
		adn.setMutante(isMutante);
		return adnRepository.saveAndFlush(adn);
	}
	public boolean existsAdn(String vectorAdn) {
		//Verifico que si el registro existe en bases de datos
		Optional<Dna> optionalHumano = adnRepository.findByCode(vectorAdn);
		return optionalHumano.isPresent();

	}

	public boolean isMutante(String[] dnaVector) {
		//Comenzamos el metodo solicitado magneto
		// sequencesfound las veces que se encuentren secuencias repetidas aumenta este contador
		byte sequencesfound = 0;
		// Creamos  el vector para almacenar la oblicua 
		char []  bldmatrizDiagonal = new char[dnaVector.length];
		
		for (int i=0; i < dnaVector.length; i++) {
			// Tenemos un vector para la lectura 
			
			char [] bldarrayLeftRight= new char[dnaVector.length];
			char [] bldarrayUpDown = new char[dnaVector.length];
			if(dnaVector[i].length()==dnaVector.length) {
				for (int j = 0; j <dnaVector.length; j ++) {
					bldarrayLeftRight[j]=(dnaVector[i].charAt (j));	
					bldarrayUpDown[j]=(dnaVector[j].charAt (i));
					if (i==j) {
						//Llenado la matriz oblicua
						bldmatrizDiagonal[i]=(dnaVector[i].charAt (j));
					}
					
				}
				
				sequencesfound+=possibleMutante(bldarrayUpDown);
				sequencesfound+=possibleMutante(bldarrayLeftRight);
				if (sequencesfound>=2) {
					break;
				}	
			}
			else {
				return false;
			}

		}

		sequencesfound+=possibleMutante(bldmatrizDiagonal);

		return sequencesfound>=2;
	}

	public byte possibleMutante(char[] code ) {
		//Metodo ´para encontrar las secuencias 
		char initial = code[0];
		byte counterNitrogen = 0;
		byte possibleMutant =0;
		for (int x = 1; x <code.length; x ++) {
			char sequence = code[x];
			if (initial==sequence) {
				counterNitrogen ++;
				if (counterNitrogen==3) {
					// En una fila pueden haber mas de una secuencia ejemplo AAAABBBB entonces aca controlo
					possibleMutant ++;
					initial ='9';
					counterNitrogen=1;
				}	
			}else {
				initial= sequence;	
				//Cada no coincida reinicia el contador los caracteres consecutivos 
				counterNitrogen= 0;
			}
		}
		return possibleMutant ;
	}

}
