package mx.vw.swf.security.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.vw.swf.security.model.SegPermisoPerfil;
import mx.vw.swf.security.model.SegPermisoPerfilId;
import mx.vw.swf.fwk.model.EntityManagerHelper;


/**
 * A data access object (DAO) providing persistence and search support for
 * SegPermisoPerfil entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see SegPermisoPerfil
 * @author MyEclipse Persistence Tools
 */
public class SegPermisoPerfilDAO implements ISegPermisoPerfilDAO {
    // property constants

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

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
     * SegPermisoPerfilDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SegPermisoPerfil entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(SegPermisoPerfil entity) {
        EntityManagerHelper.log("saving SegPermisoPerfil instance", Level.INFO,
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
     * Delete a persistent SegPermisoPerfil entity. This operation must be
     * performed within the a database transaction context for the entity's data
     * to be permanently deleted from the persistence store, i.e., database.
     * This method uses the
     * {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * SegPermisoPerfilDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            SegPermisoPerfil entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(SegPermisoPerfil entity) {
        EntityManagerHelper.log("deleting SegPermisoPerfil instance",
                Level.INFO, null);
        try {
            entity = getEntityManager().getReference(SegPermisoPerfil.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

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
     * entity = SegPermisoPerfilDAO.update(entity);
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
    public SegPermisoPerfil update(SegPermisoPerfil entity) {
        EntityManagerHelper.log("updating SegPermisoPerfil instance",
                Level.INFO, null);
        try {
            SegPermisoPerfil result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SegPermisoPerfil findById(SegPermisoPerfilId id) {
        EntityManagerHelper.log("finding SegPermisoPerfil instance with id: "
                + id, Level.INFO, null);
        try {
            SegPermisoPerfil instance = getEntityManager().find(
                    SegPermisoPerfil.class, id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

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
     *            number of results to return.
     * @return List<SegPermisoPerfil> found by query
     */
    @SuppressWarnings("unchecked")
    public List<SegPermisoPerfil> findByProperty(String propertyName,
            final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log(
                "finding SegPermisoPerfil instance with property: "
                        + propertyName + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from SegPermisoPerfil model where model."
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
     * Find all SegPermisoPerfil entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SegPermisoPerfil> all SegPermisoPerfil entities
     */
    @SuppressWarnings("unchecked")
    public List<SegPermisoPerfil> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SegPermisoPerfil instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from SegPermisoPerfil model";
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
        EntityManagerHelper.log("deleting SegPermisoPerfil instance", Level.INFO, null);
        try { 
            EntityManagerHelper.beginTransaction();
            final String queryString = "delete from SegPermisoPerfil model where model.segPerfil.id="+value;
            getEntityManager().createQuery(queryString).executeUpdate();
             EntityManagerHelper.commit();
            EntityManagerHelper.getEntityManager().close();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

}