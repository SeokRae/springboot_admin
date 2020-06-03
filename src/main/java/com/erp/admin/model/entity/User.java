package com.erp.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderGroupList"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}
