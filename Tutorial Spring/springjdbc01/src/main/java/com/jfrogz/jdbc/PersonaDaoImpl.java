package com.jfrogz.jdbc;

import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import sun.invoke.empty.Empty;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PersonaDaoImpl implements PersonaDao {
    /*Query con Parámetros por nombre, omitimos la PK ya que es autoincrementable*/
    private static final String SQL_INSERT_PERSONA = "insert into persona (nombre, ape_paterno, ape_materno, email) values (:nombre, :apePaterno, :apeMaterno, :email)";
    /*Query con Parámetros por indice ... values (?,?,?,?,?)
      Query con Parámetros por nombre ... values (:nombre, :apePaterno....)
     */
    private static final String SQL_UPDATE_PERSONA = "UPDATE PERSONA SET nombre = :nombre, ape_paterno = :apePaterno, ape_materno=:apeMaterno, email=:email WHERE id_persona=:idPersona";
    private static final String SQL_DELETE_PERSONA = "DELETE FROM PERSONA WHERE id_persona=:idPersona";
    private static final String SQL_SELECT_PERSONA = "SELECT id_persona, nombre, ape_paterno, ape_materno, email from PERSONA";
    //Parametro por indice
    private static final String SQL_SELECT_PERSONA_BY_ID = SQL_SELECT_PERSONA + " WHERE id_persona= ?";
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

    public void insertPersona(Persona persona) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(persona);
        this.namedParameterJdbcTemplate.update(SQL_INSERT_PERSONA, parameterSource);
    }

    public void updatePersona(Persona persona) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(persona);
        this.namedParameterJdbcTemplate.update(SQL_UPDATE_PERSONA, parameterSource);
    }

    public void deletePersona(Persona persona) {

    }

    public Persona findPersona(long idPersona) {
        Persona persona = null;
        try {
            //Utilizamos la clase PersonaRowMapper
            persona = jdbcTemplate.queryForObject(SQL_SELECT_PERSONA_BY_ID, new PersonaRowMapper(), idPersona);
        } catch (EmptyResultDataAccessException e) {
            persona = null;
        }
        return persona;
        /*Esta es otra forma de utilizar la clase PersonaRowMapper
        BeanPropertyRowMapper<Persona> personaRowMapper =  BeanPropertyRowMapper.newInstance(Persona.class)
        return jdbcTemplate.queryForObject(SQL_SELECT_PERSONA_BY_ID, personaRowMapper, idPersona);
         */
    }

    public List<Persona> findAllPersonas() {
        RowMapper<Persona> personaRowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Persona.class);
        return this.jdbcTemplate.query(SQL_SELECT_PERSONA, personaRowMapper);
    }

    public int contadorPersonasPorNombre(Persona persona) {
        String sql = "SELECT count(*) from PERSONA WHERE nombre= :nombre";
        /*Permite evitar crear un MAP de parametros y utilizar directamente el objecto persona
        los atributos que coincidan con el nombre de los parametros por nombre del query
        seran utilizados y proporcionados como atributos al query */
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(persona);
        //unicamente retorna un valor el método queryForInt
        return this.namedParameterJdbcTemplate.queryForInt(sql, namedParameters);
    }

    public int contadorPersonas() {
        String sql = "SELECT count(*) FROM PERSONA";
        return this.jdbcTemplate.queryForInt(sql);
    }

    public Persona getPersonaByEmail(Persona persona) {
        String sql = "SELECT * FROM PERSONA WHERE email = ?";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(persona);
        /*Si no tiene el objeto RowMapper, se puede utilizar la siguiente
        linea para crear este objeto*/
        //RowMapper<Persona> personaRowMapper = ParameterizedBeanPropertyRowMapper.newInstance(Persona.class);

        return this.jdbcTemplate.queryForObject(sql, new PersonaRowMapper(),persona.getEmail());
    }
}
