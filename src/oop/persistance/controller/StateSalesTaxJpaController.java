package oop.persistance.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import entities.DurableProducts;
import entities.PerishableProducts;
import entities.StateSalesTax;
import oop.persistance.exceptions.IllegalOrphanException;
import oop.persistance.exceptions.NonexistentEntityException;
import oop.persistance.exceptions.PreexistingEntityException;

/**
 * @author G
 */
public class StateSalesTaxJpaController implements Serializable, Controller {

    public StateSalesTaxJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StateSalesTax stateSalesTax) throws PreexistingEntityException, Exception {
        if (stateSalesTax.getPerishableProductsCollection() == null) {
            stateSalesTax.setPerishableProductsCollection(
                    new ArrayList<PerishableProducts>());
        }
        if (stateSalesTax.getDurableProductsCollection() == null) {
            stateSalesTax.setDurableProductsCollection(
                    new ArrayList<DurableProducts>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().
                    begin();
            Collection<PerishableProducts> attachedPerishableProductsCollection
                    = new ArrayList<PerishableProducts>();
            for (PerishableProducts perishableProductsCollectionPerishableProductsToAttach : stateSalesTax.
                    getPerishableProductsCollection()) {
                perishableProductsCollectionPerishableProductsToAttach
                        = em.getReference(
                                perishableProductsCollectionPerishableProductsToAttach.
                                        getClass(),
                                perishableProductsCollectionPerishableProductsToAttach.
                                        getArticleNumber());
                attachedPerishableProductsCollection.add(
                        perishableProductsCollectionPerishableProductsToAttach);
            }
            stateSalesTax.setPerishableProductsCollection(
                    attachedPerishableProductsCollection);
            Collection<DurableProducts> attachedDurableProductsCollection
                    = new ArrayList<DurableProducts>();
            for (DurableProducts durableProductsCollectionDurableProductsToAttach : stateSalesTax.
                    getDurableProductsCollection()) {
                durableProductsCollectionDurableProductsToAttach
                        = em.getReference(
                                durableProductsCollectionDurableProductsToAttach.
                                        getClass(),
                                durableProductsCollectionDurableProductsToAttach.
                                        getArticleNumber());
                attachedDurableProductsCollection.add(
                        durableProductsCollectionDurableProductsToAttach);
            }
            stateSalesTax.setDurableProductsCollection(
                    attachedDurableProductsCollection);
            em.persist(stateSalesTax);
            for (PerishableProducts perishableProductsCollectionPerishableProducts : stateSalesTax.
                    getPerishableProductsCollection()) {
                StateSalesTax oldTaxIdOfPerishableProductsCollectionPerishableProducts
                        = perishableProductsCollectionPerishableProducts.
                                getTaxId();
                perishableProductsCollectionPerishableProducts.setTaxId(
                        stateSalesTax);
                perishableProductsCollectionPerishableProducts
                        = em.merge(
                                perishableProductsCollectionPerishableProducts);
                if (oldTaxIdOfPerishableProductsCollectionPerishableProducts != null) {
                    oldTaxIdOfPerishableProductsCollectionPerishableProducts.
                            getPerishableProductsCollection().
                            remove(perishableProductsCollectionPerishableProducts);
                    oldTaxIdOfPerishableProductsCollectionPerishableProducts
                            = em.merge(
                                    oldTaxIdOfPerishableProductsCollectionPerishableProducts);
                }
            }
            for (DurableProducts durableProductsCollectionDurableProducts : stateSalesTax.
                    getDurableProductsCollection()) {
                StateSalesTax oldTaxIdOfDurableProductsCollectionDurableProducts
                        = durableProductsCollectionDurableProducts.getTaxId();
                durableProductsCollectionDurableProducts.setTaxId(stateSalesTax);
                durableProductsCollectionDurableProducts
                        = em.merge(durableProductsCollectionDurableProducts);
                if (oldTaxIdOfDurableProductsCollectionDurableProducts != null) {
                    oldTaxIdOfDurableProductsCollectionDurableProducts.
                            getDurableProductsCollection().
                            remove(durableProductsCollectionDurableProducts);
                    oldTaxIdOfDurableProductsCollectionDurableProducts
                            = em.merge(
                                    oldTaxIdOfDurableProductsCollectionDurableProducts);
                }
            }
            em.getTransaction().
                    commit();
        } catch (Exception ex) {
            if (findStateSalesTax(stateSalesTax.getTaxKey()) != null) {
                throw new PreexistingEntityException(
                        "StateSalesTax " + stateSalesTax + " already exists.",
                        ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StateSalesTax stateSalesTax) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().
                    begin();
            StateSalesTax persistentStateSalesTax
                    = em.find(StateSalesTax.class, stateSalesTax.getTaxKey());
            Collection<PerishableProducts> perishableProductsCollectionOld
                    = persistentStateSalesTax.getPerishableProductsCollection();
            Collection<PerishableProducts> perishableProductsCollectionNew
                    = stateSalesTax.getPerishableProductsCollection();
            Collection<DurableProducts> durableProductsCollectionOld
                    = persistentStateSalesTax.getDurableProductsCollection();
            Collection<DurableProducts> durableProductsCollectionNew
                    = stateSalesTax.getDurableProductsCollection();
            List<String> illegalOrphanMessages = null;
            for (PerishableProducts perishableProductsCollectionOldPerishableProducts : perishableProductsCollectionOld) {
                if (!perishableProductsCollectionNew.contains(
                        perishableProductsCollectionOldPerishableProducts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add(
                            "You must retain PerishableProducts " + perishableProductsCollectionOldPerishableProducts + " since its taxId field is not nullable.");
                }
            }
            for (DurableProducts durableProductsCollectionOldDurableProducts : durableProductsCollectionOld) {
                if (!durableProductsCollectionNew.contains(
                        durableProductsCollectionOldDurableProducts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add(
                            "You must retain DurableProducts " + durableProductsCollectionOldDurableProducts + " since its taxId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<PerishableProducts> attachedPerishableProductsCollectionNew
                    = new ArrayList<PerishableProducts>();
            for (PerishableProducts perishableProductsCollectionNewPerishableProductsToAttach : perishableProductsCollectionNew) {
                perishableProductsCollectionNewPerishableProductsToAttach
                        = em.getReference(
                                perishableProductsCollectionNewPerishableProductsToAttach.
                                        getClass(),
                                perishableProductsCollectionNewPerishableProductsToAttach.
                                        getArticleNumber());
                attachedPerishableProductsCollectionNew.add(
                        perishableProductsCollectionNewPerishableProductsToAttach);
            }
            perishableProductsCollectionNew
                    = attachedPerishableProductsCollectionNew;
            stateSalesTax.setPerishableProductsCollection(
                    perishableProductsCollectionNew);
            Collection<DurableProducts> attachedDurableProductsCollectionNew
                    = new ArrayList<DurableProducts>();
            for (DurableProducts durableProductsCollectionNewDurableProductsToAttach : durableProductsCollectionNew) {
                durableProductsCollectionNewDurableProductsToAttach
                        = em.getReference(
                                durableProductsCollectionNewDurableProductsToAttach.
                                        getClass(),
                                durableProductsCollectionNewDurableProductsToAttach.
                                        getArticleNumber());
                attachedDurableProductsCollectionNew.add(
                        durableProductsCollectionNewDurableProductsToAttach);
            }
            durableProductsCollectionNew = attachedDurableProductsCollectionNew;
            stateSalesTax.setDurableProductsCollection(
                    durableProductsCollectionNew);
            stateSalesTax = em.merge(stateSalesTax);
            for (PerishableProducts perishableProductsCollectionNewPerishableProducts : perishableProductsCollectionNew) {
                if (!perishableProductsCollectionOld.contains(
                        perishableProductsCollectionNewPerishableProducts)) {
                    StateSalesTax oldTaxIdOfPerishableProductsCollectionNewPerishableProducts
                            = perishableProductsCollectionNewPerishableProducts.
                                    getTaxId();
                    perishableProductsCollectionNewPerishableProducts.setTaxId(
                            stateSalesTax);
                    perishableProductsCollectionNewPerishableProducts
                            = em.merge(
                                    perishableProductsCollectionNewPerishableProducts);
                    if (oldTaxIdOfPerishableProductsCollectionNewPerishableProducts != null && !oldTaxIdOfPerishableProductsCollectionNewPerishableProducts.
                            equals(stateSalesTax)) {
                        oldTaxIdOfPerishableProductsCollectionNewPerishableProducts.
                                getPerishableProductsCollection().
                                remove(perishableProductsCollectionNewPerishableProducts);
                        oldTaxIdOfPerishableProductsCollectionNewPerishableProducts
                                = em.merge(
                                        oldTaxIdOfPerishableProductsCollectionNewPerishableProducts);
                    }
                }
            }
            for (DurableProducts durableProductsCollectionNewDurableProducts : durableProductsCollectionNew) {
                if (!durableProductsCollectionOld.contains(
                        durableProductsCollectionNewDurableProducts)) {
                    StateSalesTax oldTaxIdOfDurableProductsCollectionNewDurableProducts
                            = durableProductsCollectionNewDurableProducts.
                                    getTaxId();
                    durableProductsCollectionNewDurableProducts.setTaxId(
                            stateSalesTax);
                    durableProductsCollectionNewDurableProducts
                            = em.merge(
                                    durableProductsCollectionNewDurableProducts);
                    if (oldTaxIdOfDurableProductsCollectionNewDurableProducts != null && !oldTaxIdOfDurableProductsCollectionNewDurableProducts.
                            equals(stateSalesTax)) {
                        oldTaxIdOfDurableProductsCollectionNewDurableProducts.
                                getDurableProductsCollection().
                                remove(durableProductsCollectionNewDurableProducts);
                        oldTaxIdOfDurableProductsCollectionNewDurableProducts
                                = em.merge(
                                        oldTaxIdOfDurableProductsCollectionNewDurableProducts);
                    }
                }
            }
            em.getTransaction().
                    commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = stateSalesTax.getTaxKey();
                if (findStateSalesTax(id) == null) {
                    throw new NonexistentEntityException(
                            "The stateSalesTax with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().
                    begin();
            StateSalesTax stateSalesTax;
            try {
                stateSalesTax = em.getReference(StateSalesTax.class, id);
                stateSalesTax.getTaxKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "The stateSalesTax with id " + id + " no longer exists.",
                        enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PerishableProducts> perishableProductsCollectionOrphanCheck
                    = stateSalesTax.getPerishableProductsCollection();
            for (PerishableProducts perishableProductsCollectionOrphanCheckPerishableProducts : perishableProductsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add(
                        "This StateSalesTax (" + stateSalesTax + ") cannot be destroyed since the PerishableProducts " + perishableProductsCollectionOrphanCheckPerishableProducts + " in its perishableProductsCollection field has a non-nullable taxId field.");
            }
            Collection<DurableProducts> durableProductsCollectionOrphanCheck
                    = stateSalesTax.getDurableProductsCollection();
            for (DurableProducts durableProductsCollectionOrphanCheckDurableProducts : durableProductsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add(
                        "This StateSalesTax (" + stateSalesTax + ") cannot be destroyed since the DurableProducts " + durableProductsCollectionOrphanCheckDurableProducts + " in its durableProductsCollection field has a non-nullable taxId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(stateSalesTax);
            em.getTransaction().
                    commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StateSalesTax> findStateSalesTaxEntities() {
        return findStateSalesTaxEntities(true, -1, -1);
    }

    public List<StateSalesTax> findStateSalesTaxEntities(int maxResults,
            int firstResult) {
        return findStateSalesTaxEntities(false, maxResults, firstResult);
    }

    private List<StateSalesTax> findStateSalesTaxEntities(boolean all,
            int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().
                    createQuery();
            cq.select(cq.from(StateSalesTax.class));
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

    public StateSalesTax findStateSalesTax(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StateSalesTax.class, id);
        } finally {
            em.close();
        }
    }

    public int getStateSalesTaxCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().
                    createQuery();
            Root<StateSalesTax> rt = cq.from(StateSalesTax.class);
            cq.select(em.getCriteriaBuilder().
                    count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
