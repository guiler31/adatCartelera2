package hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Actores;
import entidades.Peliculas;

public class AccesoHibernate {

	Session session;

	public AccesoHibernate() {

		HibernateUtil util = new HibernateUtil();
		session = util.getSessionFactory().openSession();
	}

	public Integer maxCodigo(String tipo) {
		session.beginTransaction();
		Query q=null;
		if(tipo.equalsIgnoreCase("actores")) {
			q = session.createQuery("select max(codigo) from Actores");
		}else if(tipo.equalsIgnoreCase("peliculas")) {
			q = session.createQuery("select max(codigo) from Peliculas");
		}else if(tipo.equalsIgnoreCase("reparto")){
			q = session.createQuery("select max(codigo) from Reparto");
		}
		List results = q.list();
		int id = (int) results.iterator().next() + 1;
		return id;
	}

	public void HibAddOrUp(Actores user, Peliculas pelicula) {
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			if (user != null) {
				session.saveOrUpdate(user);
			} else if (pelicula != null) {
				session.saveOrUpdate(pelicula);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public void HibDelete(int userid, String objeto) {
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			if (objeto.equalsIgnoreCase("actores")) {
				Actores user = (Actores) session.load(Actores.class, new Integer(userid));
				session.delete(user);
			} else if (objeto.equalsIgnoreCase("peliculas")) {
				Peliculas pelicula = (Peliculas) session.load(Peliculas.class, new Integer(userid));
				session.delete(pelicula);
			}

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	public List<Actores> getAllActores() {
		List<Actores> users = new ArrayList<Actores>();
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			users = session.createQuery("from Actores").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return users;
	}

	public List<Peliculas> getAllPeliculas() {
		List<Peliculas> users = new ArrayList<Peliculas>();
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			users = session.createQuery("from Peliculas").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return users;
	}

	

	public Actores getActoresById(int userid) {
		Actores user = null;
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Actores where codigo = :codigo";
			Query query = session.createQuery(queryString);
			query.setInteger("codigo", userid);
			user = (Actores) query.uniqueResult();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return user;
	}

	public Peliculas getPeliculasById(int userid) {
		Peliculas user = null;
		Transaction trns = null;
		try {
			trns = session.beginTransaction();
			String queryString = "from Peliculas where codigo = :codigo";
			Query query = session.createQuery(queryString);
			query.setInteger("codigo", userid);
			user = (Peliculas) query.uniqueResult();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return user;
	}

	

}