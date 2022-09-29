package com.example.pullpointdev.wallet.model;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.user.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="currency_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private TransactionType type;

    @OneToOne
    private User owner;

    private Long sum;

    @Nullable
    @ManyToOne
    private Artist receiver;

}