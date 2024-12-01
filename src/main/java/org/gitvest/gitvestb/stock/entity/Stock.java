package org.gitvest.gitvestb.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "stock")
public class Stock extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long stockId;

  @Column(nullable = false)
  private Integer price;

  @ManyToOne
  @JoinColumn(name = "repositoryId")
  private Repository repository;

  @ManyToOne
  @JoinColumn(name = "owner")
  private Member member;
}
