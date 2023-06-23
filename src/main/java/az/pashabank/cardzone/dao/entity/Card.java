package az.pashabank.cardzone.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cards")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pan", length = 16, unique = true)
    private String pan;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "balance")
    private double balance;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;


    @OneToMany(mappedBy = "card")
    private List<Transaction> transactions;

}
