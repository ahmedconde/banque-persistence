package fr.m2ptsi.banque_persistence.models.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.m2ptsi.banque_persistence.exceptions.BanqueException;
import fr.m2ptsi.banque_persistence.models.Type;
import fr.m2ptsi.banque_persistence.models.enums.TypeCompte;

@Repository
public class BanqueRepository implements IBanqueRepository {

	@Autowired
	private SessionFactory factory;
	
	protected Session getSession() {
		return factory.getCurrentSession();
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Type> allTypes() throws BanqueException {
		return getSession().createQuery("from Type t").getResultList();
	}

	@Override
	public Type findTypeById(Integer id) throws BanqueException {
		return getSession().find(Type.class, id);
	}

	@Override
	public Type findTypeByLibelle(TypeCompte libelle) throws BanqueException {
		return (Type) getSession().createQuery("from Type t where t.libelle = ?1").setParameter(1, libelle).getSingleResult();
	}

	@Override
	public void saveOrUpdate(Type typeCompte) throws BanqueException {
		getSession().saveOrUpdate(typeCompte);
	}

	@Override
	public void delete(Type typeCompte) throws BanqueException {
		getSession().delete(typeCompte);

	}



}
