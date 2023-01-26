package be.technifutur.mvc.models.entities;

import be.technifutur.mvc.models.entities.enums.RoomType;
import be.technifutur.mvc.models.entities.enums.RoomView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "rooms")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer floor;

    @Column(name = "simple_beds", nullable = false)
    private Integer simpleBeds;

    @Column(name = "double_beds", nullable = false)
    private Integer doubleBeds;

    @Column(nullable = false)
    private Integer size;

    @Column(name = "additional_beds", nullable = false)
    private Integer additionalBeds;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomView view;

    private Boolean available;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;
//--------------------------------------------------//
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private List<Reservation> reservations = new LinkedList<>();
}
