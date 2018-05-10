package dao_implementacio;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dao_int.HibernateUtils;
import dao_int.MascotaDAO;
import entitats.Mascota;
import entitats.Persona;

public class MascotaDAOImplementacio implements MascotaDAO {

	private Session session;
	
	public MascotaDAOImplementacio(){
		this.session = HibernateUtils.getSessionFactory().openSession();
	}
	
	@Override
	public List<Mascota> findAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Mascota> criteria = builder.createQuery(Mascota.class);
		Root<Mascota> root = criteria.from(Mascota.class);
		criteria.select(root);
	
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public Mascota findById(int id) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Mascota> criteria = builder.createQuery(Mascota.class);
		Root<Mascota> root = criteria.from(Mascota.class);
		criteria.select(root).where(builder.equal(root.get("id"), id));
		
		Mascota mascota = session.createQuery(criteria).getSingleResult();
	
		return mascota;
	}

	@Override
	public List<Mascota> findByNom(String nom) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Mascota> criteria = builder.createQuery(Mascota.class);
		Root<Mascota> root = criteria.from(Mascota.class);
		criteria.select(root).where(builder.equal(root.get("nom"), nom));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public List<Mascota> mesAltQue(int alcada) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Mascota> criteria = builder.createQuery(Mascota.class);
		Root<Mascota> root = criteria.from(Mascota.class);
		criteria.select(root).where(builder.gt(root.get("alt"), alcada));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public List<Mascota> mesBaixQue(int alcada) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Mascota> criteria = builder.createQuery(Mascota.class);
		Root<Mascota> root = criteria.from(Mascota.class);
		criteria.select(root).where(builder.lt(root.get("alt"), alcada));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public List<Mascota> mesAmpleQue(int ample) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Mascota> criteria = builder.createQuery(Mascota.class);
		Root<Mascota> root = criteria.from(Mascota.class);
		criteria.select(root).where(builder.gt(root.get("ample"), ample));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public List<Mascota> menysAmpleQue(int ample) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Mascota> criteria = builder.createQuery(Mascota.class);
		Root<Mascota> root = criteria.from(Mascota.class);
		criteria.select(root).where(builder.lt(root.get("ample"), ample));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public List<Mascota> mascotesClient(Persona persona) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Mascota> criteria = builder.createQuery(Mascota.class);
		Root<Mascota> root = criteria.from(Mascota.class);
		criteria.select(root).where(builder.equal(root.get("persona"), persona));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public void save(Mascota mascota) {
		session.beginTransaction();
		session.save(mascota);
		session.getTransaction().commit();
	}

	@Override
	public void updateNom(int id, String nom) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaUpdate<Mascota> update = builder.createCriteriaUpdate(Mascota.class);
		Root<Mascota> root = update.from(Mascota.class);
		update.set("nom", nom);
		update.where(builder.equal(root.get("id"), id));
		
		session.createQuery(update).executeUpdate();
		session.getTransaction().commit();		
	}

	@Override
	public void delete(int id) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Mascota> delete = builder.createCriteriaDelete(Mascota.class);
		Root<Mascota> root = delete.from(Mascota.class);
		delete.where(builder.equal(root.get("id"), id));
		
		session.createQuery(delete).executeUpdate();
		session.getTransaction().commit();
		
	}

	@Override
	public void close() {
		session.close();				
	}

}
