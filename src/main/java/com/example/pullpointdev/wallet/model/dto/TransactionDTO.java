package com.example.pullpointdev.wallet.model.dto;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.wallet.model.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class TransactionDTO {
    private TransactionType type;
    private TransactionDirection dir;

    private User sub;

    private Date timestamp;

    private Long sum;
}
