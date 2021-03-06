package mx.vw.swf.fwk.dao;

import mx.vw.swf.fwk.model.FwkCorreoTemplate;
import java.util.List;

/**
 * Interface for FwkCorreoTemplateDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFwkCorreoTemplateDAO {
    /**
     * Perform an initial save of a previously unsaved FwkCorreoTemplate entity.
     * All subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * IFwkCorreoTemplateDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            FwkCorreoTemplate entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(FwkCorreoTemplate entity);

    /**
     * Delete a persistent FwkCorreoTemplate entity. This operation must be
     * performed within the a database transaction context for the entity's data
     * to be permanently deleted from the persistence store, i.e., database.
     * This method uses the
     * {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * IFwkCorreoTemplateDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            FwkCorreoTemplate entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(FwkCorreoTemplate entity);

    /**
     * Persist a previously saved FwkCorreoTemplate entity and return it or a
     * copy of it to the sender. A copy of the FwkCorreoTemplate entity
     * parameter is returned when the JPA persistence mechanism has not
     * previously been tracking the updated entity. This operation must be
     * performed within the a database transaction context for the entity's data
     * to be permanently saved to the persistence store, i.e., database. This
     * method uses the {@link javax.persistence.EntityManager#merge(Object)
     * EntityManager#merge} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = IFwkCorreoTemplateDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            FwkCorreoTemplate entity to update
     * @return FwkCorreoTemplate the persisted FwkCorreoTemplate entity
     *         instance, may not be the same
     * @throws RuntimeException
     *             if the operation fails
     */
    public FwkCorreoTemplate update(FwkCorreoTemplate entity);

    public FwkCorreoTemplate findById(String id);

    /**
     * Find all FwkCorreoTemplate entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the FwkCorreoTemplate property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<FwkCorreoTemplate> found by query
     */
    public List<FwkCorreoTemplate> findByProperty(String propertyName,
            Object value, int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByMailto(Object mailto,
            int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByMailcc(Object mailcc,
            int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByMailbcc(Object mailbcc,
            int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByMailsubject(Object mailsubject,
            int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByMailbody(Object mailbody,
            int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByMailattach(Object mailattach,
            int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByMailimages(Object mailimages,
            int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByUsuariocreacion(
            Object usuariocreacion, int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByUsuarioactualiza(
            Object usuarioactualiza, int... rowStartIdxAndCount);

    public List<FwkCorreoTemplate> findByEstatus(Object estatus,
            int... rowStartIdxAndCount);

    /**
     * Find all FwkCorreoTemplate entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<FwkCorreoTemplate> all FwkCorreoTemplate entities
     */
    public List<FwkCorreoTemplate> findAll(int... rowStartIdxAndCount);
}