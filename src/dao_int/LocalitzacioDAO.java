package dao_int;

import java.util.List;
import entitats.Localitzacio;

public interface LocalitzacioDAO {
	
	public List<Localitzacio> findAll();
	public Localitzacio findById(int id);
	public List<Localitzacio> findByCodiPostal(int codi_postal);
	public List<Localitzacio> findByCiutat(String ciutat);
	
	public void save(Localitzacio localitzacio);
	public void updateTelefon(int id,String telefon);
	public void updateInfo(int id, String info_adicional);
	public void delete(int id);
	public void close();
	

}
