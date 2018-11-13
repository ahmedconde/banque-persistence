package fr.m2ptsi.banque_persistence.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import fr.m2ptsi.banque_persistence.models.abstracts.AbstractEntity;
import fr.m2ptsi.banque_persistence.models.enums.TypeCompte;
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
public class Type extends AbstractEntity {

	@Enumerated(value = EnumType.STRING)
	private TypeCompte libelle;
	
	@OneToMany(mappedBy ="type")
	private Set<Compte> listeComptes = new HashSet<>();
	
	public Type() {
		super();
	}
		
	public Type(TypeCompte libelle, Set<Compte> listeComptes) {
		super();
		this.libelle = libelle;
		this.listeComptes = listeComptes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		return libelle.toString().equalsIgnoreCase(other.libelle.toString());
	}

	public void add(Compte compte) {
		if(compte != null) {
			listeComptes.add(compte);
		}
	}
	
	@Override
	protected void initDatas() {
		super.initDatas();
	}

	public TypeCompte getLibelle() {
		return libelle;
	}

	public void setLibelle(TypeCompte libelle) {
		this.libelle = libelle;
	}

	public Set<Compte> getListeComptes() {
		return listeComptes;
	}

	public void setListeComptes(Set<Compte> listeComptes) {
		this.listeComptes = listeComptes;
	}
	
	
}
