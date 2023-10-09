package com.wnated.findjob.repository.jobposting;

import com.wnated.findjob.domain.jobposting.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

}
