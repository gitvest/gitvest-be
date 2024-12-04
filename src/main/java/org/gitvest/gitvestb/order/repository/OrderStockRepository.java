package org.gitvest.gitvestb.order.repository;

import org.gitvest.gitvestb.order.entity.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStockRepository extends JpaRepository<OrderStock, Long> {

}
