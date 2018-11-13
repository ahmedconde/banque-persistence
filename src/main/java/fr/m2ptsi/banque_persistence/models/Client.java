package fr.m2ptsi.banque_persistence.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

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
public class Client extends AbstractEntity {

	private String nom;
	private String prenom;
	private String mdp;
	private String email;
	private LocalDate ddn;

	@Transient
	private Integer age;

	@OneToMany(mappedBy ="client")
	private Set<Compte> listeComptes = new HashSet<>();

	@OneToOne
	private Adresse adresse;

	public Client(String nom, String prenom, String mdp, String email, LocalDate ddn, Integer age,
			Set<Compte> listeComptes, Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mdp = mdp;
		this.email = email;
		this.ddn = ddn;
		this.age = age;
		this.listeComptes = listeComptes;
		this.adresse = adresse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Client other = (Client) obj;
		return email.equalsIgnoreCase(other.email);
	}

	// ajout d'un nouveau compte du client
	
	public void add(Compte compte) {
		if(compte != null) {
			listeComptes.add(compte);
		}
	}

	@Override
	protected void initDatas() {
		super.initDatas();
		nom = BanqueUtils.capitalize(nom);
		prenom = BanqueUtils.capitalize(prenom);
		email = BanqueUtils.capitalize(email);
		mdp = BanqueUtils.capitalize(mdp);
	}

	// initialisation des données du client après chargement et avant modification

	@PreUpdate
	@PostLoad
	private void initDonnees() {
		nom = BanqueUtils.capitalize(nom);
		prenom = BanqueUtils.capitalize(prenom);
		email = BanqueUtils.capitalize(email);
		mdp = BanqueUtils.capitalize(mdp);
	}

}
