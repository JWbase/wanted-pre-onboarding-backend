package com.wanted.findjob.repository.applyHistory;

import com.wanted.findjob.domain.jobposting.JobPosting;
import com.wanted.findjob.domain.user.ApplyHistory;
import com.wanted.findjob.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyHistoryRepository extends JpaRepository<ApplyHistory, Long> {

    boolean existsByJobPostingAndUser(JobPosting jobPosting, User user);
}
