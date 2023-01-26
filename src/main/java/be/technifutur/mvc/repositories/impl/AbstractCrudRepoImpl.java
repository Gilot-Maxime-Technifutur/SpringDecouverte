package be.technifutur.mvc.repositories.impl;

import be.technifutur.mvc.repositories.CrudRepository;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class AbstractCrudRepoImpl<TENTITY, TID> implements CrudRepository<TENTITY, TID> {
    private final Class<TENTITY> entityClass;
    protected final EntityManager em;

    protected AbstractCrudRepoImpl(Class<TENTITY> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    @Override
    public List<TENTITY> getAll() {
        String entityName = entityClass.getSimpleName();
        String qlQuery = String.format("SELECT e FROM %s e", entityName);
        TypedQuery<TENTITY> query = em.createQuery(qlQuery, entityClass);
        List<TENTITY> list = query.getResultList();
        em.clear();
        return list;
    }

    @Override
    public Optional<TENTITY> getById(TID id) {
        TENTITY tentity = em.find(entityClass, id);
        em.clear();
        return Optional.ofNullable(tentity);
    }

    @Override
    public void save(TENTITY entity) {

        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();

    }

    @Override
    public boolean existsById(TID id) {
        return em.find(entityClass, id) != null;
    }

    @Override
    public void delete(TENTITY entity) {
        em.getTransaction().begin();
        TENTITY managedEntity = em.merge(entity);
        em.remove(managedEntity);
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(TID id) {

//        Field[] fields = entityClass.getDeclaredFields();
//        String idName = null;
//
//        for(Field field : fields) {
//            if(field.isAnnotationPresent(Id.class) ){
//                idName = field.getName();
//                break;
//            }
////                    Column column = field.getAnnotation(Column.class);
////                    if(column != null) {
////                        System.out.println(column.name());
////                    }
//        }
//        if(idName == null){
//            throw new RuntimeException("entityClass is not an Entity class");
//        }
//
//        String qlQuery = String.format("DELETE FROM %s WHERE %s = ?1",entityClass.getSimpleName(),idName);
//        Query query = em.createQuery(qlQuery);
//        query.setParameter(1,id);
//        em.getTransaction().begin();
//        query.executeUpdate();
//        em.getTransaction().commit();

        em.getTransaction().begin();
        TENTITY entity = em.find(entityClass, id);
        em.remove(entity);
        em.getTransaction().commit();
    }
}