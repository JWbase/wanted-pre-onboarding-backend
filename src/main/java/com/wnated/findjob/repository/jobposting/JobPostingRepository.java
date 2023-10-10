package com.wnated.findjob.repository.jobposting;

import com.wnated.findjob.domain.company.Company;
import com.wnated.findjob.domain.jobposting.JobPosting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    List<JobPosting> findAllByCompanyAndIdNot(Company company, Long id);
}
