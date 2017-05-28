package mx.vw.swf.sms.dao;

import mx.vw.swf.fwk.model.EntityManagerHelper;
import mx.vw.swf.sms.dao.ISmsMovimientoDAO;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.vw.swf.sms.model.SmsMovimiento;

/**
 * A data access object (DAO) providing persistence and search support for
 * SmsMovimiento entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see SmsMovimiento
 * @author MyEclipse Persistence Tools
 */
public class SmsMovimientoDAO implements ISmsMovimientoDAO {
    // property constants
    public static final String ES_MOV_CLIENTE = "esMovCliente";
    public static final String ID_PRODUCTO = "idProducto";
    public static final String ID_CTE_PROV_SAP = "idCteProvSap";
    public static final String ID_PRODUCTO_SAP = "idProductoSap";
    public static final String PLACA_VEHICULO = "placaVehiculo";
    public static final String DESTINO = "destino";
    public static final String FACTURA = "factura";
    public static final String PESO_ENTRADA = "pesoEntrada";
    public static final String PESO_SALIDA = "pesoSalida";
    public static final String BASCULA_CONTINGENCIA = "basculaContingencia";
    public static final String GUARDIA_CONTINGENCIA = "guardiaContingencia";
    public static final String PESO_NETO = "pesoNeto";
    public static final String PESO_TARA = "pesoTara";
    public static final String ENVIAR_SAP = "enviarSap";
    public static final String ENVIADO_SAP = "enviadoSap";
    public static final String CANCELADO = "cancelado";
    public static final String MOTIVO_CANCELA = "motivoCancela";

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

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
     * SmsMovimientoDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     * 
     * @param entity
     *            SmsMovimiento entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(SmsMovimiento entity) {
        EntityManagerHelper.log("saving SmsMovimiento instance", Level.INFO,
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
     * Delete a persistent SmsMovimiento entity. This operation must be
     * performed within the a database transaction context for the entity's data
     * to be permanently deleted from the persistence store, i.e., database.
     * This method uses the
     * {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     * 
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * SmsMovimientoDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     * 
     * @param entity
     *            SmsMovimiento entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(SmsMovimiento entity) {
        EntityManagerHelper.log("deleting SmsMovimiento instance", Level.INFO,
                null);
        try {
            entity = getEntityManager().getReference(SmsMovimiento.class,
                    entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

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
     * entity = SmsMovimientoDAO.update(entity);
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
    public SmsMovimiento update(SmsMovimiento entity) {
        EntityManagerHelper.log("updating SmsMovimiento instance", Level.INFO,
                null);
        try {
            SmsMovimiento result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);
            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public SmsMovimiento findById(Long id) {
        EntityManagerHelper.log(
                "finding SmsMovimiento instance with id: " + id, Level.INFO,
                null);
        try {
            SmsMovimiento instance = getEntityManager().find(
                    SmsMovimiento.class, id);
            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

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
     *            number of results to return.
     * @return List<SmsMovimiento> found by query
     */
    @SuppressWarnings("unchecked")
    public List<SmsMovimiento> findByProperty(String propertyName,
            final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log(
                "finding SmsMovimiento instance with property: " + propertyName
                        + ", value: " + value, Level.INFO, null);
        try {
            final String queryString = "select model from SmsMovimiento model where model."
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

    public List<SmsMovimiento> findByEsMovCliente(Object esMovCliente,
            int... rowStartIdxAndCount) {
        return findByProperty(ES_MOV_CLIENTE, esMovCliente, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByIdProducto(Object idProducto,
            int... rowStartIdxAndCount) {
        return findByProperty(ID_PRODUCTO, idProducto, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByIdCteProvSap(Object idCteProvSap,
            int... rowStartIdxAndCount) {
        return findByProperty(ID_CTE_PROV_SAP, idCteProvSap,
                rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByIdProductoSap(Object idProductoSap,
            int... rowStartIdxAndCount) {
        return findByProperty(ID_PRODUCTO_SAP, idProductoSap,
                rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByPlacaVehiculo(Object placaVehiculo,
            int... rowStartIdxAndCount) {
        return findByProperty(PLACA_VEHICULO, placaVehiculo,
                rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByDestino(Object destino,
            int... rowStartIdxAndCount) {
        return findByProperty(DESTINO, destino, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByFactura(Object factura,
            int... rowStartIdxAndCount) {
        return findByProperty(FACTURA, factura, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByPesoEntrada(Object pesoEntrada,
            int... rowStartIdxAndCount) {
        return findByProperty(PESO_ENTRADA, pesoEntrada, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByPesoSalida(Object pesoSalida,
            int... rowStartIdxAndCount) {
        return findByProperty(PESO_SALIDA, pesoSalida, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByBasculaContingencia(
            Object basculaContingencia, int... rowStartIdxAndCount) {
        return findByProperty(BASCULA_CONTINGENCIA, basculaContingencia,
                rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByGuardiaContingencia(
            Object guardiaContingencia, int... rowStartIdxAndCount) {
        return findByProperty(GUARDIA_CONTINGENCIA, guardiaContingencia,
                rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByPesoNeto(Object pesoNeto,
            int... rowStartIdxAndCount) {
        return findByProperty(PESO_NETO, pesoNeto, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByPesoTara(Object pesoTara,
            int... rowStartIdxAndCount) {
        return findByProperty(PESO_TARA, pesoTara, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByEnviarSap(Object enviarSap,
            int... rowStartIdxAndCount) {
        return findByProperty(ENVIAR_SAP, enviarSap, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByEnviadoSap(Object enviadoSap,
            int... rowStartIdxAndCount) {
        return findByProperty(ENVIADO_SAP, enviadoSap, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByCancelado(Object cancelado,
            int... rowStartIdxAndCount) {
        return findByProperty(CANCELADO, cancelado, rowStartIdxAndCount);
    }

    public List<SmsMovimiento> findByMotivoCancela(Object motivoCancela,
            int... rowStartIdxAndCount) {
        return findByProperty(MOTIVO_CANCELA, motivoCancela,
                rowStartIdxAndCount);
    }

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
    @SuppressWarnings("unchecked")
    public List<SmsMovimiento> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all SmsMovimiento instances",
                Level.INFO, null);
        try {
            final String queryString = "select model from SmsMovimiento model";
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

    @SuppressWarnings("unchecked")
    public List<SmsMovimiento> findAllMovimientosClientes() {
        EntityManagerHelper.log(
                "finding SmsMovimiento instance with property: " 
                        + ", value: ", Level.INFO, null);
        try {
            final String queryString = 
                    "select model from SmsMovimiento model where model.enviarSap=:enviarSapValue and "
                    + " model.enviadoSap=:enviadoSapValue"
                    + " and model.cancelado=:canceladoValue";
            Query query = getEntityManager().createQuery(queryString);
            query.setParameter("enviarSapValue", Short.valueOf("1"));
            query.setParameter("enviadoSapValue", Short.valueOf("0"));
            query.setParameter("canceladoValue", Short.valueOf("0"));
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find by property name failed",
                    Level.SEVERE, re);
            throw re;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<SmsMovimiento> findAllMovimientosProveedores() {
        EntityManagerHelper.log(
                "finding SmsMovimiento instance with property: " 
                        + ", value: ", Level.INFO, null);
        try {
            final String queryString = 
                    "select model from SmsMovimiento model where model.enviarSap=:enviarSapValue and "
                    + " model.fechaEntrada is not null and model.fechaSalida is null"
                    + " and model.cancelado=:canceladoValue";
            Query query = getEntityManager().createQuery(queryString);
            query.setParameter("enviarSapValue", Short.valueOf("0"));
            query.setParameter("canceladoValue", Short.valueOf("0"));
            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find by property name failed",
                    Level.SEVERE, re);
            throw re;
        }
    }
}