/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navalBattle.datos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import navalBattle.datos.exceptions.NonexistentEntityException;
import navalBattle.datos.exceptions.PreexistingEntityException;

/**
 *
 * @author Maribel Tello Rodriguez
 * @author José Alí Valdivia Ruiz
 */
public class CuentaJpaController implements Serializable {
   private final EntityManagerFactory emf;
   
   public CuentaJpaController(EntityManagerFactory emf) {
      this.emf = Persistence.createEntityManagerFactory("Naval-BattlePU", null);
   }
   
   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(Cuenta cuenta) throws PreexistingEntityException  {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         em.persist(cuenta);
         em.getTransaction().commit();
      } catch (Exception ex) {
         if (findCuenta(cuenta.getNombreUsuario()) != null) {
            throw new PreexistingEntityException("Cuenta " + cuenta + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }
  
   public void edit(Cuenta cuenta) throws NonexistentEntityException {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         cuenta = em.merge(cuenta);
         em.getTransaction().commit();
      } catch (Exception ex) {
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            String id = cuenta.getNombreUsuario();
            if (findCuenta(id) == null) {
               throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.");
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
         em.getTransaction().begin();
         Cuenta cuenta;
         try {
            cuenta = em.getReference(Cuenta.class, id);
            cuenta.getNombreUsuario();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.", enfe);
         }
         em.remove(cuenta);
         em.getTransaction().commit();
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public List<Cuenta> findCuentaEntities() {
      return findCuentaEntities(true, -1, -1);
   }

   public List<Cuenta> findCuentaEntities(int maxResults, int firstResult) {
      return findCuentaEntities(false, maxResults, firstResult);
   }

   private List<Cuenta> findCuentaEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(Cuenta.class));
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

   public Cuenta findCuenta(String id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(Cuenta.class, id);
      } finally {
         em.close();
      }
   }

   public int getCuentaCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<Cuenta> rt = cq.from(Cuenta.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}
