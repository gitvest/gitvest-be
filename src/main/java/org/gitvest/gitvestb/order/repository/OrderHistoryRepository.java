package org.gitvest.gitvestb.order.repository;

import org.gitvest.gitvestb.order.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

}
