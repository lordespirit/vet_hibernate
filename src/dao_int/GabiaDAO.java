package dao_int;

import java.util.List;

import entitats.Gabia;

public interface GabiaDAO {

	public void save(Gabia gabia);

	public List<Gabia> findAll();
	public Gabia findById(int id);
	public List<Gabia> findByMida(int mida);
	public List<Gabia> findByPis(int pis);
	public void updateGabia(int id, int pis, int mida);
	public void delete(int id);
	public void close();
		
}
