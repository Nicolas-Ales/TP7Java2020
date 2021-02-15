package ui;


import java.util.LinkedList;
import java.util.Scanner;

import entities.*;
import logic.Login;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login();

	public void start() {
		s = new Scanner(System.in);
		Persona p=login();
		System.out.println("Bienvenido "+p.getNombre()+" "+p.getApellido());
		System.out.println();
		
		String command;
		do {
			command=getCommand();
			executeCommand(command);
			System.out.println();
			
		}while(!command.equalsIgnoreCase("exit"));
		
		s.close();
	}

	private void executeCommand(String command) {
		switch (command) {
		case "list":
			System.out.println(ctrlLogin.getAll());
			break;
		case "find":
			System.out.println(find());
			break;
		case "search":
			System.out.println(search());
			break;
		case "new":
			System.out.println(nuevo());
			break;
		case "edit":
			System.out.println(edit());
			break;
		case "delete":
			delete();
			break;
		default:
			break;
		}
	}

	private String getCommand() {
		System.out.println("Ingrese el comando según la opción que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por tipo y nro de documento"); //solo debe devolver 1
		System.out.println("search\t\tlistar por apellido"); //puede devolver varios
		System.out.println("new\t\tcrea una nueva persona y asigna un rol existente");
		System.out.println("edit\t\tbusca por tipo y nro de documento y actualiza todos los datos");
		System.out.println("delete\t\tborra por tipo y nro de documento");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	public Persona login() {
		Persona p=new Persona();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine());

		System.out.print("password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p);
		
		return p;
		
	}
	
	public void delete() {
		Persona p =find();
		System.out.println(p);
		System.out.println("Desea eliminar esta persona de la base de datos? (s/n)");
		char sn = ' ';
		while(sn != 'n' && sn!= 's') {
			sn = s.nextLine().charAt(0);
		}
		if (sn == 's') {
			ctrlLogin.deletePersona(p);
			System.out.println("Se ha eliminado a la persona de la base de datos");
		};
		
	}
	
	public Persona edit() {
		//busca por tipo y nro de documento y actualiza todos los datos
		Persona p = find();
		char sn;
		
		sn=' '; //Nombre
		while(sn != 'n' && sn!= 's') {
			System.out.println("Nombre Actual: " + p.getNombre());
			System.out.println("Modificar(s/n): ");
			sn = s.nextLine().charAt(0);
		}
		if (sn == 's') {
			System.out.println("Nombre nuevo: ");
			p.setNombre(s.nextLine());
		};
			
		sn=' ';//Apellido
		while(sn != 'n' && sn!= 's') {
			System.out.println("Apellido Actual: " + p.getApellido());
			System.out.println("Modificar(s/n): ");
			sn = s.nextLine().charAt(0);
		}
		if (sn == 's') {
			System.out.println("Apellido nuevo: ");
			p.setApellido(s.nextLine());
		};	
		
		sn=' ';//Email
		while(sn != 'n' && sn!= 's') {
			System.out.println("Email Actual: " + p.getEmail());
			System.out.println("Modificar(s/n): ");
			sn = s.nextLine().charAt(0);
		}
		if (sn == 's') {
			System.out.println("Email nuevo: ");
			p.setEmail(s.nextLine());
		};
		
		sn=' ';//Telefono
		while(sn != 'n' && sn!= 's') {
			System.out.println("Telefono Actual: " + p.getTel());
			System.out.println("Modificar(s/n): ");
			sn = s.nextLine().charAt(0);
		}
		if (sn == 's') {
			System.out.println("Telefono nuevo: ");
			p.setTel(s.nextLine());
		};
		
		return ctrlLogin.actualizar(p);
	}
	
	public Persona nuevo() {
		System.out.println();
		Persona p = new Persona();
		Documento d= new Documento();
		p.setDocumento(d);
		
		System.out.println("Nombre: ");
		p.setNombre(s.nextLine());
		
		System.out.println("Apellido:");
		p.setApellido(s.nextLine());
		
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		System.out.println("Email: ");
		p.setEmail(s.nextLine());
		
		System.out.println("Telefono: ");
		p.setTel(s.nextLine());
		
		System.out.println("Contrasenia: ");
		p.setPassword(s.nextLine());
		
		p.setHabilitado(true);
		
		System.out.println(ctrlLogin.getAllRol());
		
		System.out.println("ingrese id de rol a agregar");
		
		p.addRol(ctrlLogin.getRolById(Integer.parseInt(s.nextLine())));
		//soy conciente que falta alguna validacion aca pero no me quiero trabar con eso ahora
		
		char sn = 'a'; 
		do {
			sn=' ';
			while(sn != 'n' && sn!= 's') {
				System.out.println("Quiere aniadir otro rol?(s/n): ");
				sn = s.next().charAt(0);
			}
			if (sn == 's') {
				System.out.println("id del rol a agregar: ");
				p.addRol(ctrlLogin.getRolById(s.nextInt()));
			};
		} while (sn != 'n');
		return ctrlLogin.nueva(p);
	}
	
	
	private Persona find() {
		System.out.println();
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		return ctrlLogin.getByDocumento(p);
	}
	
	public LinkedList<Persona> search(){
		Persona p=new Persona();
		
		System.out.print("Apellido: ");
		p.setApellido(s.nextLine());
		
		return ctrlLogin.getListByLastname(p);
	}
}
