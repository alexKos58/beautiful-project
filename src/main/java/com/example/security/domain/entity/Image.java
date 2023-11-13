package com.example.security.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "image")
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Product product;
}