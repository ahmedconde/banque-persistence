package fr.m2ptsi.banque_persistence.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import fr.m2ptsi.banque_persistence.models.abstracts.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compte extends AbstractEntity {

	private String numero;
	private BigDecimal solde;
	
	@ManyToOne
	private Type type;
	
	@ManyToOne
	private Agence agence;
	
	@ManyToOne
	private Client client;
		
	public Compte(String numero, BigDecimal solde, Type type, Agence agence, Client client) {
		super();
		this.numero = numero;
		this.solde = solde;
		this.type = type;
		this.agence = agence;
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Compte other = (Compte) obj;
		return (numero.equalsIgnoreCase(other.numero));
			
	}

	@Override
	protected void initDatas() {
		super.initDatas();
	}
	
	
}
