package com.example.pullpointdev.wallet.model;

import com.example.pullpointdev.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="wallet")
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @OneToOne
    @NotNull
    @JsonIgnore
    private User owner;

    @Min(0)
    private long balance;

    @ManyToMany
    @JsonIgnore
    private List<Transaction> history;

    private String bankCredentials;
}
