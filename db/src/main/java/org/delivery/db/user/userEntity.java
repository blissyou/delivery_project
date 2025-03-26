package org.delivery.db.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name= "user")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class userEntity extends BaseEntity {

    @Column(length=50,nullable=false)
    private String name;

    @Column(length=100,nullable=false)
    private String password;

    @Column(length=100,nullable=false)
    private String email;

    @Column(length=50,nullable=false)
    @Enumerated(EnumType.STRING)
    private String status;

    @Column(length=150,nullable=false)
    private String address;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime lastLoginAt;
}
