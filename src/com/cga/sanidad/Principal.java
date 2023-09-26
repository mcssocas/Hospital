package com.cga.sanidad;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.cga.sanidad.dao.PacienteDao;
import com.cga.sanidad.pojo.Paciente;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		
		ApplicationContext apc = new ClassPathXmlApplicationContext("ConfigSpring.xml");
		
		Scanner sc = new Scanner(System.in);

		PacienteDao pacienteDao = (PacienteDao) apc.getBean("PacienteDao");

		Paciente pac = (Paciente) apc.getBean("pac");

		try {
//			pacienteDao.save(pac); 
		} catch (CannotGetJdbcConnectionException e) {
			System.out.println("Credenciales, configuraciones!!");
		} catch (DataAccessException ex) {
			System.out.println("Error en el SQL!!");
		}

		((ClassPathXmlApplicationContext) apc).close();

//		List<Paciente> pacientes = pacienteDao.findAll();
//
//		for (Paciente paciente2 : pacientes) {
//			System.out.println("Mostrar: " + paciente2);
//		}

		//lista por id y por nombre
//		System.out.println(pacienteDao.findById(1001));
//		System.out.println(pacienteDao.findById(1002));
//		System.out.println(pacienteDao.findByNombre("Pepito").toString());
//		
//		Paciente pac1 = pacienteDao.findById(1234);
//		System.out.println("Paciente con id 1234");
//		
//		pac1.setNombre("Maria");
//		pac1.setApellidos("Gutierrez Salcedo");
//		
//		if (pacienteDao.update(pac1)) {
//			System.out.println("Paciente actualizado " + pac1);
//		}
//		Paciente pac2 = pacienteDao.findById(1235);		
//		if (pacienteDao.delete(pac2.getIdPaciente())) {
//			System.out.println("Paciente eliminado " + pac2.getNombre());
//		}
//		
		//Menu para datos de paciente
		String str = "";
		boolean valida = true;
		int nId = 0;
		int n=0;
		do {
			
			n = Integer.parseInt(JOptionPane.showInputDialog("\nSeleccione una opción: \n"
					+ "1. Mostrar pacientes. \n "
					+ "2. Añadir paciente. \n"
					+ "3. Modificar paciente. \n"
					+ "4. Eliminar paciente. \n"
					+ "5. Salir. \n"));
			//valida la entrada 
			try {
				//n = Integer.parseInt(n);
				} catch (Exception e) {
					System.out.println("La opción no es valida\n");
					continue;
				}
			switch (n) {
			case 1: 
				List<Paciente> pacientes = pacienteDao.findAll();

				for (Paciente paciente2 : pacientes) {
					System.out.println("Mostrar: " + paciente2);
				} break;
				
//			case 2: listar();break;
				
			case 3: 
				nId = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el id a modificar : "));
				//System.out.println("Introduzca el id a modificar : ");
				//nId = sc.nextInt();
				Paciente pac1 = pacienteDao.findById(nId);
				//System.out.println("Paciente con id " + nId);
				//System.out.println(pac1);
				//sc.nextLine();
				
				//System.out.println("Nombre: " + pac1.getNombre());
				pac1.setNombre(JOptionPane.showInputDialog(null, "Nombre: ", pac1.getNombre()));
				pac1.setApellidos(JOptionPane.showInputDialog(null, "Apellidos: ", pac1.getApellidos()));
				pac1.setEdad(Integer.parseInt(JOptionPane.showInputDialog(null, "Edad: ", pac1.getEdad())));
				pac1.setTelefono(Integer.parseInt(JOptionPane.showInputDialog(null, "Telefono: ", pac1.getTelefono())));
				pac1.setHistorial(JOptionPane.showInputDialog(null, "Historial: ", pac1.getHistorial()));
				
				
				
//				System.out.println("Apellidos: "+ pac1.getApellidos() );
//				pac1.setApellidos(sc.nextLine());
//				
//				System.out.println("Historial: "+ pac1.getHistorial() );
//				pac1.setHistorial(sc.nextLine());
//
//				System.out.println("Edad: "+ pac1.getEdad() );
//				pac1.setEdad(sc.nextInt());
//
//				System.out.println("Telefono: " + pac1.getTelefono() );
//				pac1.setTelefono(sc.nextInt());

				
				
				if (pacienteDao.update(pac1)) {
					System.out.println("Paciente actualizado " + pac1);
				}
				
				break;
				
//			case 4: disponibles();break;
			case 5: valida = false; break;
			}
	}while (valida); 

}
	
}