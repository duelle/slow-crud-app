// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.jclarity.had_one_dismissal.domain;

import com.jclarity.had_one_dismissal.domain.JobListing;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect JobListing_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager JobListing.entityManager;
    
    public static final EntityManager JobListing.entityManager() {
        EntityManager em = new JobListing().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long JobListing.countJobListings() {
        return entityManager().createQuery("SELECT COUNT(o) FROM JobListing o", Long.class).getSingleResult();
    }
    
    public static List<JobListing> JobListing.findAllJobListings() {
        return entityManager().createQuery("SELECT o FROM JobListing o", JobListing.class).getResultList();
    }
    
    public static JobListing JobListing.findJobListing(Long id) {
        if (id == null) return null;
        return entityManager().find(JobListing.class, id);
    }
    
    public static List<JobListing> JobListing.findJobListingEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM JobListing o", JobListing.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void JobListing.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void JobListing.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            JobListing attached = JobListing.findJobListing(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void JobListing.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void JobListing.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public JobListing JobListing.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        JobListing merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
