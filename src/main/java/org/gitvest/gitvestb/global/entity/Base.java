package org.gitvest.gitvestb.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
public abstract class Base {

  @Column(name = "createdAt", updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updatedAt", nullable = false)
  private LocalDateTime updatedAt;

  @Column(name = "deletedAt")
  private LocalDateTime deletedAt;

  public Base() {
  }

  @PrePersist
  public void prePersist() {
    LocalDateTime now = now();
    createdAt = now;
    updatedAt = now;
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = now();
  }

  private LocalDateTime now() {
    return LocalDateTime.now();
  }

}
