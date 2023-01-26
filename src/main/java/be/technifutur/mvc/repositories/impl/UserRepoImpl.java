package be.technifutur.mvc.repositories.impl;

import be.technifutur.mvc.models.entities.User;
import be.technifutur.mvc.repositories.UserRepository;
import be.technifutur.mvc.utils.EMFSharer;

public class UserRepoImpl extends AbstractCrudRepoImpl<User, Long> implements UserRepository {
    public UserRepoImpl() {
        super(User.class, EMFSharer.getInstance().createEntityManager());
    }
}
