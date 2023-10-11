package com.wanted.findjob.dto.jobposting.response;

import com.wanted.findjob.dto.company.response.CompanyResponse;
import com.wanted.findjob.domain.jobposting.JobPosting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JobPostingListResponse {

    private Long id;

    private CompanyResponse company;

    private String position;

    private int compensation; //채용보상금

    private String technologyUsed;

    @Builder
    private JobPostingListResponse(Long id, CompanyResponse company, String position,
        int compensation,
        String technologyUsed) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.compensation = compensation;
        this.technologyUsed = technologyUsed;
    }

    public static JobPostingListResponse of(JobPosting jobPosting) {
        return JobPostingListResponse.builder().
            id(jobPosting.getId()).
            company(CompanyResponse.of(jobPosting.getCompany())).
            position(jobPosting.getPosition()).
            compensation(jobPosting.getCompensation()).
            technologyUsed(jobPosting.getTechnologyUsed()).
            build();
    }
}
