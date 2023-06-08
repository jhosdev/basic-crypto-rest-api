package com.pokeswap.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name="currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Column(name = "wait_time", nullable = false)
    private Integer waitTime;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "commission", nullable = false)
    private Double commission;

    @Column(name = "minimum_deposit", nullable = false)
    private Double minimumDeposit;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}