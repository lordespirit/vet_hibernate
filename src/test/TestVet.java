package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import dao_implementacio.GabiaDAOImplementacio;
import dao_implementacio.LocalitzacioDAOImplementacio;
import dao_implementacio.MascotaDAOImplementacio;
import dao_implementacio.PersonaDAOImplementacio;
import entitats.Gabia;
import entitats.Localitzacio;
import entitats.Mascota;
import entitats.Persona;
import org.junit.runners.MethodSorters;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestVet {
	
	private static LocalitzacioDAOImplementacio l	= new LocalitzacioDAOImplementacio();
	private static PersonaDAOImplementacio p		= new PersonaDAOImplementacio();
	private static MascotaDAOImplementacio m		= new MascotaDAOImplementacio();
	private static GabiaDAOImplementacio g			= new GabiaDAOImplementacio();
	
	public static void open(){
		l = new LocalitzacioDAOImplementacio();
		p = new PersonaDAOImplementacio();
		m = new MascotaDAOImplementacio();
		g = new GabiaDAOImplementacio();
	}
	
	public static void close(){
		l.close();
		p.close();
		m.close();
		g.close();
	}

	
	@Test
	public void testA_Gabia() {
	
		List<Gabia> gabies;
		
		// Busquem totes les gabies
		// Tenim un total de 5
		gabies = g.findAll();
		Assert.assertEquals(5, gabies.size());	
		
		// Provem a cerca les gabies del pis 2
		// Tenim un total de 3
		gabies = g.findByPis(2);
		Assert.assertEquals(3, gabies.size());
		
		// Provem a actualitzar la gabia amb ID 5
		// Li possem el pis 1
		g.updateGabia(5, 1, 2);
		
		// Ara provem de nou a cercar les gabies del pis 2
		// Tenim ara 2
		gabies = g.findByPis(2);
		Assert.assertEquals(2, gabies.size());
		
		// Esborrem la gabia 3, est� desocupado i volem fer manteniment
		g.delete(3);
		// Busquem totes les gabies
		// Amb el esborrat, ara tenim 4
		gabies = g.findAll();
		Assert.assertEquals(4, gabies.size());	
		
	}
	
	@Test
	public void testB_Persona() {
			
		List<Persona> persona;
		Persona personaInd;
		
		// Busquem totes les persones
		// Tenim un total de 5
		persona = p.findAll();
		Assert.assertEquals(5, persona.size());	
		
		// Provem a cerca persona amb nom i cognom
		// Tenim dues persones que es diuen 'Mar'
		// Pero els cognoms dels dos s�n diferents
		persona = p.findByNameSurname("Mar","Mar");
		Assert.assertEquals(1, persona.size());
		
		// Per� si ara busquem nom�s pel nom, trobar� 2 Mar
		persona = p.findByName("Mar");
		Assert.assertEquals(2, persona.size());
		
		// Busquem una persona pel seu DNI
		personaInd = p.findByDni("23346789P");
		// Aquesta persona es diu Edu
		Assert.assertEquals("Edu", personaInd.getNom());
				
		// Abans d'actualitzar, mirem si existeix Eduard Valles
		persona = p.findByNameSurname("Eduard","Valles");
		// Esperem 0 persones
		Assert.assertEquals(0, persona.size());
		// Actualitzem les dades d'aquesta persona amb Eduard Valles
		p.updateName(personaInd.getDni(), "Eduard", "Valles");
		// Ara comprovem com existeix un usuari amb aquest nom
		persona = p.findByNameSurname("Eduard","Valles");
		Assert.assertEquals(1, persona.size());
				
		// Afegim una nova persona
		Persona novaPersona = new Persona("12987452A", "Marcel", "Lopez");
		p.save(novaPersona);
		
		// Ara comprovem que hi ha 6 persones en total
		persona = p.findAll();
		Assert.assertEquals(6, persona.size());	
		
		// Esborrem aquesta persona
		p.delete(novaPersona.getDni());
		
		// Ara comprovem que hi ha 5 persones de nou
		persona = p.findAll();
		Assert.assertEquals(5, persona.size());	
		
		// Ara anem a veure les localitzacions de la persona
		// amb DNI 23346789P, n'ha de tenir 2
		personaInd = p.findByDni("23346789P");
		Set<Localitzacio> localitzacionsEdu = personaInd.getLocalitzacio();
		Assert.assertEquals(2, localitzacionsEdu.size());
		
		// Li afegim una nova localitzaci�
		Localitzacio newloc = new Localitzacio(18752, "Carrer de la pera", "55 3a", "Malgrat de Mar","Espanya", 945851254);
		// Guardem la localitzaci�
		l.save(newloc);		
		// Afegim aquesta localitzaci� a la persona
		localitzacionsEdu.add(newloc);
		// Guardem les dades d'Edu
		p.save(personaInd);
		// I comprovem que t� les 3
		personaInd = p.findByDni("23346789P");
		localitzacionsEdu = personaInd.getLocalitzacio();
		Assert.assertEquals(3, localitzacionsEdu.size());
		
	}	
	

	@Test
	public void testC_Mascota() {
			
		List<Mascota> mascotes;
		Mascota mascotaInd;
		
		// Busquem totes les mascotes
		// Tenim un total de 4
		mascotes = m.findAll();
		Assert.assertEquals(4, mascotes.size());	
		
		// Busquem la mascota Felix
		mascotes = m.findByNom("Felix");
		// Tenim una mascota amb aquest nom
		Assert.assertEquals(1, mascotes.size());
		
		// Busquem les mascotas del client (en t� 2)
		mascotes = m.mascotesClientDNI("12563413Y");
		// Comprovem que son 2
		Assert.assertEquals(2, mascotes.size());
		
		// Anem a buscar les mascotes m�s amples que 2
		mascotes = m.mesAmpleQue(2);
		// Nom�s n'hi ha 1, que es de 3
		Assert.assertEquals(1, mascotes.size());
		
		// Anem a buscar les mascotes menys ample que 3
		mascotes = m.menysAmpleQue(3);
		// Nom�s n'hi ha 3
		Assert.assertEquals(3, mascotes.size());
		
		// Anem a buscar les mascotes mes baixes que 4
		mascotes = m.mesBaixQue(4);
		// Nom�s n'hi ha 4, totes
		Assert.assertEquals(4, mascotes.size());
		
		// Anem a fer un update, una mascota ara t� una mida diferent
		// I pertany a la persona amb dni 01232342Q
		Persona persona = p.findByDni("01232342Q");
		// La mascota 3 "T-REX" t� una nova al�ada de 9 metres i ample de 4
		m.update(3, "T-REX", 9, 4, persona);
		
		// Tornem a obtindre les dades despr�s del update
		close();
		open();
	
		// Tornem a obtindre les dades per aquesta mascota
		mascotaInd = m.findById(3);
		Assert.assertEquals("T-REX", mascotaInd.getNom());
		Assert.assertEquals(4, mascotaInd.getAmple());
		Assert.assertEquals(9, mascotaInd.getAlt());
		Assert.assertEquals("01232342Q", mascotaInd.getPersona().getDni());

		}
	
	@Test
	public void testD_Localitzacio() {
			
		List<Localitzacio> localitzacions;
		Localitzacio loc;
		
		// Busquem totes les mascotes
		// Tenim un total de 6
		// Comptant la creada al testB
		localitzacions = l.findAll();
		Assert.assertEquals(6, localitzacions.size());	
		
		//Comprovem, de les localitzacions de Barcelona
		// Que un t� el telefon 666666666
		localitzacions = l.findByCiutat("Barcelona");
		List<Localitzacio> expected = new ArrayList<Localitzacio>();
		
		// Agafem els mateixos objetos per afegir al expected
		Localitzacio loccopy1 = new Localitzacio(8028, "Avinguda Madrid 40", "Pis quart, porta dos", "Barcelona","Espanya", 666666666);
		Localitzacio loccopy2 = new Localitzacio(8029, "Passeig de Sant Juan", "sense n�mero", "Barcelona","Espanya", 45957854);
		expected.add(loccopy1);
		expected.add(loccopy2);

		// Comprovem que tenen el mateix telefon, carrer i codi postal
		assertThat(localitzacions.get(0).getTelefon(), is(expected.get(0).getTelefon()));
		assertThat(localitzacions.get(0).getCarrer(), is(expected.get(0).getCarrer()));
		assertThat(localitzacions.get(0).getCodi_postal(), is(expected.get(0).getCodi_postal()));
	
		assertThat(localitzacions.get(1).getTelefon(), is(expected.get(1).getTelefon()));
		assertThat(localitzacions.get(1).getCarrer(), is(expected.get(1).getCarrer()));
		assertThat(localitzacions.get(1).getCodi_postal(), is(expected.get(1).getCodi_postal()));
		
		// Cerquem la loalitzacio amb id 4
		loc = l.findById(4);
		// Dades que t� Calle Mejia 12 Girona 4000 bla bla 1 Espanya 14253674
		Assert.assertEquals(4000, loc.getCodi_postal());	
		Assert.assertEquals("Calle Mejia 12", loc.getCarrer());	
		Assert.assertEquals("bla bla 1", loc.getInfo_adicional());	
		
		// Anem a canviar les dades de informaci� addicional
		l.updateInfo(4, "Client VIP");
		
		// Tornem a obtindre les dades, primer tanquem sessi�
		close();
		open();
		
		loc = l.findById(4);		
		// I comprovem que sigui aixi
		Assert.assertEquals("Client VIP", loc.getInfo_adicional());	
	
	}
	
	/**
	 * M�tode que carrega un cop abans del test per carregar les dades inicials
	 */
	@BeforeClass
	public static void insercions(){
				
		Localitzacio loc1 = new Localitzacio(8028, "Avinguda Madrid 40", "Pis quart, porta dos", "Barcelona","Espanya", 666666666);
		Localitzacio loc2 = new Localitzacio(8029, "Passeig de Sant Juan", "sense n�mero", "Barcelona","Espanya", 45957854);
		Localitzacio loc3 = new Localitzacio(3000, "Carrer Major 31", "7e 4t", "Lleida","Espanya", 758654545);
		Localitzacio loc4 = new Localitzacio(4000, "Calle Mejia 12", "bla bla 1", "Girona","Espanya", 14253674);
		Localitzacio loc5 = new Localitzacio(5525, "Straufen 122", "123 po.", "Berlin","Alemanya", 123789456);
		l.save(loc1);
		l.save(loc2);
		l.save(loc3);
		l.save(loc4);
		l.save(loc5);
		
		
		Persona persona1 = new Persona("23346789P", "Edu", "Val");
		Persona persona2 = new Persona("12563413Y", "Bil", "Bil");
		Persona persona3 = new Persona("97625151S", "Jos", "Jos");
		Persona persona4 = new Persona("01232342Q", "Mar", "Mar");
		Persona persona5 = new Persona("63434233M", "Mar", "Pol");
		
		// Creem la variable localitzacions amb un SET, la SET localitzaci� P1 t� dos localitzacions
		Set<Localitzacio> localitzacionsP1 = new LinkedHashSet();
		localitzacionsP1.add(loc1); // T� casa a Barcelona
		localitzacionsP1.add(loc4); // T� segona resid�ncia a Girona
		
		// Afegim aquest SET de localitzacions a la persona 1
		persona1.setLocalitzacio(localitzacionsP1);
		
		// Fem els seg�ents casos amb els altres llocs
		Set<Localitzacio> localitzacionsP2 = new LinkedHashSet();
		localitzacionsP2.add(loc2);
		persona2.setLocalitzacio(localitzacionsP2);
		
		Set<Localitzacio> localitzacionsP3 = new LinkedHashSet();
		localitzacionsP3.add(loc3);
		persona3.setLocalitzacio(localitzacionsP3);

		// La persona 4 i 5 viuen junts
		Set<Localitzacio> localitzacionsP4 = new LinkedHashSet();
		localitzacionsP4.add(loc5);
		persona4.setLocalitzacio(localitzacionsP4);
		persona5.setLocalitzacio(localitzacionsP4);
		
		// Ara guardem les dades de persones i totes les seves localitzacions
		p.save(persona1);
		p.save(persona2);
		p.save(persona3);
		p.save(persona4);
		p.save(persona5);
		
		// Instanciem les gabies amb la seva informaci�
		Gabia gabia1 = new Gabia(1, 1);
		Gabia gabia2 = new Gabia(2, 1);
		Gabia gabia3 = new Gabia(3, 2);
		Gabia gabia4 = new Gabia(4, 2);
		Gabia gabia5 = new Gabia(1, 2);

		// Guardem els canvis a la base de dades
		g.save(gabia1);
		g.save(gabia2);
		g.save(gabia3);
		g.save(gabia4);
		g.save(gabia5);
		
		// Instanciem les mascotes amb el seu responsable/amo
		Mascota mascota1 = new Mascota(1,1,"Felix",gabia1 , persona1);
		Mascota mascota2 = new Mascota(2,1,"Rex",gabia2 , persona3);
		Mascota mascota3 = new Mascota(3,2,"T-REX",gabia4 , persona2);
		Mascota mascota4 = new Mascota(1,1,"Piolin",gabia5 , persona2);
		m.save(mascota1);
		m.save(mascota2);
		m.save(mascota3);
		m.save(mascota4);
				
	}

}
