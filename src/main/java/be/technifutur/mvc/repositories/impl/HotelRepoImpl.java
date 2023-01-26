package be.technifutur.mvc.repositories.impl;

import be.technifutur.mvc.models.entities.Hotel;
import be.technifutur.mvc.repositories.HotelRepository;
import be.technifutur.mvc.utils.EMFSharer;
import jakarta.persistence.EntityManager;

public class HotelRepoImpl extends AbstractCrudRepoImpl<Hotel, Long> implements HotelRepository {
    public HotelRepoImpl() {
        super(Hotel.class, EMFSharer.getInstance().createEntityManager());
    }
}
