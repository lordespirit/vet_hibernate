package entitats;

import javax.persistence.*;

@Entity
@Table(name="MASCOTA")
public class Mascota {

	public Mascota() {}
	
	public Mascota(int ample, int alt, String nom, Gabia gabia, Persona persona) {
		this.ample=ample;
		this.alt=alt;
		this.nom=nom;
		this.gabia=gabia;
		this.persona=persona;
	}


	   @Id
	   @Column(name = "ID") @GeneratedValue(strategy=GenerationType.IDENTITY)
	   private int id;
	
	   @Column(name = "AMPLE")
	   private int ample;
	
	   @Column(name = "ALT")
	   private int alt;
	
	   @Column(name = "NOM")
	   private String nom;
	   
	   @OneToOne 
	   @JoinColumn(name="ID_GABIA") 
	   private Gabia gabia;
	   
	   @ManyToOne
	   @JoinColumn(name="DNI_PERSONA")
	   private Persona persona;
	   
	   public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getAmple() {
			return ample;
		}

		public void setAmple(int ample) {
			this.ample = ample;
		}

		public int getAlt() {
			return alt;
		}

		public void setAlt(int alt) {
			this.alt = alt;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public Gabia getGabia() {
			return gabia;
		}

		public void setGabia(Gabia gabia) {
			this.gabia = gabia;
		}

		public Persona getPersona() {
			return persona;
		}

		public void setPersona(Persona persona) {
			this.persona = persona;
		}
	   
	   @Override
		public String toString(){
			return "ID: "+this.id+" | Nom: "+this.nom+" | Gabia: "+this.gabia.getId()+" | Mascota de: "+this.persona.toString();
		}
}
