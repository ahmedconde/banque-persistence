package fr.m2ptsi.banque_persistence.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.m2ptsi.banque_persistence.exceptions.BanqueException;
import fr.m2ptsi.banque_persistence.models.Banque;
import fr.m2ptsi.banque_persistence.models.Type;
import fr.m2ptsi.banque_persistence.models.enums.TypeCompte;

public interface IBanqueRepository /*extends JpaRepository<Banque, Integer>*/ {

	List<Type> allTypes() throws BanqueException;
	
	Type findTypeById(Integer id) throws BanqueException;
	
	Type findTypeByLibelle(TypeCompte libelle) throws BanqueException;
	
	void saveOrUpdate(Type typeCompte) throws BanqueException;
	
	void delete(Type typeCompte) throws BanqueException;
	
}
