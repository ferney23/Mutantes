package com.meli.co.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meli.co.model.Dna;
@Repository
public interface AdnRepository extends JpaRepository<Dna, Long>  {
	Optional<Dna> findByCode(String code);
	@Query("select count(*) from Dna where isMutante=?1")
	Long getIsMutante(boolean mutante);
	
	
	

}
