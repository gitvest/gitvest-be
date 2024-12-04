package org.gitvest.gitvestb.member.repository;

import org.gitvest.gitvestb.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
