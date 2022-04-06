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
		double radio =  mutante/humanos;
		result.put("count_mutant_dna",mutante );
		result.put("count_humanos_dna",  humanos);
		result.put("ratio", radio);
		return result;
	}

	public boolean transaccion(AdnDto adnDto) throws Exception{
		String dnaCode = converterString( adnDto.getDna());
		
		boolean isMutante =false;
		if (dnaCode.matches("[ATCG]+")) {
			boolean exists = existsAdn(dnaCode);
			if (!exists) {
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
		Optional<Dna> optionalHumano = adnRepository.findByCode(vectorAdn);
		return optionalHumano.isPresent();

	}

	public boolean isMutante(String[] dnaVector) {

		byte sequencesfound = 0;
		char []  bldmatrizDiagonal = new char[dnaVector.length];
		for (int i=0; i < dnaVector.length; i++) {
			char [] bldarrayLeftRight= new char[dnaVector.length];
			char [] bldarrayUpDown = new char[dnaVector.length];
			if(dnaVector[i].length()==dnaVector.length) {
				for (int j = 0; j <dnaVector.length; j ++) {

					bldarrayUpDown[j]=(dnaVector[i].charAt (j));	
					bldarrayLeftRight[j]=(dnaVector[j].charAt (i));	
					if (i==j) {
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

	private byte possibleMutante(char[] aux ) {
		char initial = aux[0];
		byte counterNitrogen = 0;
		byte possibleMutant =0;
		for (int x = 1; x <aux.length; x ++) {
			char sequence = aux[x];
			if (initial==sequence) {
				counterNitrogen ++;
				if (counterNitrogen==3) {
					possibleMutant ++;
					initial ='9';
					counterNitrogen=1;
				}	
			}else {
				initial= sequence;	
				counterNitrogen= 0;
			}
		}


		return possibleMutant ;
	}

}
