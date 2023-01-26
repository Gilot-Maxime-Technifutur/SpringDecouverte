package be.technifutur.mvc.repositories.impl;

import be.technifutur.mvc.models.entities.Employee;
import be.technifutur.mvc.repositories.EmployeeRepository;
import be.technifutur.mvc.utils.EMFSharer;

public class EmployeeRepoImpl extends AbstractCrudRepoImpl<Employee, Long> implements EmployeeRepository {
    public EmployeeRepoImpl() {
        super(Employee.class, EMFSharer.getInstance().createEntityManager());
    }
}
