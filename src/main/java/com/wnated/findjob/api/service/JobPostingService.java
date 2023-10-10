package com.wnated.findjob.api.service;

import com.wnated.findjob.domain.company.Company;
import com.wnated.findjob.domain.jobposting.JobPosting;
import com.wnated.findjob.dto.jobposting.requeset.JobCreateRequest;
import com.wnated.findjob.dto.jobposting.requeset.JobUpdateRequest;
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
            .orElseThrow(IllegalArgumentException::new);

        JobPosting savedJob = jobPostingRepository.save(JobPosting.builder()
            .company(company)
            .position(request.getPosition())
            .compensation(request.getCompensation())
            .postingDetails(request.getPostingDetails())
            .technologyUsed(request.getTechnologyUsed())
            .build());

        return JobResponse.of(savedJob);
    }

    @Transactional
    public void updateJob(JobUpdateRequest request) {
        JobPosting jobPosting = jobPostingRepository.findById(request.getId())
            .orElseThrow(IllegalArgumentException::new);

        jobPosting.updateJobPosting(
            request.getPosition(),
            request.getCompensation(),
            request.getPostingDetails(),
            request.getTechnologyUsed());
    }

    public void deleteJob(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
            .orElseThrow(IllegalArgumentException::new);

        jobPostingRepository.delete(jobPosting);
    }
}
