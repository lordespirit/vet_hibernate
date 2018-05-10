package main;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import dao_implementacio.GabiaDAOImplementacio;
import dao_implementacio.LocalitzacioDAOImplementacio;
import dao_implementacio.MascotaDAOImplementacio;
import dao_implementacio.PersonaDAOImplementacio;
import entitats.Gabia;
import entitats.Localitzacio;
import entitats.Mascota;
import entitats.Persona;

public class Main {

	public static void main(String[] args){
		
		LocalitzacioDAOImplementacio l = new LocalitzacioDAOImplementacio();
		PersonaDAOImplementacio p = new PersonaDAOImplementacio();
		MascotaDAOImplementacio m = new MascotaDAOImplementacio();
		GabiaDAOImplementacio g = new GabiaDAOImplementacio();
		
		Localitzacio loc1 = new Localitzacio(8028, "Avinguda Madrid 40", "Pis quart, porta dos", "Barcelona","Espanya", 666666666);
		Localitzacio loc2 = new Localitzacio(8029, "Passeig de Sant Juan", "sense número", "Barcelona","Espanya", 45957854);
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
		Persona persona5 = new Persona("63434233M", "Pol", "Pol");
		
		// Creem la variable localitzacions
		Set<Localitzacio> localitzacionsP1 = new LinkedHashSet();
		localitzacionsP1.add(loc1); // Té casa a Barcelona
		localitzacionsP1.add(loc4); // Té segona residència a Girona
		// Fem el set de localitzacions a la persona 1
		persona1.setLocalitzacio(localitzacionsP1);
		
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
		
		// Instanciem les gabies amb la seva informació
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
		
		/*
		l.findAll().forEach(localitzacio -> {
			System.out.println(localitzacio.toString());
		});
		
		System.out.println(l.findById(1).toString());
		
		l.findByCiutat("Barcelona").forEach(localitzacio -> {
			System.out.println(localitzacio.toString());
		});
		
		*/
		
		m.mascotesClient(persona2).forEach(persona -> {
			System.out.println(persona.toString());
		});
		
		l.close();
		
	}
	
}
