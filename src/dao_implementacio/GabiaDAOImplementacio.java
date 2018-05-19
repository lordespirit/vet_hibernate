package dao_implementacio;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import dao_int.GabiaDAO;
import dao_int.HibernateUtils;
import entitats.Gabia;

public class GabiaDAOImplementacio implements GabiaDAO {

	private Session session;
	
	public GabiaDAOImplementacio(){
		this.session = HibernateUtils.getSessionFactory().openSession();
	}

	@Override
	public void save(Gabia gabia) {
		session.beginTransaction();
		session.save(gabia);
		session.getTransaction().commit();
	}

	@Override
	public void close() {
		session.close();
	}

	@Override
	public List<Gabia> findAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Gabia> criteria = builder.createQuery(Gabia.class);
		Root<Gabia> root = criteria.from(Gabia.class);
		criteria.select(root);
	
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public Gabia findById(int id) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Gabia> criteria = builder.createQuery(Gabia.class);
		Root<Gabia> root = criteria.from(Gabia.class);
		criteria.select(root).where(builder.equal(root.get("id"), id));
		
		Gabia gab = session.createQuery(criteria).getSingleResult();
	
		return gab;
	}

	@Override
	public List<Gabia> findByMida(int mida) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Gabia> criteria = builder.createQuery(Gabia.class);
		Root<Gabia> root = criteria.from(Gabia.class);
		criteria.select(root).where(builder.gt(root.get("mida"), mida));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public List<Gabia> findByPis(int pis) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Gabia> criteria = builder.createQuery(Gabia.class);
		Root<Gabia> root = criteria.from(Gabia.class);
		criteria.select(root).where(builder.equal(root.get("pis"), pis));
			
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public void updateGabia(int id, int pis, int mida) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaUpdate<Gabia> update = builder.createCriteriaUpdate(Gabia.class);
		Root<Gabia> root = update.from(Gabia.class);
		update.set("pis", pis);
		update.set("mida", mida);
		update.where(builder.equal(root.get("id"), id));
		
		session.createQuery(update).executeUpdate();
		session.getTransaction().commit();
	}

	@Override
	public void delete(int id) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<Gabia> delete = builder.createCriteriaDelete(Gabia.class);
		Root<Gabia> root = delete.from(Gabia.class);
		delete.where(builder.equal(root.get("id"), id));
		
		session.createQuery(delete).executeUpdate();
		session.getTransaction().commit();
	}
	
	
}
