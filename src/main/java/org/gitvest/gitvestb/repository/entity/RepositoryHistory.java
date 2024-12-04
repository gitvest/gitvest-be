package org.gitvest.gitvestb.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "repositoryHistory")
@Getter
public class RepositoryHistory extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long repositoryHistoryId;

  @Column(nullable = false)
  private Integer highPrice;

  @Column(nullable = false)
  private Integer lowPrice;

  @Column(nullable = false)
  private Integer openingPrice;

  @Column(nullable = false)
  private Integer closingPrice;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "repositoryId")
  private Repository repository;
}
