package be.technifutur.mvc.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

@Component
public final class EMFSharer {
    private static EMFSharer instance;
    public static EMFSharer getInstance() {
        return instance == null ? instance = new EMFSharer() : instance;
    }
//--------------------------------------------------//
    public EMFSharer() {
        this.emf = Persistence.createEntityManagerFactory("hotel_db");
    }
//--------------------------------------------------//
    private final EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
        return emf;
    }
    public EntityManager createEntityManager(){
        return emf.createEntityManager();
    }

    public void close(){
        emf.close();
    }
}