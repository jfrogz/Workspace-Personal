package com.jfrogz.jdbc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaRowMapper implements RowMapper<Persona> {
    /**
     * Método que es llamado por la plantilla de Spring.
     * este es un método callback
     **/
    public Persona mapRow(ResultSet resultSet, int i) throws SQLException {
         /*Ceración del objeto persona por cada
        registro encontrado en el resultset*/
        Persona persona = new Persona();
        persona.setIdPersona(resultSet.getLong("id_Persona"));
        persona.setNombre(resultSet.getString("nombre"));
        persona.setApePaterno(resultSet.getString("ape_paterno"));
        persona.setApeMaterno(resultSet.getString("ape_materno"));
        persona.setEmail(resultSet.getString("email"));
        return persona;
    }
}
