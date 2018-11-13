package fr.m2ptsi.banque_persistence.boot;

import fr.m2ptsi.banque_persistence.exceptions.BanqueException;
import fr.m2ptsi.banque_persistence.models.Type;
import fr.m2ptsi.banque_persistence.models.enums.TypeCompte;
import fr.m2ptsi.banque_persistence.models.services.BanqueService;

public class BanquePersistenceMain {

	public static void main(String[] args) throws BanqueException {
		
		BanqueService service = new BanqueService();
		
		Type type = new Type();
		type.setLibelle(TypeCompte.COURANT);
		System.out.println("Le libelle : "+type.getLibelle());
		
		service.save(type);
		
	}

}
