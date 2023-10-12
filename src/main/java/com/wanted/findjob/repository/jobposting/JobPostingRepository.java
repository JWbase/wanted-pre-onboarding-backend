package com.wanted.findjob.repository.jobposting;

import com.wanted.findjob.domain.jobposting.JobPosting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    @Query("SELECT J FROM JobPosting J " +
        "WHERE " +
        "(:search IS NULL OR " +
        " J.company.name LIKE %:search% OR " +
        " J.position LIKE %:search% OR " +
        " J.postingDetails LIKE %:search% OR " +
        " J.technologyUsed LIKE %:search%)")
    List<JobPosting> findAllBySearch(@Param("search") String search);
}
