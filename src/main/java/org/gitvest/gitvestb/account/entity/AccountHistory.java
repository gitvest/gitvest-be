package org.gitvest.gitvestb.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gitvest.gitvestb.global.entity.Base;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accountHistory")
@Getter
public class AccountHistory extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accountHistoryId;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private Integer price;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TransactionType ipji;// 입/출금 관련 변수

  @Column(nullable = false)
  private Integer fee;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "accountId")
  private Account account;
}
