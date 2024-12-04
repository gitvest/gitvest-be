package org.gitvest.gitvestb.repository.repository;

import org.gitvest.gitvestb.repository.entity.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<Repository, Long> {

}
