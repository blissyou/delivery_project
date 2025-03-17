package org.delivery.db.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="account")
@Entity
@ToString
@NoArgsConstructor
public class AccountEntity extends BaseEntity {

}
