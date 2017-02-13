package com.jfrogz.jdbc;

import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PeronsaDaoImpl implements PersonaDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        /*No es común que se utilicen las 2 plantillas, sin embargo si es posible
        La diferencia es el manejo de los parámetros por indice o por nombre
         */
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /*Query con Parámetros por nombre, omitimos la PK ya que es autoincrementable*/
    private static final String SQL_INSERT_PERSONA = "insert into persona (nombre, ape_paterno, email) values (:nombre, :apePaterno, :apeMaterno)";
    /*Query con Parámetros por indice ... values (?,?,?,?,?)
      Query con Parámetros por nombre ... values (:nombre, :apePaterno....)
     */
    private static final String SQL_UPDATE_PERSONA = "UPDATE PERSONA SET nombre = :nombre, ape_paterno = :apePaterno, ape_materno=apeMaterno, email=:email";
    private static final String SQL_DELETE_PERSONA = "DELETE FROM PERSONA WHERE id_persona=:idPersona";
    private static final String SQL_SELECT_PERSONA = "SELECT id_persona, nombre, ape_paterno, ape_materno, email from PERSONA";
    //Parametro por indice
    private static final String SQL_SELECT_PERSONA_BY_ID = SQL_SELECT_PERSONA + " WHERE id_persona= ?";


    public void insertPersona(Persona persona) {

    }

    public void updatePersona(Persona persona) {

    }

    public void deletePersona(Persona persona) {

    }

    public Persona findPersona(long idPersona) {
        return null;
    }

    public List<Persona> findAllPersonas() {
        RowMapper<Persona> personaRowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Persona.class);
        return this.jdbcTemplate.query(SQL_SELECT_PERSONA, personaRowMapper);
    }

    public int contadorPersonasPorNombre(Persona persona) {
        return 0;
    }

    public int contadorPersonas() {
        String sql = "SELECT count(*) FROM PERSONA";
        return this.jdbcTemplate.queryForInt(sql);
    }

    public Persona getPersonaByEmail(Persona persona) {
        return null;
    }
}
