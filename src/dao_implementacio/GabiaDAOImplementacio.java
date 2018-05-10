package dao_implementacio;

import org.hibernate.Session;

import dao_int.GabiaDAO;
import dao_int.HibernateUtils;
import entitats.Gabia;
import entitats.Mascota;

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
	
	
}
