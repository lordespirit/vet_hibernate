package entitats;

import javax.persistence.*;

@Entity
@Table(name="GABIA")
public class Gabia {

	public Gabia(){};
	
	public Gabia(int mida, int pis) {
		this.mida=mida;
		this.pis=pis;
	}

   @Id
   @Column(name = "ID") @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;

   @Column(name = "MIDA")
   private int mida;

   @Column(name = "PIS")
	   private int pis;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMida() {
		return mida;
	}
	public void setMida(int mida) {
		this.mida = mida;
	}
	public int getPis() {
		return pis;
	}
	public void setPis(int pis) {
		this.pis = pis;
	}   
	
}
