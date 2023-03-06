
package oop.persistance.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import oop.persistance.DurableProducts;
import oop.persistance.StateSalesTax;
import oop.persistance.exceptions.NonexistentEntityException;
import oop.persistance.exceptions.PreexistingEntityException;

/**
 * @author G
 */
public class DurableProductsJpaController implements Serializable {

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
                throw new PreexistingEntityException("DurableProducts " + durableProducts + " already exists.",
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
                    throw new NonexistentEntityException("The durableProducts with id " + id + " no longer exists.");
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
                throw new NonexistentEntityException("The durableProducts with id " + id + " no longer exists.",
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
            CriteriaQuery cq = em.getCriteriaBuilder(). createQuery();
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
            CriteriaQuery cq = em.getCriteriaBuilder(). createQuery();
            Root<DurableProducts> rt = cq.from(DurableProducts.class);
            cq.select(em.getCriteriaBuilder().
                    count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
