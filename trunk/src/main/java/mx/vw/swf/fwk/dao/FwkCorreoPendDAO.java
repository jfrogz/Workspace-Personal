package mx.vw.swf.fwk.dao;

import mx.vw.swf.fwk.dao.IFwkCorreoPendDAO;
import mx.vw.swf.fwk.model.FwkCorreoPend;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.vw.swf.fwk.model.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * FwkCorreoPend entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see FwkCorreoPend
 * @author MyEclipse Persistence Tools
 */
public class FwkCorreoPendDAO implements IFwkCorreoPendDAO {
    // property constants
    public static final String LENVIADO = "lenviado";

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved FwkCorreoPend entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * FwkCorreoPendDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            FwkCorreoPend entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(FwkCorreoPend entity) {
        EntityManagerHelper.log("saving FwkCorreoPend instance", Level.INFO,
                null);
        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent FwkCorreoPend entity. This operation must be
     * performed within the a database transaction context for the entity's data
     * to be permanently deleted from the persistence store, i.e., database.
     * This method uses the
     * {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * FwkCorreoPendDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            FwkCorreoPend entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(FwkCorreoPend entity) {
        EntityManagerHelper.log("deleting FwkCorreoPend instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(FwkCorreoPend.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved FwkCorreoPend entity and return it or a copy
     * of it to the sender. A copy of the FwkCorreoPend entity parameter is
     * returned when the JPA persistence mechanism has not previously been
     * tracking the updated entity. This operation must be performed within the
     * a database transaction context for the entity's data to be permanently
     * saved to the persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = FwkCorreoPendDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            FwkCorreoPend entity to update
     * @return FwkCorreoPend the persisted FwkCorreoPend entity instance, may
     *         not be the same
     * @throws RuntimeException
     *             if the operation fails
     */
    public FwkCorreoPend update(FwkCorreoPend entity) {
        EntityManagerHelper.log("updating FwkCorreoPend instance", Level.INFO,
                null);
        try {
            FwkCorreoPend result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public FwkCorreoPend findById(Long id) {
        EntityManagerHelper.log(
                "finding FwkCorreoPend instance with id: " + id, Level.INFO,
                null);
        try {
            FwkCorreoPend instance = getEntityManager().find(
                    FwkCorreoPend.class, id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all FwkCorreoPend entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the FwkCorreoPend property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            number of results to return.
     * @return List<FwkCorreoPend> found by query
     */
    @SuppressWarnings("unchecked")
    public List<FwkCorreoPend> findByProperty(String propertyName,
            final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log(
                "finding FwkCorreoPend instance with property: " + propertyName
                        + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from FwkCorreoPend model where model."
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

    public List<FwkCorreoPend> findByLenviado(Object lenviado,
            int... rowStartIdxAndCount) {
        return findByProperty(LENVIADO, lenviado, rowStartIdxAndCount);
    }

    /**
     * Find all FwkCorreoPend entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<FwkCorreoPend> all FwkCorreoPend entities
     */
    @SuppressWarnings("unchecked")
    public List<FwkCorreoPend> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all FwkCorreoPend instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from FwkCorreoPend model";
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