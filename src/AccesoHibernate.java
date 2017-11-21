

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.HibernateUtil;
import modelo.Empleado;
import modelo.Lugar;
import modelo.Notificacion;

public class AccesoHibernate {

	Session session;

	public AccesoHibernate() {

		HibernateUtil util = new HibernateUtil();
		session = util.getSessionFactory().openSession();
	}

	public void cargarTodo() {
		obtenerLugar();
		obtenerNotificacion();
		obtenerEmpleado();

	}

	public HashMap<Integer, Empleado> obtenerEmpleado() {
		Query q = session.createQuery("select dep from Empleado dep");
		List results = q.list();
		int clave;
		HashMap<Integer, Empleado> depositosCreados = new HashMap<Integer, Empleado>();
		Iterator equiposIterator = results.iterator();

		while (equiposIterator.hasNext()) {
			Empleado team = (Empleado) equiposIterator.next();

			depositosCreados.put(team.getCodInterno(), team);

		}

		return depositosCreados;
	}

	public HashMap<Integer, Lugar> obtenerLugar() {
		Query q = session.createQuery("select dis from Lugar dis");
		List results = q.list();
		String clave;
		HashMap<Integer, Lugar> dispensadorCreados = new HashMap<Integer, Lugar>();
		Iterator equiposIterator = results.iterator();

		while (equiposIterator.hasNext()) {
			Lugar team = (Lugar) equiposIterator.next();
			// team = new Dispensador(team.getClave(), team.getNombreProducto(),
			// team.getPrecio(), team.getCantidad());
			// clave = team.getClave();
			dispensadorCreados.put(team.getCodParque(), team);

		}
		return dispensadorCreados;
	}

	public HashMap<Integer, Notificacion> obtenerNotificacion() {
		Query q = session.createQuery("select dis from Notificacion dis");
		List results = q.list();
		String clave;
		HashMap<Integer, Notificacion> dispensadorCreados = new HashMap<Integer, Notificacion>();
		Iterator equiposIterator = results.iterator();

		while (equiposIterator.hasNext()) {
			Notificacion team = (Notificacion) equiposIterator.next();
			// team = new Dispensador(team.getClave(), team.getNombreProducto(),
			// team.getPrecio(), team.getCantidad());
			// clave = team.getClave();
			dispensadorCreados.put(team.getCodNotificacion(), team);

		}
		return dispensadorCreados;
	}

	public void guardarEmpleados(Empleado empleados) {
		session.beginTransaction();
		Query q = session.createQuery("select max(codInterno) from Empleado");
		List results = q.list();
		int id =(int) results.iterator().next()+1;
		
		Empleado lug= new Empleado(id,empleados.getDni(),empleados.getNombre(), empleados.getApellidos(), empleados.getFechaNacimiento(),empleados.getCodParque());
		HashMap<Integer, Empleado> lugar = new HashMap<Integer, Empleado>();
		lugar.put(id, lug);
		for (Entry<Integer, Empleado> entry : lugar.entrySet()) {
			session.save(entry.getValue());

		}
		
		session.getTransaction().commit();
		
		
	}

	public void guardarLugar(Lugar ar) {
		
		session.beginTransaction();
		Query q = session.createQuery("select max(codParque) from Lugar");
		List results = q.list();
		int id =(int) results.iterator().next()+1;
		
		Lugar lug= new Lugar(id,ar.getNombre(),ar.getTelefono(),ar.getDireccion());
		HashMap<Integer, Lugar> lugar = new HashMap<Integer, Lugar>();
		lugar.put(id, lug);
		for (Entry<Integer, Lugar> entry : lugar.entrySet()) {
			session.save(entry.getValue());

		}
		
		session.getTransaction().commit();
	}

	public void guardarNotificacion(Notificacion notificacion) {
		session.beginTransaction();
		Query q = session.createQuery("select max(codNotificacion) from Notificacion");
		List results = q.list();
		int id =(int) results.iterator().next()+1;
		
		Notificacion lug= new Notificacion(id,notificacion.getDireccion(),notificacion.getUrgencia(),notificacion.getTipo());
		HashMap<Integer, Notificacion> lugar = new HashMap<Integer, Notificacion>();
		lugar.put(id, lug);
		for (Entry<Integer, Notificacion> entry : lugar.entrySet()) {
			session.save(entry.getValue());

		}
		
		session.getTransaction().commit();
	}
	
	public void borrarLugar(String nombre) {
		session.beginTransaction();
        Query q = session.createQuery("delete from Lugar where codParque = '"+nombre+"'");
        q.executeUpdate();
        session.getTransaction().commit();
    }
	
	public void borrarNotificacion(String nombre) {
		session.beginTransaction();
        Query q = session.createQuery("delete from Notificacion where codNotificacion = '"+nombre+"'");
        q.executeUpdate();
        session.getTransaction().commit();
    }
	
	public void borrarEmpleado(String nombre) {
		session.beginTransaction();
        Query q = session.createQuery("delete from Empleado where codInterno = '"+nombre+"'");
        q.executeUpdate();
        session.getTransaction().commit();
    }
	
	public void borrarEmpleadoTodo() {
		session.beginTransaction();
        Query q = session.createQuery("delete from Empleado");
        q.executeUpdate();
        session.getTransaction().commit();
    }
	
	public void borrarLugarTodo() {
		session.beginTransaction();
        Query q = session.createQuery("delete from Lugar");
        q.executeUpdate();
        session.getTransaction().commit();
    }
	
	public void borrarNotificacionTodo() {
		session.beginTransaction();
        Query q = session.createQuery("delete from Notificacion");
        q.executeUpdate();
        session.getTransaction().commit();
    }
}
