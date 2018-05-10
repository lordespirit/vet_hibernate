package dao_int;

import java.util.List;
import entitats.Persona;

public interface PersonaDAO {

	public List<Persona> findAll();
	public Persona findByDni(String dni);
	public List<Persona> findByNameSurname(String name, String surname);
	public List<Persona> findBySurname(String surname);
	
	public void save(Persona persona);
	public void updateName(String dni,String nom, String cognom);
	public void delete(String dni);
	public void close();
	
}
