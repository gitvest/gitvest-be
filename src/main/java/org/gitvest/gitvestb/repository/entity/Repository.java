package org.gitvest.gitvestb.repository.entity;

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
import org.gitvest.gitvestb.member.entity.Member;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repository")
public class Repository {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long repositoryId;

  @Column(nullable = false)
  private String mainLanguage;

  @Column(nullable = false)
  private String repoName;

  @Column(nullable = false)
  private String repoState;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private String repoDetail;

  @Column(nullable = false)
  private Integer firstAmount;

  @ManyToOne
  @JoinColumn(name = "owner")
  private Member member;


}
