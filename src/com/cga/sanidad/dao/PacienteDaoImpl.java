package com.cga.sanidad.dao;

import java.util.List;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.cga.sanidad.pojo.Paciente;

@Component("PacienteDao")

public class PacienteDaoImpl implements PacienteDao {
	//plantilla para evitar inyeccion de sql
	private NamedParameterJdbcTemplate jdbcTemplate;
	//hace referencia hacia el fichero config.xml, dataSource, va i
	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new  NamedParameterJdbcTemplate(dataSource);
	}
		
	
	@Override 
	public boolean save(Paciente paciente) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("idPaciente", paciente.getIdPaciente());
		map.addValue("nombre", paciente.getNombre());
		map.addValue("apellidos", paciente.getApellidos());
		map.addValue("edad", paciente.getEdad());
		map.addValue("telefono", paciente.getTelefono());
		//map.addValue("direccion", paciente.getDireccion());
		map.addValue("historial", paciente.getHistorial());
		
		return jdbcTemplate.update("insert into pacientes VALUES (:idPaciente,:nombre,:apellidos,:edad,:telefono,null,:historial)",map)==1;
	}


	@Override
	public List<Paciente> findAll() {
		// TODO Auto-generated method stub
		
		return jdbcTemplate.query("select * from pacientes ", new PacienteRowMapper() );
				
				
//				new RowMapper<Paciente>() {
//				
//			@Override
//			public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Paciente pac = new Paciente();
//				pac.setIdPaciente(rs.getInt("idPaciente"));
//				pac.setNombre(rs.getString("nombre"));
//				pac.setApellidos(rs.getString("apellidos"));
//				pac.setEdad(rs.getInt("edad"));
//				pac.setTelefono(rs.getInt("telefono"));
//				pac.setHistorial(rs.getString("historial"));
//				return pac;
//			}
//  });		
//				
	}

//returm (Paciente) jdbcTemplate.query ("select * from Paciente where idPaciente=:idPaciente", 
//new MapSqlParameterSource("idPaciente", id), new PacienteRowMapper());

	@Override
	public Paciente findById(int id) {
		// TODO Auto-generated method stub
		
		return jdbcTemplate.queryForObject("select * from Pacientes where idPaciente = :idPaciente", 
				new MapSqlParameterSource("idPaciente", id), new PacienteRowMapper());
	}


	@Override
	public List<Paciente> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from Pacientes " + "where nombre like :nombre", 
				new MapSqlParameterSource("nombre", "%"+ nombre+"%"), new PacienteRowMapper());
	}


	@Override
	public boolean update(Paciente pac) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("update Pacientes set  nombre= :nombre,"
				+ " edad= :edad where idPaciente=:idPaciente", new BeanPropertySqlParameterSource(pac)) ==1;
	}


	@Override
	public boolean delete(int idPaciente) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("delete from Pacientes where  idPaciente=:idPaciente ",
				new MapSqlParameterSource("idPaciente", idPaciente)) ==1;
	}
}
