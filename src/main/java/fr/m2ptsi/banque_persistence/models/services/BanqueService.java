package fr.m2ptsi.banque_persistence.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.m2ptsi.banque_persistence.exceptions.BanqueException;
import fr.m2ptsi.banque_persistence.models.Type;
import fr.m2ptsi.banque_persistence.models.enums.TypeCompte;
import fr.m2ptsi.banque_persistence.models.repositories.IBanqueRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BanqueException.class)
public class BanqueService implements IBanqueService {

	@Autowired
	private IBanqueRepository repository;
	
	@Override
	public List<Type> allTypes() throws BanqueException {
		return repository.allTypes();
	}

	@Override
	public Type findTypeById(Integer id) throws BanqueException {
		return repository.findTypeById(id);
	}

	@Override
	public Type findTypeByLibelle(TypeCompte libelle) throws BanqueException {
		return repository.findTypeByLibelle(libelle);
	}

	@Override
	public void save(Type type) throws BanqueException {
		if(type.getId()!= null && !allTypes().contains(type)) {
			Type tpe = new Type();
			tpe.setLibelle(type.getLibelle());
			repository.saveOrUpdate(tpe);
		}
	}

	
	@Override
	public void update(Type type) throws BanqueException {
		if(type.getId()== null) {
			Type t = findTypeById(type.getId());
			if(t != null && !allTypes().contains(type)) {
				t.setLibelle(type.getLibelle());
				repository.saveOrUpdate(type);
			}
		}
	}

	
	@Override
	public void delete(Integer id) throws BanqueException {
		Type type = findTypeById(id);
		if(allTypes().contains(type)){
			repository.delete(type);
		}
	}

}
