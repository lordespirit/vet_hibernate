package dao_implementacio;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dao_int.HibernateUtils;
import dao_int.PersonaDAO;
import entitats.Persona;

public class PersonaDAOImplementacio implements PersonaDAO {

	private Session session;
	
	public PersonaDAOImplementacio(){
		this.session = HibernateUtils.getSessionFactory().openSession();
	}
	
	@Override
	public List<Persona> findAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);
		Root<Persona> root = criteria.from(Persona.class);
		criteria.select(root);
	
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public Persona findByDni(String dni) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);
		Root<Persona> root = criteria.from(Persona.class);
		criteria.select(root).where(builder.equal(root.get("dni"), dni));
		
		Persona persona = session.createQuery(criteria).getSingleResult();
	
		return persona;
	}

	@Override
	public List<Persona> findByNameSurname(String nom, String cognom) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);
		Root<Persona> root = criteria.from(Persona.class);
		criteria.select(root).where(builder.and(builder.equal(root.get("nom"), nom),builder.equal(root.get("cognom"), cognom)));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public List<Persona> findBySurname(String cognom) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Persona> criteria = builder.createQuery(Persona.class);
		Root<Persona> root = criteria.from(Persona.class);
		criteria.select(root).where(builder.equal(root.get("cognom"), cognom));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public void save(Persona persona) {
		session.beginTransaction();
		session.save(persona);
		session.getTransaction().commit();
	}

	@Override
	public void updateName(String dni, String nom, String cognom) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaUpdate<Persona> update = builder.createCriteriaUpdate(Persona.class);
		Root<Persona> root = update.from(Persona.class);
		update.set("nom", nom);
		update.set("cognom", cognom);
		update.where(builder.equal(root.get("dni"), dni));
		
		session.createQuery(update).executeUpdate();
		session.getTransaction().commit();		
	}

	@Override
	public void delete(String dni) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Persona> delete = builder.createCriteriaDelete(Persona.class);
		Root<Persona> root = delete.from(Persona.class);
		delete.where(builder.equal(root.get("dni"), dni));
		
		session.createQuery(delete).executeUpdate();
		session.getTransaction().commit();
		
	}

	@Override
	public void close() {
		session.close();		
	}

}
