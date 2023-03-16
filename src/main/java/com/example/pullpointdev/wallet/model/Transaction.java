package com.example.pullpointdev.wallet.model;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name="currency_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private long id;

    private TransactionType type;

    @ManyToOne
    @JsonIgnore
    private User owner;

    private Date timestamp;

    private Long sum;

    @Nullable
    @ManyToOne
    private Artist receiver;

}