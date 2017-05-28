package mx.vw.swf.security.dao;

import java.util.List;
import mx.vw.swf.security.model.SegPerfilUsr;
import mx.vw.swf.security.model.SegPerfilUsrId;

/**
 * Interface for SegPerfilUsrDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISegPerfilUsrDAO {
    /**
     * Perform an initial save of a previously unsaved SegPerfilUsr entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISegPerfilUsrDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SegPerfilUsr entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(SegPerfilUsr entity);

    /**
     * Delete a persistent SegPerfilUsr entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISegPerfilUsrDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            SegPerfilUsr entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(SegPerfilUsr entity);

    /**
     * Persist a previously saved SegPerfilUsr entity and return it or a copy of
     * it to the sender. A copy of the SegPerfilUsr entity parameter is returned
     * when the JPA persistence mechanism has not previously been tracking the
     * updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = ISegPerfilUsrDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SegPerfilUsr entity to update
     * @return SegPerfilUsr the persisted SegPerfilUsr entity instance, may not
     *         be the same
     * @throws RuntimeException
     *             if the operation fails
     */
    public SegPerfilUsr update(SegPerfilUsr entity);

    public SegPerfilUsr findById(SegPerfilUsrId id);

    /**
     * Find all SegPerfilUsr entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the SegPerfilUsr property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SegPerfilUsr> found by query
     */
    public List<SegPerfilUsr> findByProperty(String propertyName, Object value,
            int... rowStartIdxAndCount);

    /**
     * Find all SegPerfilUsr entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SegPerfilUsr> all SegPerfilUsr entities
     */
    public List<SegPerfilUsr> findAll(int... rowStartIdxAndCount);
}