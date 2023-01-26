package be.technifutur.mvc.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<TENTITY, TID> {
    Optional<TENTITY> getOne(TID id);
    List<TENTITY> getAll();
    void add(TENTITY toInsert);
    void update(TENTITY updated, TID toUpdate);
    void remove(TID toDelete);
}
