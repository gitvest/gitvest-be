package org.gitvest.gitvestb.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gitvest.gitvestb.global.entity.Base;
import org.gitvest.gitvestb.stock.entity.Stock;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderStock")
@Getter
public class OrderStock extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderStockId;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStockState state;

  @OneToOne
  @JoinColumn(name = "orderId")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "stockId")
  private Stock stock;
}
