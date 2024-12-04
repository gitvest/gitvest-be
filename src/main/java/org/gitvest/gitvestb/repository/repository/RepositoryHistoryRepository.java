package org.gitvest.gitvestb.repository.repository;

import org.gitvest.gitvestb.repository.entity.RepositoryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryHistoryRepository extends JpaRepository<RepositoryHistory, Long> {

}
