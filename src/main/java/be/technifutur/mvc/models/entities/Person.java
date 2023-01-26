package be.technifutur.mvc.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String phone;

    @Column(nullable = false, length = 100)
    private String address;
}
