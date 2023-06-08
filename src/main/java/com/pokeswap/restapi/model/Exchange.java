package com.pokeswap.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokeswap.restapi.enums.Ex_Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exchanges")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Column(name = "to_user_id", nullable = false)
    private Long toUserId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "from_token_id", nullable = false)
    private Long fromTokenId;

    @Column(name = "from_token_quantity", nullable = false)
    private Double fromTokenQuantity;

    @Column(name = "to_token_id", nullable = false)
    private Long toTokenId;

    @Column(name = "to_token_quantity", nullable = false)
    private Double toTokenQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Ex_Status status;

    @Column(name = "exchange_date", nullable = false)
    private LocalDateTime exchangeDate;

    @PrePersist
    public void prePersist() {
        this.exchangeDate = LocalDateTime.now();
    }
}