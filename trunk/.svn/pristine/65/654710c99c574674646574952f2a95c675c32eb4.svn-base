package mx.vw.swf.security.dao;

import java.util.List;
import mx.vw.swf.security.model.SegPermisoPerfil;
import mx.vw.swf.security.model.SegPermisoPerfilId;

/**
 * Interface for SegPermisoPerfilDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISegPermisoPerfilDAO {
    /**
     * Perform an initial save of a previously unsaved SegPermisoPerfil entity.
     * All subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISegPermisoPerfilDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SegPermisoPerfil entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(SegPermisoPerfil entity);

    /**
     * Delete a persistent SegPermisoPerfil entity. This operation must be
     * performed within the a database transaction context for the entity's data
     * to be permanently deleted from the persistence store, i.e., database.
     * This method uses the
     * {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISegPermisoPerfilDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            SegPermisoPerfil entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(SegPermisoPerfil entity);

    /**
     * Persist a previously saved SegPermisoPerfil entity and return it or a
     * copy of it to the sender. A copy of the SegPermisoPerfil entity parameter
     * is returned when the JPA persistence mechanism has not previously been
     * tracking the updated entity. This operation must be performed within the
     * a database transaction context for the entity's data to be permanently
     * saved to the persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = ISegPermisoPerfilDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SegPermisoPerfil entity to update
     * @return SegPermisoPerfil the persisted SegPermisoPerfil entity instance,
     *         may not be the same
     * @throws RuntimeException
     *             if the operation fails
     */
    public SegPermisoPerfil update(SegPermisoPerfil entity);

    public SegPermisoPerfil findById(SegPermisoPerfilId id);

    /**
     * Find all SegPermisoPerfil entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the SegPermisoPerfil property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SegPermisoPerfil> found by query
     */
    public List<SegPermisoPerfil> findByProperty(String propertyName,
            Object value, int... rowStartIdxAndCount);

    /**
     * Find all SegPermisoPerfil entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SegPermisoPerfil> all SegPermisoPerfil entities
     */
    public List<SegPermisoPerfil> findAll(int... rowStartIdxAndCount);
}