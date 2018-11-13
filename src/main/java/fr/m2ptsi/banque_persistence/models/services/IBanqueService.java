package fr.m2ptsi.banque_persistence.models.services;

import java.util.List;

import fr.m2ptsi.banque_persistence.exceptions.BanqueException;
import fr.m2ptsi.banque_persistence.models.Type;
import fr.m2ptsi.banque_persistence.models.enums.TypeCompte;

public interface IBanqueService {

	List<Type> allTypes() throws BanqueException;
	
	Type findTypeById(Integer id) throws BanqueException;
	
	Type findTypeByLibelle(TypeCompte libelle) throws BanqueException;
	
	void save(Type type) throws BanqueException;
	
	void update(Type type) throws BanqueException;
	
	void delete(Integer id) throws BanqueException;
}
