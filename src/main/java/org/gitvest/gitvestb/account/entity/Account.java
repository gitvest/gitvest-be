package org.gitvest.gitvestb.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.gitvest.gitvestb.global.entity.Base;
import org.gitvest.gitvestb.member.entity.Member;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accountId;

  @Column(nullable = false)
  private Long balance;

  @OneToOne
  @JoinColumn(name = "memberId")
  private Member member;

}
