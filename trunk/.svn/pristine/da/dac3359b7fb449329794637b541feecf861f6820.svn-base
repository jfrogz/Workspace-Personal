package mx.vw.swf.sms.dao;

import java.util.List;
import mx.vw.swf.sms.model.SmsMovimiento;

/**
 * Interface for SmsMovimientoDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISmsMovimientoDAO {
    /**
     * Perform an initial save of a previously unsaved SmsMovimiento entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISmsMovimientoDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SmsMovimiento entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(SmsMovimiento entity);

    /**
     * Delete a persistent SmsMovimiento entity. This operation must be
     * performed within the a database transaction context for the entity's data
     * to be permanently deleted from the persistence store, i.e., database.
     * This method uses the
     * {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ISmsMovimientoDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            SmsMovimiento entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(SmsMovimiento entity);

    /**
     * Persist a previously saved SmsMovimiento entity and return it or a copy
     * of it to the sender. A copy of the SmsMovimiento entity parameter is
     * returned when the JPA persistence mechanism has not previously been
     * tracking the updated entity. This operation must be performed within the
     * a database transaction context for the entity's data to be permanently
     * saved to the persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = ISmsMovimientoDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SmsMovimiento entity to update
     * @return SmsMovimiento the persisted SmsMovimiento entity instance, may
     *         not be the same
     * @throws RuntimeException
     *             if the operation fails
     */
    public SmsMovimiento update(SmsMovimiento entity);

    public SmsMovimiento findById(Long id);

    /**
     * Find all SmsMovimiento entities with a specific property value.
     * 
     * @param propertyName
     *            the name of the SmsMovimiento property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SmsMovimiento> found by query
     */
    public List<SmsMovimiento> findByProperty(String propertyName,
            Object value, int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByEsMovCliente(Object esMovCliente,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByIdProducto(Object idProducto,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByIdCteProvSap(Object idCteProvSap,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByIdProductoSap(Object idProductoSap,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByPlacaVehiculo(Object placaVehiculo,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByDestino(Object destino,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByFactura(Object factura,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByPesoEntrada(Object pesoEntrada,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByPesoSalida(Object pesoSalida,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByBasculaContingencia(
            Object basculaContingencia, int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByGuardiaContingencia(
            Object guardiaContingencia, int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByPesoNeto(Object pesoNeto,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByPesoTara(Object pesoTara,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByEnviarSap(Object enviarSap,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByEnviadoSap(Object enviadoSap,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByCancelado(Object cancelado,
            int... rowStartIdxAndCount);

    public List<SmsMovimiento> findByMotivoCancela(Object motivoCancela,
            int... rowStartIdxAndCount);

    /**
     * Find all SmsMovimiento entities.
     * 
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<SmsMovimiento> all SmsMovimiento entities
     */
    public List<SmsMovimiento> findAll(int... rowStartIdxAndCount);
}