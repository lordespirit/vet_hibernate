package entitats;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="LOCALITZACIO")
public class Localitzacio {

		@Id
	   @Column(name = "ID") @GeneratedValue(strategy=GenerationType.IDENTITY)
	   private int id;
	   
	   @Column(name = "CODI_POSTAL")
	   private int codi_postal;
	
	   @Column(name = "CARRER")
	   private String carrer;
	
	   @Column(name = "INFO_ADICIONAL")
	   private String info_adicional;
	
	   @Column(name = "CIUTAT")
	   private String ciutat;
	   
	   @Column(name = "PAIS")
	   private String pais;
	   
	   @Column(name = "TELEFON")
	   private int telefon;
	   
	   @ManyToMany(mappedBy="localitzacio")
       private Set<Persona> persones;
	   
	
		
		public Localitzacio() {}
		
		public Localitzacio(int codi_postal,String carrer, String info_adicional, String ciutat, String pais, int telefon){
			this.codi_postal = codi_postal;
			this.carrer = carrer;
			this.info_adicional = info_adicional;
			this.ciutat=ciutat;
			this.pais=pais;
			this.telefon=telefon;
		}   

	public int getCodi_postal() {
		return codi_postal;
	}

	public void setCodi_postal(int codi_postal) {
		this.codi_postal = codi_postal;
	}

	public String getCarrer() {
		return carrer;
	}

	public void setCarrer(String carrer) {
		this.carrer = carrer;
	}

	public String getInfo_adicional() {
		return info_adicional;
	}

	public void setInfo_adicional(String info_adicional) {
		this.info_adicional = info_adicional;
	}

	public String getCiutat() {
		return ciutat;
	}

	public void setCiutat(String ciutat) {
		this.ciutat = ciutat;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}
	
	public Set<Persona> getPersones() {
		return persones;
	}

	public void setPersones(Set<Persona> persones) {
		this.persones = persones;
	}

	@Override
	public String toString(){
		return "ID: "+this.getId()+" | Codi postal: "+this.getCodi_postal()+" | Carrer: "+this.getCarrer()+" | Ciutat: "+this.getCiutat()+" | Informació addicional: "+this.getInfo_adicional()+" | Pais: "+this.getPais()+" | Telefon: "+this.getTelefon();
	}

}
