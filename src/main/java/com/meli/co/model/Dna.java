package com.meli.co.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Table(name = "Dna")
@Entity()
@Getter
@Setter
public class Dna implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "code", nullable = false, unique = true, length = 200 )
	private String code ;
	
	@Column(name = "isMutante", nullable = false )
	private boolean isMutante ;
	
	public Dna(){

	}

	public Dna(Long idAdn,String code) {
		this.id=idAdn;
		this.code = code;
	}


	

}
