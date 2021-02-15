package logic;

import java.util.LinkedList;

import data.*;
import entities.*;

public class Login {
	private DataPersona dp;
	private DataRol dr;
	
	public Login() {
		dp=new DataPersona();
		dr=new DataRol();
	}
	
	public Persona validate(Persona p) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		return dp.getByUser(p);
	}

	public LinkedList<Persona> getAll(){
		return dp.getAll();
	}

	public Persona getByDocumento(Persona per) {
		return dp.getByDocumento(per);
		
	}
	
	public Persona actualizar(Persona per) {
		return dp.update(per);
	}
	
	public void deletePersona(Persona p) {
		dr.deleteRols(p);
		dp.delete(p);
	}
	
	public Persona nueva(Persona per) {
		dp.add(per);
		dr.InsertaRoles(per);
		return per;
	}
	
	public LinkedList<Rol> getAllRol(){
		return dr.getAll();
	}
	
	public Rol getRolById(int id) {
		Rol r = new Rol();
		r.setId(id);
		return dr.getById(r);
	}

	public LinkedList<Persona> getListByLastname(Persona p) {
		return dp.getListByLastname(p);
	}
}
