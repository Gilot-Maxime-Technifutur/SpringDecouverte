package be.technifutur.mvc.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

//--------------------------------------------------//
    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    private Set<WorkDetail> contracts = new LinkedHashSet<>();
    @OneToOne(mappedBy = "receptionist", orphanRemoval = true)
    private Hotel receptionnistAt;
}
