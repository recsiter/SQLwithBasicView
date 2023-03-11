package oop.persistance.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.DurableProducts;
import entities.PerishableProducts;
import entities.SelectByCriticalQuantity;
import entities.StateSalesTax;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import oop.persistance.exceptions.NonexistentEntityException;
import oop.persistance.exceptions.PreexistingEntityException;

/**
 * @author G
 */
class DurableProductsJpaController implements Serializable, CreateAble<DurableProducts>, Controller {

    public DurableProductsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DurableProducts durableProducts) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().
                    begin();
            StateSalesTax taxId = durableProducts.getTaxId();
            if (taxId != null) {
                taxId = em.getReference(taxId.getClass(), taxId.getTaxKey());
                durableProducts.setTaxId(taxId);
            }
            em.persist(durableProducts);
            if (taxId != null) {
                taxId.getDurableProductsCollection().
                        add(durableProducts);
                taxId = em.merge(taxId);
            }
            em.getTransaction().
                    commit();
        } catch (Exception ex) {
            if (findDurableProducts(durableProducts.getArticleNumber()) != null) {
                throw new PreexistingEntityException(
                        "DurableProducts " + durableProducts + " already exists.",
                        ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DurableProducts durableProducts) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().
                    begin();
            DurableProducts persistentDurableProducts
                    = em.find(DurableProducts.class,
                            durableProducts.getArticleNumber());
            StateSalesTax taxIdOld = persistentDurableProducts.getTaxId();
            StateSalesTax taxIdNew = durableProducts.getTaxId();
            if (taxIdNew != null) {
                taxIdNew
                        = em.getReference(taxIdNew.getClass(),
                                taxIdNew.getTaxKey());
                durableProducts.setTaxId(taxIdNew);
            }
            durableProducts = em.merge(durableProducts);
            if (taxIdOld != null && !taxIdOld.equals(taxIdNew)) {
                taxIdOld.getDurableProductsCollection().
                        remove(durableProducts);
                taxIdOld = em.merge(taxIdOld);
            }
            if (taxIdNew != null && !taxIdNew.equals(taxIdOld)) {
                taxIdNew.getDurableProductsCollection().
                        add(durableProducts);
                taxIdNew = em.merge(taxIdNew);
            }
            em.getTransaction().
                    commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = durableProducts.getArticleNumber();
                if (findDurableProducts(id) == null) {
                    throw new NonexistentEntityException(
                            "The durableProducts with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().
                    begin();
            DurableProducts durableProducts;
            try {
                durableProducts = em.getReference(DurableProducts.class, id);
                durableProducts.getArticleNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "The durableProducts with id " + id + " no longer exists.",
                        enfe);
            }
            StateSalesTax taxId = durableProducts.getTaxId();
            if (taxId != null) {
                taxId.getDurableProductsCollection().
                        remove(durableProducts);
                taxId = em.merge(taxId);
            }
            em.remove(durableProducts);
            em.getTransaction().
                    commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DurableProducts> findDurableProductsEntities() {
        return findDurableProductsEntities(true, -1, -1);
    }

    public List<DurableProducts> findDurableProductsEntities(int maxResults,
            int firstResult) {
        return findDurableProductsEntities(false, maxResults, firstResult);
    }

    private List<DurableProducts> findDurableProductsEntities(boolean all,
            int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().
                    createQuery();
            cq.select(cq.from(DurableProducts.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DurableProducts findDurableProducts(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DurableProducts.class, id);
        } finally {
            em.close();
        }
    }

    public int getDurableProductsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().
                    createQuery();
            Root<DurableProducts> rt = cq.from(DurableProducts.class);
            cq.select(em.getCriteriaBuilder().
                    count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<SelectByCriticalQuantity> selectByCriticalQuantity() {
        Query query = getEntityManager().
                createNamedQuery("DurableProducts.selectByCriticalQuantity");
        List<SelectByCriticalQuantity> result
                = (List<SelectByCriticalQuantity>) query.
                        getResultList();
        return result;
    }

    @Override
    public void createAndMakeFK(DurableProducts product) {
        StateSalesTaxJpaController sstc
                = new StateSalesTaxJpaController(emf);
        int tax = product.getTaxId().
                getTaxKey();
//       Controller sstc= (StateSalesTaxJpaController)ControllerFactory.createController(ControllerName.StateSales);
        if (sstc.findStateSalesTax(tax) != null) {
            try {
                product.setTaxId(sstc.findStateSalesTax(tax));
                create(product);
            } catch (Exception ex) {
                Logger.
                        getLogger(DurableProductsJpaController.class.
                                getName()).
                        log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                sstc.create(
                        new StateSalesTax(tax, String.valueOf(tax) + "%",
                                (tax / 100.0) + 1));
                product.setTaxId(sstc.findStateSalesTax(tax));
                create(product);
            } catch (Exception ex) {
                Logger.
                        getLogger(DurableProductsJpaController.class.
                                getName()).
                        log(Level.SEVERE, null, ex);
            }

        }

    }
// próbálkozás a tárolt eljárás meghívásához:
//    public int createAndMakeFKByStroderPr(DurableProducts product
//    ) {
//        EntityManager em = getEntityManager();
//        StoredProcedureQuery query = em.createNamedStoredProcedureQuery(
//                "insertProduct");
//
//        query.setParameter("article_number", product.getArticleNumber());
//        query.setParameter("prod_name", product.getName());
//        query.setParameter("brand", product.getBrand());
//        query.setParameter("family", product.getFamily());
//        query.setParameter("netto_price", product.getNettoPrice());
//        query.setParameter("tax_id", product.getTaxId().
//                getTaxKey());
//        query.setParameter("quantity", product.getQuantity());
//        query.setParameter("amount_units", product.getAmountUnits());
//        query.setParameter("critical_quantity", product.getCriticalQuantity());
//        query.setParameter("waranty_period", product.getWarantyPeriod());
//        query.setParameter("gross_weight", product.getGrossWeight());
//
//        query.execute();
//
//        int success = (int) query.getOutputParameterValue("success");
//        if (success != 1) {
//            throw new IllegalArgumentException("Insert to mysql was unsuccess.");
//        }
//        return success;
//    }

    public List<DurableProducts> searchByIdPart(String idPart) {
        Query query = getEntityManager().
                createNamedQuery("DurableProducts.searchByIdPart");
        query.setParameter("wordPiece", idPart);
        return query.getResultList();
    }

    public List<DurableProducts> orderByQuery(String columnName) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DurableProducts> cq = cb.createQuery(
                DurableProducts.class);
        Root<DurableProducts> root = cq.from(DurableProducts.class);
        cq.orderBy(cb.asc(root.get(columnName)));
        List<DurableProducts> list;
        return list = em.createQuery(cq).
                getResultList();
    }

}
