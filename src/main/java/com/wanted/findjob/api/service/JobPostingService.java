package com.wanted.findjob.api.service;

import com.wanted.findjob.domain.company.Company;
import com.wanted.findjob.domain.jobposting.JobPosting;
import com.wanted.findjob.dto.jobposting.requeset.JobPostingCreateRequest;
import com.wanted.findjob.dto.jobposting.requeset.JobPostingUpdateRequest;
import com.wanted.findjob.dto.jobposting.response.JobPostingListResponse;
import com.wanted.findjob.dto.jobposting.response.JobPostingResponse;
import com.wanted.findjob.repository.company.CompanyRepository;
import com.wanted.findjob.repository.jobposting.JobPostingRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    public List<JobPostingListResponse> findJobPostings() {
        return jobPostingRepository.findAll().stream()
            .map(JobPostingListResponse::of)
            .collect(Collectors.toList());
    }

    @Transactional
    public JobPostingListResponse createJobPosting(JobPostingCreateRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
            .orElseThrow(IllegalArgumentException::new);

        JobPosting savedJob = jobPostingRepository.save(JobPosting.builder()
            .company(company)
            .position(request.getPosition())
            .compensation(request.getCompensation())
            .postingDetails(request.getPostingDetails())
            .technologyUsed(request.getTechnologyUsed())
            .build());

        return JobPostingListResponse.of(savedJob);
    }

    public JobPostingResponse findOne(Long jobPostingId) {

        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
            .orElseThrow(IllegalArgumentException::new);

        //같은 회사의 다른 채용공고 Id 조회
        List<JobPosting> otherPostings = jobPostingRepository.findAllByCompanyAndIdNot(
            jobPosting.getCompany(), jobPosting.getId());

        return JobPostingResponse.of(jobPosting, otherPostings);
    }

    @Transactional
    public void updateJobPosting(JobPostingUpdateRequest request) {
        JobPosting jobPosting = jobPostingRepository.findById(request.getId())
            .orElseThrow(IllegalArgumentException::new);

        jobPosting.updateJobPosting(
            request.getPosition(),
            request.getCompensation(),
            request.getPostingDetails(),
            request.getTechnologyUsed());
    }

    @Transactional
    public void deleteJobPosting(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
            .orElseThrow(IllegalArgumentException::new);

        jobPostingRepository.delete(jobPosting);
    }

}
