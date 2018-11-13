package fr.m2ptsi.banque_persistence.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Agence extends AbstractEntity {

	private String codeGuichet;
	private String nom;
	
	// plusieurs agences compose une banque
	
	@ManyToOne
	@JoinColumn(name = "banqueId")
	private Banque banque;
	
	@OneToMany(mappedBy = "agence")
	private Set<Compte> listeComptes = new HashSet<>();;
	
	public Agence(String codeGuichet, String nom, Banque banque, Set<Compte> listeComptes) {
		super();
		this.codeGuichet = codeGuichet;
		this.nom = nom;
		this.banque = banque;
		this.listeComptes = listeComptes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeGuichet == null) ? 0 : codeGuichet.hashCode());
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
		final Agence other = (Agence) obj;
		return codeGuichet.equalsIgnoreCase(other.codeGuichet) && nom.equalsIgnoreCase(other.nom);
	}
	
		
	@Override
	protected void initDatas() {
		super.initDatas();
		nom = BanqueUtils.capitalize(nom);
		codeGuichet = BanqueUtils.capitalize(codeGuichet);
	}
	
	@PreUpdate
	@PostLoad
	private void initDonnees() {
		nom = BanqueUtils.capitalize(nom);
		codeGuichet = BanqueUtils.capitalize(codeGuichet);
	}
}
