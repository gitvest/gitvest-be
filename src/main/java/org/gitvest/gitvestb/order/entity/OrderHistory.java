package org.gitvest.gitvestb.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gitvest.gitvestb.global.entity.Base;
import org.gitvest.gitvestb.member.entity.Member;
import org.gitvest.gitvestb.repository.entity.Repository;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderHistory")
public class OrderHistory extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderHistoryId;

  @Column(nullable = false)
  private Long seller;

  @Column(nullable = false)
  private Long buyer;

  @Column(nullable = false)
  private LocalDateTime completedAt;

  @Column(nullable = false)
  private Integer fee;

  @ManyToOne
  @JoinColumn(name = "orderId")
  private Order order;
}
