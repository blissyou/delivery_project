package org.delivery.db.userorder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity,Long> {
}
