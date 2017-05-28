package mx.vw.swf.fwk.dao;

import mx.vw.swf.fwk.dao.IFwkCatAppsDAO;
import mx.vw.swf.fwk.model.FwkCatApps;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.vw.swf.fwk.model.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * FwkCatApps entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see FwkCatApps
 * @author MyEclipse Persistence Tools
 */
public class FwkCatAppsDAO implements IFwkCatAppsDAO {
    // property constants
    public static final String APPLICATION_NAME = "applicationName";
    public static final String CREATED_BY = "createdBy";
    public static final String UPDATED_BY = "updatedBy";
    public static final String STATUS = "status";

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved FwkCatApps entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * FwkCatAppsDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            FwkCatApps entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(FwkCatApps entity) {
        EntityManagerHelper.log("saving FwkCatApps instance", Level.INFO, null);
        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent FwkCatApps entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * FwkCatAppsDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            FwkCatApps entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(FwkCatApps entity) {
        EntityManagerHelper.log("deleting FwkCatApps instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(FwkCatApps.class,
                    entity.getIdApp());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved FwkCatApps entity and return it or a copy of
     * it to the sender. A copy of the FwkCatApps entity parameter is returned
     * when the JPA persistence mechanism has not previously been tracking the
     * updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = FwkCatAppsDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            FwkCatApps entity to update
     * @return FwkCatApps the persisted FwkCatApps entity instance, may not be
     *         the same
     * @throws RuntimeException
     *             if the operation fails
     */
    public FwkCatApps update(FwkCatApps entity) {
        EntityManagerHelper.log("updating FwkCatApps instance", Level.INFO,
                null);
        try {
            FwkCatApps result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public FwkCatApps findById(Short id) {
        EntityManagerHelper.log("finding FwkCatApps instance with id: " + id,
                Level.INFO, null);
        try {
            FwkCatApps instance = getEntityManager().find(FwkCatApps.class, id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all FwkCatApps entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the FwkCatApps property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            number of results to return.
     * @return List<FwkCatApps> found by query
     */
    @SuppressWarnings("unchecked")
    public List<FwkCatApps> findByProperty(String propertyName,
            final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding FwkCatApps instance with property: "
                + propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from FwkCatApps model where model."
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

    public List<FwkCatApps> findByApplicationName(Object applicationName,
            int... rowStartIdxAndCount) {
        return findByProperty(APPLICATION_NAME, applicationName,
                rowStartIdxAndCount);
    }

    public List<FwkCatApps> findByCreatedBy(Object createdBy,
            int... rowStartIdxAndCount) {
        return findByProperty(CREATED_BY, createdBy, rowStartIdxAndCount);
    }

    public List<FwkCatApps> findByUpdatedBy(Object updatedBy,
            int... rowStartIdxAndCount) {
        return findByProperty(UPDATED_BY, updatedBy, rowStartIdxAndCount);
    }

    public List<FwkCatApps> findByStatus(Object status,
            int... rowStartIdxAndCount) {
        return findByProperty(STATUS, status, rowStartIdxAndCount);
    }

    /**
     * Find all FwkCatApps entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<FwkCatApps> all FwkCatApps entities
     */
    @SuppressWarnings("unchecked")
    public List<FwkCatApps> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all FwkCatApps instances", Level.INFO,
                null);
        try {
            final String queryString = "select model from FwkCatApps model";
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

}