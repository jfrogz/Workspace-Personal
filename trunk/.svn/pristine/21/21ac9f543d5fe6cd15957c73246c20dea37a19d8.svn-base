package mx.vw.swf.fwk.dao;

import mx.vw.swf.fwk.dao.IFwkLoggerDAO;
import mx.vw.swf.fwk.model.FwkLogger;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.vw.swf.fwk.model.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * FwkLogger entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see FwkLogger
 * @author MyEclipse Persistence Tools
 */
public class FwkLoggerDAO implements IFwkLoggerDAO {
    // property constants
    public static final String NIVEL = "nivel";
    public static final String CLASE = "clase";
    public static final String METODO = "metodo";
    public static final String LINEA = "linea";
    public static final String MENSAJE = "mensaje";
    public static final String STACKTRACE = "stacktrace";
    public static final String USERID = "userid";

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved FwkLogger entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * FwkLoggerDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            FwkLogger entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(FwkLogger entity) {
        EntityManagerHelper.log("saving FwkLogger instance", Level.INFO, null);
        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent FwkLogger entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * FwkLoggerDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            FwkLogger entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(FwkLogger entity) {
        EntityManagerHelper
                .log("deleting FwkLogger instance", Level.INFO, null);
        try {
            entity = getEntityManager().getReference(FwkLogger.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved FwkLogger entity and return it or a copy of it
     * to the sender. A copy of the FwkLogger entity parameter is returned when
     * the JPA persistence mechanism has not previously been tracking the
     * updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = FwkLoggerDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            FwkLogger entity to update
     * @return FwkLogger the persisted FwkLogger entity instance, may not be the
     *         same
     * @throws RuntimeException
     *             if the operation fails
     */
    public FwkLogger update(FwkLogger entity) {
        EntityManagerHelper
                .log("updating FwkLogger instance", Level.INFO, null);
        try {
            FwkLogger result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public FwkLogger findById(Long id) {
        EntityManagerHelper.log("finding FwkLogger instance with id: " + id,
                Level.INFO, null);
        try {
            FwkLogger instance = getEntityManager().find(FwkLogger.class, id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all FwkLogger entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the FwkLogger property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            number of results to return.
     * @return List<FwkLogger> found by query
     */
    @SuppressWarnings("unchecked")
    public List<FwkLogger> findByProperty(String propertyName,
            final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding FwkLogger instance with property: "
                + propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from FwkLogger model where model."
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

    public List<FwkLogger> findByNivel(Object nivel, int... rowStartIdxAndCount) {
        return findByProperty(NIVEL, nivel, rowStartIdxAndCount);
    }

    public List<FwkLogger> findByClase(Object clase, int... rowStartIdxAndCount) {
        return findByProperty(CLASE, clase, rowStartIdxAndCount);
    }

    public List<FwkLogger> findByMetodo(Object metodo,
            int... rowStartIdxAndCount) {
        return findByProperty(METODO, metodo, rowStartIdxAndCount);
    }

    public List<FwkLogger> findByLinea(Object linea, int... rowStartIdxAndCount) {
        return findByProperty(LINEA, linea, rowStartIdxAndCount);
    }

    public List<FwkLogger> findByMensaje(Object mensaje,
            int... rowStartIdxAndCount) {
        return findByProperty(MENSAJE, mensaje, rowStartIdxAndCount);
    }

    public List<FwkLogger> findByStacktrace(Object stacktrace,
            int... rowStartIdxAndCount) {
        return findByProperty(STACKTRACE, stacktrace, rowStartIdxAndCount);
    }

    public List<FwkLogger> findByUserid(Object userid,
            int... rowStartIdxAndCount) {
        return findByProperty(USERID, userid, rowStartIdxAndCount);
    }

    /**
     * Find all FwkLogger entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<FwkLogger> all FwkLogger entities
     */
    @SuppressWarnings("unchecked")
    public List<FwkLogger> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all FwkLogger instances", Level.INFO,
                null);
        try {
            final String queryString = "select model from FwkLogger model";
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