package entitats;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="PERSONA")
public class Persona {

	/**
	 * Constructor
	 */
	public Persona() {}
	
	public Persona(String dni, String nom, String cognom){
		this.dni=dni;
		this.nom=nom;
		this.cognom=cognom;
	}
	
	@Id
	@Column(name = "DNI")
	private String dni;
	
	@Column(name = "NOM")
	private String nom;
	
	@Column(name = "COGNOM")
	private String cognom;
   
	@ManyToMany(cascade=CascadeType.ALL)  
	@JoinTable(name="localitzacions_persones", joinColumns={@JoinColumn(name="DNI_PERSONA")},
	inverseJoinColumns={@JoinColumn(name="ID_LOCALITZACIO")})  
	@JoinColumn(name="ID_LOCALITZACIO")
	private Set<Localitzacio> localitzacio = new LinkedHashSet();
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public Set<Localitzacio> getLocalitzacio() {
		return localitzacio;
	}

	public void setLocalitzacio(Set<Localitzacio> localitzacio) {
		this.localitzacio = localitzacio;
	}
	
	@Override
	public String toString(){
		return "DNI: "+this.dni+" | Nom: "+this.nom+" | Cognom: "+this.cognom;
	}

}
