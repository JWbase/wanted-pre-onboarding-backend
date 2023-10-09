package com.wnated.findjob.api.service;

import com.wnated.findjob.domain.company.Company;
import com.wnated.findjob.domain.jobposting.JobPosting;
import com.wnated.findjob.dto.jobposting.requeset.JobCreateRequest;
import com.wnated.findjob.dto.jobposting.response.JobResponse;
import com.wnated.findjob.repository.company.CompanyRepository;
import com.wnated.findjob.repository.jobposting.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public JobResponse saveJob(JobCreateRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
            .orElseThrow(IllegalAccessError::new);

        JobPosting savedJob = jobPostingRepository.save(JobPosting.builder()
            .company(company)
            .position(request.getPosition())
            .compensation(request.getCompensation())
            .postingDetails(request.getPostingDetails())
            .technologyUsed(request.getTechnologyUsed())
            .build());

        return JobResponse.of(savedJob);
    }
}
