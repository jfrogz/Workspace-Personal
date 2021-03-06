package mx.vw.swf.sms.dao;

import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.sms.dao.ISmsProductoDAO;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.vw.swf.sms.model.SmsProducto;

/**
 * A data access object (DAO) providing persistence and search support for
 * SmsProducto entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see SmsProducto
 * @author MyEclipse Persistence Tools
 */
public class SmsProductoDAO implements ISmsProductoDAO {
    // property constants
    public static final String CLAVE = "clave";
    public static final String NOMBRE = "nombre";
    public static final String ID_SAP = "idSap";
    public static final String ESTATUS = "estatus";

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved SmsProducto entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * SmsProductoDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SmsProducto entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(SmsProducto entity) {
        EntityManagerHelper
                .log("saving SmsProducto instance", Level.INFO, null);
        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent SmsProducto entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * SmsProductoDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            SmsProducto entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(SmsProducto entity) {
        EntityManagerHelper.log("deleting SmsProducto instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(SmsProducto.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved SmsProducto entity and return it or a copy of
     * it to the sender. A copy of the SmsProducto entity parameter is returned
     * when the JPA persistence mechanism has not previously been tracking the
     * updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = SmsProductoDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SmsProducto entity to update
     * @return SmsProducto the persisted SmsProducto entity instance, may not be
     *         the same
     * @throws RuntimeException
     *             if the operation fails
     */
    public SmsProducto update(SmsProducto entity) {
        EntityManagerHelper.log("updating SmsProducto instance", Level.INFO,
                null);
        try {
            SmsProducto result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SmsProducto findById(Long id) {
        EntityManagerHelper.log("finding SmsProducto instance with id: " + id,
                Level.INFO, null);
        try {
            SmsProducto instance = getEntityManager().find(SmsProducto.class,
                    id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all SmsProducto entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the SmsProducto property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            number of results to return.
     * @return List<SmsProducto> found by query
     */
    @SuppressWarnings("unchecked")
    public List<SmsProducto> findByProperty(String propertyName,
            final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding SmsProducto instance with property: "
                + propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from SmsProducto model where model."
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

    public List<SmsProducto> findByClave(Object clave,
            int... rowStartIdxAndCount) {
        return findByProperty(CLAVE, clave, rowStartIdxAndCount);
    }

    public List<SmsProducto> findByNombre(Object nombre,
            int... rowStartIdxAndCount) {
        return findByProperty(NOMBRE, nombre, rowStartIdxAndCount);
    }

    

    public List<SmsProducto> findByEstatus(Object estatus,
            int... rowStartIdxAndCount) {
        return findByProperty(ESTATUS, estatus, rowStartIdxAndCount);
    }

    /**
     * Find all SmsProducto entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SmsProducto> all SmsProducto entities
     */
    @SuppressWarnings("unchecked")
    public List<SmsProducto> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SmsProducto instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from SmsProducto model";
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
    
    public List<SmsProducto> findByIdSap(Object idSap,
            int... rowStartIdxAndCount) {
            try {
            final String queryString = "select model from SmsProducto model where UPPER(model.idSap) =  UPPER(:propertyValueIdSap)";                                       
                    
            Query query = getEntityManager().createQuery(queryString);
            query.setParameter("propertyValueIdSap", idSap);
            
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
        }    }

}