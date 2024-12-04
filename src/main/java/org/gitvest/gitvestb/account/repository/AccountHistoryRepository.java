package org.gitvest.gitvestb.account.repository;

import org.gitvest.gitvestb.account.entity.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {

}
