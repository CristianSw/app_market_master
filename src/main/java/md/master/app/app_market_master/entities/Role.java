package md.master.app.app_market_master.entities;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
