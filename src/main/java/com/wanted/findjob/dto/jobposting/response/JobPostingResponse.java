package com.wanted.findjob.dto.jobposting.response;

import com.wanted.findjob.domain.jobposting.JobPosting;
import com.wanted.findjob.dto.company.response.CompanyResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JobPostingResponse {

    private Long id;

    private CompanyResponse company;

    private String position;

    private int compensation; //채용보상금

    private String postingDetails;

    private String technologyUsed;

    private List<Long> otherPostings;

    @Builder
    private JobPostingResponse(Long id, CompanyResponse company, String position, int compensation,
        String postingDetails, String technologyUsed, List<Long> otherPostings) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.compensation = compensation;
        this.postingDetails = postingDetails;
        this.technologyUsed = technologyUsed;
        this.otherPostings = otherPostings;
    }

    public static JobPostingResponse of(JobPosting jobPosting, List<JobPosting> otherPostings) {
        return JobPostingResponse.builder().
            id(jobPosting.getId()).
            company(CompanyResponse.of(jobPosting.getCompany())).
            position(jobPosting.getPosition()).
            compensation(jobPosting.getCompensation()).
            postingDetails(jobPosting.getPostingDetails()).
            technologyUsed(jobPosting.getTechnologyUsed()).
            otherPostings(otherPostings.stream()
                .map(JobPosting::getId)
                .collect(Collectors.toList())).
            build();
    }
}
