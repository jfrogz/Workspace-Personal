package mx.vw.swf.sms.dao;

import java.util.List;
import mx.vw.swf.sms.model.SmsProdCteprov;
import mx.vw.swf.sms.model.SmsProdCteprovId;

/**
 * Interface for SmsProdCteprovDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISmsProdCteprovDAO {
    /**
     * Perform an initial save of a previously unsaved SmsProdCteprov entity.
     * All subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISmsProdCteprovDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SmsProdCteprov entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(SmsProdCteprov entity);

    /**
     * Delete a persistent SmsProdCteprov entity. This operation must be
     * performed within the a database transaction context for the entity's data
     * to be permanently deleted from the persistence store, i.e., database.
     * This method uses the
     * {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISmsProdCteprovDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            SmsProdCteprov entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(SmsProdCteprov entity);

    /**
     * Persist a previously saved SmsProdCteprov entity and return it or a copy
     * of it to the sender. A copy of the SmsProdCteprov entity parameter is
     * returned when the JPA persistence mechanism has not previously been
     * tracking the updated entity. This operation must be performed within the
     * a database transaction context for the entity's data to be permanently
     * saved to the persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = ISmsProdCteprovDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SmsProdCteprov entity to update
     * @return SmsProdCteprov the persisted SmsProdCteprov entity instance, may
     *         not be the same
     * @throws RuntimeException
     *             if the operation fails
     */
    public SmsProdCteprov update(SmsProdCteprov entity);

    public SmsProdCteprov findById(SmsProdCteprovId id);

    /**
     * Find all SmsProdCteprov entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the SmsProdCteprov property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SmsProdCteprov> found by query
     */
    public List<SmsProdCteprov> findByProperty(String propertyName,
            Object value, int... rowStartIdxAndCount);

    /**
     * Find all SmsProdCteprov entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SmsProdCteprov> all SmsProdCteprov entities
     */
    public List<SmsProdCteprov> findAll(int... rowStartIdxAndCount);
}