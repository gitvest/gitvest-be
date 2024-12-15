package org.gitvest.gitvestb.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import java.util.Optional;
import org.gitvest.gitvestb.account.entity.Account;
import org.gitvest.gitvestb.global.config.QueryDslConfig;
import org.gitvest.gitvestb.member.entity.Member;
import org.gitvest.gitvestb.member.repository.dto.MemberProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(QueryDslConfig.class)
@DisplayName("MemberRepository는")
class MemberRepositoryTest {

  @Autowired
  private EntityManager em;

  @Autowired
  private MemberRepository memberRepository;

  private Member savedMember;

  @BeforeEach
  void setUp() {
    savedMember = Member.builder()
        .memberId(null)
        .socialId(1L)
        .email("email")
        .nickname("nickname")
        .profileImageUrl("profileImageUrl")
        .build();
    em.persist(savedMember);
    em.flush();
    em.clear();
  }

  @Nested
  @DisplayName("Describe: findMemberProfile 메서드는")
  public class FindMemberProfile {

    private Account savedAccount;

    @BeforeEach
    void setUp() {
      savedAccount = Account.createNewAccount(savedMember);
      em.persist(savedAccount);
      em.flush();
      em.clear();
    }

    @Nested
    @DisplayName("Context: 존재하는 Member의 id 값을 입력받으면")
    public class ExistMemberId {

      // given
      private Long memberId;

      @BeforeEach
      void setUp(){
        memberId = savedMember.getMemberId();
      }

      @Test
      @DisplayName("It: 해당하는 Member와 Account의 데이터를 포함한 MemberProfile을 반환한다.")
      public void returnMemberProfile() {
        // when
        Optional<MemberProfile> memberProfile = memberRepository.findMemberProfile(memberId);

        // then
        assertThat(memberProfile.isPresent()).isTrue();
        assertThat(memberProfile.get().balance()).isEqualTo(savedAccount.getBalance());
      }
    }

    @Nested
    @DisplayName("Context: 존재하지 않는 Member의 id 값을 입력받으면")
    public class NonExistMemberId {

      // given
      private Long nonExistMemberId;

      @BeforeEach
      void setUp(){
        nonExistMemberId = 0L;
      }

      @Test
      @DisplayName("It: 빈 Optional 객체를 반환한다")
      public void optionalIsEmpty() {
        // when
        Optional<MemberProfile> memberProfile =
            memberRepository.findMemberProfile(nonExistMemberId);

        // then
        assertThat(memberProfile.isEmpty()).isTrue();
      }
    }
  }
}