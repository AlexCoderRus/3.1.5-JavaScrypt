package ru.kata.spring.boot_security.demo.model;



import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;


@Entity
@Data
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
