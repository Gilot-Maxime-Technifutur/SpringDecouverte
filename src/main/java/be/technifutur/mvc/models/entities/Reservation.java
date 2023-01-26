package be.technifutur.mvc.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreate;

    @Column(name = "date_cancellation")
    private LocalDateTime dateCancel;

    @Column(nullable = false, precision = 2)
    private Double price;

    private Double discount;

    @Column(name = "with_additional_beds", nullable = false)
    private Integer withAdditionalBeds;

    @Column(name = "breakfastincluded", nullable = false)
    private Boolean breakfast;

    @Column(name = "date_begin", nullable = false)
    private LocalDateTime begin;

    @Column(name = "date_end", nullable = false)
    private LocalDateTime end;
//--------------------------------------------------//
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}
