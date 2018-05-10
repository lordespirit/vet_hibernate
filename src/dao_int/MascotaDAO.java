package dao_int;

import java.util.List;

import entitats.Mascota;
import entitats.Persona;

public interface MascotaDAO {

	public List<Mascota> findAll();
	public Mascota findById(int id);
	public List<Mascota> findByNom(String nom);
	public List<Mascota> mesAltQue(int alcada);
	public List<Mascota> mesBaixQue(int alcada);
	public List<Mascota> mesAmpleQue(int ample);
	public List<Mascota> menysAmpleQue(int ample);
	public List<Mascota> mascotesClient(Persona persona);

	
	public void save(Mascota mascota);
	public void updateNom(int id,String nom);
	public void delete(int id);
	public void close();
	
}
