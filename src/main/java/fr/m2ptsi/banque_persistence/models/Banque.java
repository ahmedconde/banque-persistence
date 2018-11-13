package fr.m2ptsi.banque_persistence.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;

import fr.m2ptsi.banque_persistence.models.abstracts.AbstractEntity;
import fr.m2ptsi.banque_persistence.tools.BanqueUtils;
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
public class Banque extends AbstractEntity {

	private String code;
	private String nom;
	
	// une banque est compose de plusieurs agences
	
	@OneToMany(mappedBy = "banque")
	private Set<Agence> listeAgences = new HashSet<>();
	
	public Banque(String code, String nom, Set<Agence> listeAgences) {
		super();
		this.code = code;
		this.nom = nom;
		this.listeAgences = listeAgences;
	}
	
	public Banque() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		final Banque other = (Banque) obj;
		return code.equalsIgnoreCase(other.code) && nom.equalsIgnoreCase(other.nom);
	}
	
	// ajout d'une nouvelle agence à la liste d'agence de la banque
	
	public void add(Agence agence) {
		if(agence != null) {
			listeAgences.add(agence);
		}
	}
	
	@Override
	protected void initDatas() {
		super.initDatas();
		nom = BanqueUtils.capitalize(nom);
		code = BanqueUtils.capitalize(code);
	}
	
	// initialisation des données de la banque après chargement et avant modification
	
	@PreUpdate
	@PostLoad
	private void initDonnees() {
		nom = BanqueUtils.capitalize(nom);
		code = BanqueUtils.capitalize(code);
	}
	
	
}
