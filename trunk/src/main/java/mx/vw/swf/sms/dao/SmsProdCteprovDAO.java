package mx.vw.swf.sms.dao;

import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.sms.dao.ISmsProdCteprovDAO;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.vw.swf.sms.model.SmsProdCteprov;
import mx.vw.swf.sms.model.SmsProdCteprovId;

/**
 * A data access object (DAO) providing persistence and search support for
 * SmsProdCteprov entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see SmsProdCteprov
 * @author MyEclipse Persistence Tools
 */
public class SmsProdCteprovDAO implements ISmsProdCteprovDAO {
    // property constants

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

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
     * SmsProdCteprovDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SmsProdCteprov entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(SmsProdCteprov entity) {
        EntityManagerHelper.log("saving SmsProdCteprov instance", Level.INFO,
                null);
        try {
            EntityManagerHelper.beginTransaction();
            getEntityManager().persist(entity);
             EntityManagerHelper.commit();
            EntityManagerHelper.getEntityManager().close();
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

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
     * SmsProdCteprovDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            SmsProdCteprov entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(SmsProdCteprov entity) {
        EntityManagerHelper.log("deleting SmsProdCteprov instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(SmsProdCteprov.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

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
     * entity = SmsProdCteprovDAO.update(entity);
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
    public SmsProdCteprov update(SmsProdCteprov entity) {
        EntityManagerHelper.log("updating SmsProdCteprov instance", Level.INFO,
                null);
        try {
            SmsProdCteprov result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SmsProdCteprov findById(SmsProdCteprovId id) {
        EntityManagerHelper.log("finding SmsProdCteprov instance with id: "
                + id, Level.INFO, null);
        try {
            SmsProdCteprov instance = getEntityManager().find(
                    SmsProdCteprov.class, id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

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
     *            number of results to return.
     * @return List<SmsProdCteprov> found by query
     */
    @SuppressWarnings("unchecked")
    public List<SmsProdCteprov> findByProperty(String propertyName,
            final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log(
                "finding SmsProdCteprov instance with property: "
                        + propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from SmsProdCteprov model where model."
                    + propertyName + "= :propertyValue";
            Query query = getEntityManager().createQuery(queryString);
            query.setParameter("propertyValue", value);
            if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
                int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
                if (rowStartIdx > 0) {
                    query.setFirstResult(rowStartIdx);
                }

                if (rowStartIdxAndCount.length > 1) {
                    int rowCount = Math.max(0, rowStartIdxAndCount[1]);
                    if (rowCount > 0) {
                        query.setMaxResults(rowCount);
                    }
                }
            }
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find by property name failed",
                    Level.SEVERE, re);
            throw re;
        }
    }

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
    @SuppressWarnings("unchecked")
    public List<SmsProdCteprov> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SmsProdCteprov instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from SmsProdCteprov model";
            Query query = getEntityManager().createQuery(queryString);
            if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
                int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
                if (rowStartIdx > 0) {
                    query.setFirstResult(rowStartIdx);
                }

                if (rowStartIdxAndCount.length > 1) {
                    int rowCount = Math.max(0, rowStartIdxAndCount[1]);
                    if (rowCount > 0) {
                        query.setMaxResults(rowCount);
                    }
                }
            }
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }
    
    public void deleteQuery(final Object value) {
        EntityManagerHelper.log("deleting SmsProdCteprov instance", Level.INFO, null);
        try {
            EntityManagerHelper.beginTransaction();
            final String queryString = "delete from SmsProdCteprov model where model.smsCteProv.id="+value;
            getEntityManager().createQuery(queryString).executeUpdate();
            EntityManagerHelper.commit();
            EntityManagerHelper.getEntityManager().close();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

}