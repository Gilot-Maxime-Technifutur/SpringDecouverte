package be.technifutur.mvc.repositories.impl;

import be.technifutur.mvc.repositories.CrudRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class AbstractCrudRepoImpl<TENTITY, TID> implements CrudRepository<TENTITY, TID> {
    protected final EntityManager em;
    private final Class<TENTITY> entityClass;

    public AbstractCrudRepoImpl(Class<TENTITY> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    @Override
    public Optional<TENTITY> getOne(TID id) {
        TENTITY p = em.find(entityClass, id);

        em.clear();
        return Optional.ofNullable(p);
    }

    @Override
    public List<TENTITY> getAll() {
        List<TENTITY> list = em.createQuery(String.format("SELECT e FROM %s e", entityClass.getSimpleName()), entityClass).getResultList();

        em.clear();
        return list;
    }

    @Override
    public void add(TENTITY toInsert) {
        em.getTransaction().begin();
        em.persist(toInsert);
        em.getTransaction().commit();
    }

    @Override
    public void update(TENTITY updated, TID id) {
        if(getOne(id).isPresent()) {
            em.getTransaction().begin();
            em.merge(updated);
            em.getTransaction().commit();
        }else
            throw new IllegalArgumentException("element does not exist");
    }

    @Override
    public void remove(TID id) {
        em.getTransaction().begin();
        TENTITY p = em.find(entityClass, id);
        if(p != null)
            em.remove(p);
        em.getTransaction().commit();
    }
}