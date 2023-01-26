package be.technifutur.mvc.repositories.impl;

import be.technifutur.mvc.models.entities.Room;
import be.technifutur.mvc.repositories.RoomRepository;
import be.technifutur.mvc.utils.EMFSharer;
import jakarta.persistence.EntityManager;

public class RoomRepoImpl extends AbstractCrudRepoImpl<Room, Long> implements RoomRepository {
    public RoomRepoImpl() {
        super(Room.class, EMFSharer.getInstance().createEntityManager());
    }
}
