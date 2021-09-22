package com.murilo.carrinhodecompras.models;

import java.util.Date;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Embeddable
@ToString
public class Relatorio {
	
	private Double precoTotal;
	
	private Date dataVenda;
	
	private String nome;
}
