package md.master.app.core.entities;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

//    @CreationTimestamp
//    @Column(name = "name")
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "name")
//    private LocalDateTime updatedAt;
}
