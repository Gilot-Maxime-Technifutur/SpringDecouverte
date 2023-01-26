package be.technifutur.mvc.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "hotels")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long id;

    @Column(name = "rating", scale = 1)
    private Integer nbStar;

    @OneToOne
    @JoinColumn(name = "receptionnist_id", nullable = false, unique = true)
    private Employee receptionist;

    @Column(name = "hotel_name", nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String address;
//--------------------------------------------------//
    @OneToMany(mappedBy = "hotel", orphanRemoval = true)
    private Set<Room> rooms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "hotel", orphanRemoval = true)
    private Set<WorkDetail> workers = new LinkedHashSet<>();
}
