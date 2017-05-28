package mx.vw.swf.security.dao;

import java.util.List;
import mx.vw.swf.security.model.SegPerfil;

/**
 * Interface for SegPerfilDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISegPerfilDAO {
    /**
     * Perform an initial save of a previously unsaved SegPerfil entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISegPerfilDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SegPerfil entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(SegPerfil entity);

    /**
     * Delete a persistent SegPerfil entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISegPerfilDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            SegPerfil entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(SegPerfil entity);

    /**
     * Persist a previously saved SegPerfil entity and return it or a copy of it
     * to the sender. A copy of the SegPerfil entity parameter is returned when
     * the JPA persistence mechanism has not previously been tracking the
     * updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = ISegPerfilDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SegPerfil entity to update
     * @return SegPerfil the persisted SegPerfil entity instance, may not be the
     *         same
     * @throws RuntimeException
     *             if the operation fails
     */
    public SegPerfil update(SegPerfil entity);

    public SegPerfil findById(Integer id);

    /**
     * Find all SegPerfil entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the SegPerfil property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SegPerfil> found by query
     */
    public List<SegPerfil> findByProperty(String propertyName, Object value,
            int... rowStartIdxAndCount);

    public List<SegPerfil> findByPerfil(Object perfil,
            int... rowStartIdxAndCount);

    public List<SegPerfil> findByEstatus(Object estatus,
            int... rowStartIdxAndCount);

    /**
     * Find all SegPerfil entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SegPerfil> all SegPerfil entities
     */
    public List<SegPerfil> findAll(int... rowStartIdxAndCount);
}