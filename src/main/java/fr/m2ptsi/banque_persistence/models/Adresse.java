package fr.m2ptsi.banque_persistence.models;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;

import fr.m2ptsi.banque_persistence.models.abstracts.AbstractEntity;
import fr.m2ptsi.banque_persistence.tools.BanqueUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Adresse extends AbstractEntity {
	
	private String voie;
	private String codePostal;
	private String ville;
	private String pays;
	
	public Adresse(String voie, String codePostal, String ville, String pays) {
		super();
		this.voie = voie;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((voie == null) ? 0 : voie.hashCode());
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
		Adresse other = (Adresse) obj;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equalsIgnoreCase(other.codePostal)) {
			return false;
		}
		if (voie == null) {
			if (other.voie != null)
				return false;
		} else if (!voie.equals(other.voie))
			return false;
		return true;
	}

	@Override
	protected void initDatas() {
		super.initDatas();
		voie = BanqueUtils.capitalize(voie);
		codePostal = BanqueUtils.capitalize(codePostal);
		ville = BanqueUtils.capitalize(ville);
		pays = BanqueUtils.capitalize(pays);
	}

	// initialisation des données du client après chargement et avant modification

	@PreUpdate
	@PostLoad
	private void initDonnees() {
		voie = BanqueUtils.capitalize(voie);
		codePostal = BanqueUtils.capitalize(codePostal);
		ville = BanqueUtils.capitalize(ville);
		pays = BanqueUtils.capitalize(pays);
	}

}
