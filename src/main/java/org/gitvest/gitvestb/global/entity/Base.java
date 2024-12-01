package org.gitvest.gitvestb.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@NoArgsConstructor
@Getter
public abstract class Base {

  @CreatedDate
  @Column(name = "createdAt", updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updatedAt", nullable = false)
  private LocalDateTime updatedAt;

  @Column(name = "deletedAt")
  private LocalDateTime deletedAt;

  @PreUpdate
  public void preUpdate() {
    updatedAt = now();
  }

  private LocalDateTime now() {
    return LocalDateTime.now();
  }

}
