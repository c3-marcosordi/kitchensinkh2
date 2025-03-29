package org.jboss.as.quickstarts.kitchensink.data;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;


@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    
    Member findByEmail(String email);
    List<Member> findAllByOrderByNameAsc();

    // @Query("SELECT COUNT(m) FROM Member m")
    // long countAllMembers();
}