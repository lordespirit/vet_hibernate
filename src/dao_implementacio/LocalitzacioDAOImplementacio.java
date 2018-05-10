package dao_implementacio;

import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dao_int.HibernateUtils;
import dao_int.LocalitzacioDAO;
import entitats.Localitzacio;

public class LocalitzacioDAOImplementacio implements LocalitzacioDAO{

	private Session session;
	
	public LocalitzacioDAOImplementacio(){
		this.session = HibernateUtils.getSessionFactory().openSession();
	}
	
	@Override
	public Localitzacio findById(int id) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Localitzacio> criteria = builder.createQuery(Localitzacio.class);
		Root<Localitzacio> root = criteria.from(Localitzacio.class);
		criteria.select(root).where(builder.equal(root.get("id"), id));
		
		Localitzacio loc = session.createQuery(criteria).getSingleResult();
	
		return loc;
	}

	@Override
	public List<Localitzacio> findByCodiPostal(int codi_postal) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Localitzacio> criteria = builder.createQuery(Localitzacio.class);
		Root<Localitzacio> root = criteria.from(Localitzacio.class);
		criteria.select(root).where(builder.equal(root.get("codi_postal"), codi_postal));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public List<Localitzacio> findByCiutat(String ciutat) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Localitzacio> criteria = builder.createQuery(Localitzacio.class);
		Root<Localitzacio> root = criteria.from(Localitzacio.class);
		criteria.select(root).where(builder.equal(root.get("ciutat"), ciutat));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public void save(Localitzacio localitzacio) {
		session.beginTransaction();
		session.save(localitzacio);
		session.getTransaction().commit();
	}

	@Override
	public void delete(int id) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Localitzacio> delete = builder.createCriteriaDelete(Localitzacio.class);
		Root<Localitzacio> root = delete.from(Localitzacio.class);
		delete.where(builder.equal(root.get("id"), id));
		
		session.createQuery(delete).executeUpdate();
		session.getTransaction().commit();
	}

	@Override
	public void close() {
		session.close();
	}

	@Override
	public List<Localitzacio> findAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Localitzacio> criteria = builder.createQuery(Localitzacio.class);
		Root<Localitzacio> root = criteria.from(Localitzacio.class);
		criteria.select(root);
	
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public void updateTelefon(int id, String telefon) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaUpdate<Localitzacio> update = builder.createCriteriaUpdate(Localitzacio.class);
		Root<Localitzacio> root = update.from(Localitzacio.class);
		update.set("telefon", telefon);
		update.where(builder.equal(root.get("id"), id));
		
		session.createQuery(update).executeUpdate();
		session.getTransaction().commit();
		
	}

	@Override
	public void updateInfo(int id, String info_adicional) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaUpdate<Localitzacio> update = builder.createCriteriaUpdate(Localitzacio.class);
		Root<Localitzacio> root = update.from(Localitzacio.class);
		update.set("info_adicional", info_adicional);
		update.where(builder.equal(root.get("id"), id));
		
		session.createQuery(update).executeUpdate();
		session.getTransaction().commit();
		
	}

}
