package oop.persistance.controller;

import entities.GroupByTaxId;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.PerishableProducts;
import entities.SelectByCriticalQuantity;
import entities.StateSalesTax;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import oop.persistance.exceptions.NonexistentEntityException;
import oop.persistance.exceptions.PreexistingEntityException;

/**
 * @author G
 */
class PerishableProductsJpaController implements Serializable, CreateAble<PerishableProducts>, Controller {

    public PerishableProductsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PerishableProducts perishableProducts) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().
                    begin();
            StateSalesTax taxId = perishableProducts.getTaxId();
            if (taxId != null) {
                taxId = em.getReference(taxId.getClass(), taxId.getTaxKey());
                perishableProducts.setTaxId(taxId);
            }
            em.persist(perishableProducts);
            if (taxId != null) {
                taxId.getPerishableProductsCollection().
                        add(perishableProducts);
                taxId = em.merge(taxId);
            }
            em.getTransaction().
                    commit();
        } catch (Exception ex) {
            if (findPerishableProducts(perishableProducts.getArticleNumber()) != null) {
                throw new PreexistingEntityException(
                        "PerishableProducts " + perishableProducts + " already exists.",
                        ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PerishableProducts perishableProducts) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().
                    begin();
            PerishableProducts persistentPerishableProducts
                    = em.find(PerishableProducts.class,
                            perishableProducts.getArticleNumber());
            StateSalesTax taxIdOld = persistentPerishableProducts.getTaxId();
            StateSalesTax taxIdNew = perishableProducts.getTaxId();
            if (taxIdNew != null) {
                taxIdNew
                        = em.getReference(taxIdNew.getClass(),
                                taxIdNew.getTaxKey());
                perishableProducts.setTaxId(taxIdNew);
            }
            perishableProducts = em.merge(perishableProducts);
            if (taxIdOld != null && !taxIdOld.equals(taxIdNew)) {
                taxIdOld.getPerishableProductsCollection().
                        remove(perishableProducts);
                taxIdOld = em.merge(taxIdOld);
            }
            if (taxIdNew != null && !taxIdNew.equals(taxIdOld)) {
                taxIdNew.getPerishableProductsCollection().
                        add(perishableProducts);
                taxIdNew = em.merge(taxIdNew);
            }
            em.getTransaction().
                    commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = perishableProducts.getArticleNumber();
                if (findPerishableProducts(id) == null) {
                    throw new NonexistentEntityException(
                            "The perishableProducts with id " + id + " no longer exists.");
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
            PerishableProducts perishableProducts;
            try {
                perishableProducts
                        = em.getReference(PerishableProducts.class, id);
                perishableProducts.getArticleNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "The perishableProducts with id " + id + " no longer exists.",
                        enfe);
            }
            StateSalesTax taxId = perishableProducts.getTaxId();
            if (taxId != null) {
                taxId.getPerishableProductsCollection().
                        remove(perishableProducts);
                taxId = em.merge(taxId);
            }
            em.remove(perishableProducts);
            em.getTransaction().
                    commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PerishableProducts> findPerishableProductsEntities() {
        return findPerishableProductsEntities(true, -1, -1);
    }

    public List<PerishableProducts> findPerishableProductsEntities(
            int maxResults, int firstResult) {
        return findPerishableProductsEntities(false, maxResults, firstResult);
    }

    private List<PerishableProducts> findPerishableProductsEntities(boolean all,
            int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().
                    createQuery();
            cq.select(cq.from(PerishableProducts.class));
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

    public PerishableProducts findPerishableProducts(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PerishableProducts.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerishableProductsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().
                    createQuery();
            Root<PerishableProducts> rt = cq.from(PerishableProducts.class);
            cq.select(em.getCriteriaBuilder().
                    count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<PerishableProducts> searchByNamePart(String namePart) {
        Query query = getEntityManager().
                createNamedQuery("PerishableProducts.searchByNamePart");
        query.setParameter("namePart", namePart);
        return query.getResultList();
    }

    public List<GroupByTaxId> groupingByTaxId() {
        Query query = getEntityManager().
                createNamedQuery("PerishableProducts.groupingByTaxId");
        List<GroupByTaxId> result = (List<GroupByTaxId>) query.
                getResultList();
        return result;
    }

    public List<SelectByCriticalQuantity> selectByCriticalQuantity() {
        Query query = getEntityManager().
                createNamedQuery("PerishableProducts.selectByCriticalQuantity");
        List<SelectByCriticalQuantity> result
                = (List<SelectByCriticalQuantity>) query.
                        getResultList();
        return result;
    }

    @Override
    public void createAndMakeFK(PerishableProducts product, int tax) {
        StateSalesTaxJpaController sstc = new StateSalesTaxJpaController(emf);
//        Controller sstc = (StateSalesTaxJpaController) ControllerFactory.
//                createController(ControllerName.StateSales);
        if (sstc.findStateSalesTax(tax) != null) {
            try {
                product.setTaxId(sstc.findStateSalesTax(tax));
                create(product);
            } catch (Exception ex) {
                Logger.
                        getLogger(PerishableProductsJpaController.class.
                                getName()).
                        log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                sstc.create(new StateSalesTax(tax, String.valueOf(tax) + "%",
                        (tax / 100.0) + 1));
                product.setTaxId(sstc.findStateSalesTax(tax));
                create(product);
            } catch (Exception ex) {
                Logger.
                        getLogger(PerishableProductsJpaController.class.
                                getName()).
                        log(Level.SEVERE, null, ex);
            }

        }
    }

    public List<PerishableProducts> orderByQuery(String columnName) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PerishableProducts> cq = cb.createQuery(
                PerishableProducts.class);
        Root<PerishableProducts> root = cq.from(PerishableProducts.class);
        cq.orderBy(cb.asc(root.get(columnName)));
        List<PerishableProducts> list;
        return list = em.createQuery(cq).
                getResultList();
    }

}
