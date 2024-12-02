package org.gitvest.gitvestb.account.repository;

import org.gitvest.gitvestb.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
