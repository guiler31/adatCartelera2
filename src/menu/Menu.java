package menu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entidades.Actores;
import entidades.Peliculas;

import hibernate.AccesoHibernate;

public class Menu {

	public static void main(String[] args) {
		AccesoHibernate ah = new AccesoHibernate();
		// TODO Auto-generated method stub
		System.out.println("Introduce el numero para realizar la accion");
		System.out.println("1. Insert/Update");
		System.out.println("2. Delete");
		System.out.println("3. Select");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		switch (choice) {
		case "1":
			// Perform "original number" case.
			System.out.println("Introduce el numero para realizar la accion");
			System.out.println("1. Insert/Update Actores");
			System.out.println("2. Insert/Update Peliculas");
			String choice11 = scanner.nextLine();

			switch (choice11) {
			case "1":
				Actores actor = new Actores();
				System.out.println("Introduce el id SOLO si quieres hacer update");
				String cod = scanner.nextLine();
				if (!cod.isEmpty()) {
					int codigo = Integer.parseInt(cod);
					actor.setCodigo(codigo);
				} else {
					actor.setCodigo(null);
				}

				System.out.println("Nombre:");

				String nombre = scanner.nextLine();
				actor.setNombre(nombre);
				System.out.println("Introduce fecha Nacimiento en formato yyyy-MM-dd (Ejemplo: 2017-07-97):");
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String Fnacimiento = scanner.nextLine();
				Date FNacimiento = null;
				try {
					FNacimiento = format.parse(Fnacimiento);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				actor.setFNacimiento(FNacimiento);
				System.out.println("Nacionalidad:");
				String nacionalidad = scanner.nextLine();
				actor.setNacionalidad(nacionalidad);
				ah.HibAddOrUp(actor, null);
				break;
			case "2":
				Peliculas peli = new Peliculas();
				System.out.println("Introduce el id SOLO si quieres hacer update");
				String codPelicula = scanner.nextLine();
				if (!codPelicula.isEmpty()) {
					int codigoPelicula = Integer.parseInt(codPelicula);
					peli.setCodigo(codigoPelicula);
				} else {
					peli.setCodigo(null);
				}
				System.out.println("Titulo:");
				String titulo = scanner.nextLine();
				peli.setTitulo(titulo);
				System.out.println("Introduce fecha  en formato yyyy-MM-dd (Ejemplo: 2017-07-97):");
				DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
				String fech = scanner.nextLine();
				Date fecha = null;
				try {
					fecha = format2.parse(fech);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				peli.setFecha(fecha);
				System.out.println("Introduce presupuesto:");
				String presupuesto = scanner.nextLine();
				Double pres = Double.parseDouble(presupuesto);
				peli.setPresupuesto(pres);
				ah.HibAddOrUp(null, peli);
				break;
			
			}

			break;
		case "2":
			// Perform "encrypt number" case.
			System.out.println("Introduce el numero para realizar la accion");
			System.out.println("1. Delete Actores");
			System.out.println("2. Delete Peliculas");
			String choice12 = scanner.nextLine();

			switch (choice12) {
			case "1":
				System.out.println("Introduce el id del actor a borrar:");
				String delAct = scanner.nextLine();
				ah.HibDelete(Integer.parseInt(delAct), "actores");
				break;
			case "2":
				System.out.println("Introduce el id de la pelicula a borrar:");
				String delPel = scanner.nextLine();
				ah.HibDelete(Integer.parseInt(delPel), "peliculas");
				break;
			
			}

			break;
		case "3":
			// Perform "decrypt number" case.
			System.out.println("Introduce el numero para realizar la accion");
			System.out.println("1. Select All Actores");
			System.out.println("2. Select Actores por su key");
			System.out.println("3. Select All Peliculas");
			System.out.println("4. Select Peliculas por su key");
			
			String choice13 = scanner.nextLine();
			switch (choice13) {
			case "1":
				ArrayList<Actores> models=(ArrayList<Actores>) ah.getAllActores();
				for(Actores model : models) {
		            System.out.println(model.getCodigo()+" "+model.getNombre()+" "+model.getFNacimiento()+" "+model.getNacionalidad());
		        }
				break;
			case "2":
				Actores selIdAct = new Actores();
				System.out.println("Introduce id:");
				String selAct = scanner.nextLine();
				selIdAct=ah.getActoresById(Integer.parseInt(selAct));
				System.out.println(selIdAct.getCodigo()+" "+selIdAct.getNombre()+" "+selIdAct.getFNacimiento()+" "+selIdAct.getNacionalidad());
				break;
			case "3":
				ArrayList<Peliculas> listPel=(ArrayList<Peliculas>) ah.getAllPeliculas();
				for(Peliculas pelik : listPel) {
		            System.out.println(pelik.getCodigo()+" "+pelik.getTitulo()+" "+pelik.getFecha()+" "+pelik.getPresupuesto());
		        }
				break;
			case "4":
				Peliculas selIdPel = new Peliculas();
				System.out.println("Introduce id:");
				String selPel = scanner.nextLine();
				selIdPel=ah.getPeliculasById(Integer.parseInt(selPel));
				System.out.println(selIdPel.getCodigo()+" "+selIdPel.getTitulo()+" "+selIdPel.getFecha()+" "+selIdPel.getPresupuesto());
				break;
			

			}
			break;
		default:
			// The user input an unexpected choice.
			System.out.println("Introduce solo un numero");
		}

	}

}
