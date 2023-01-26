package be.technifutur.mvc.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<TENTITY, TID> {
    Optional<TENTITY> getById(TID id);
    List<TENTITY> getAll();
    void save(TENTITY entity);
    boolean existsById(TID id);
    void delete(TENTITY entity);
    void deleteById(TID toDelete);
}
