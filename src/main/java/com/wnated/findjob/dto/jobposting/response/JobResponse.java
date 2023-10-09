package com.wnated.findjob.dto.jobposting.response;

import com.wnated.findjob.domain.company.Company;
import com.wnated.findjob.domain.jobposting.JobPosting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JobResponse {

    private Long id;

    private Company company;

    private String position;

    private int compensation; //채용보상금

    private String postingDetails;

    private String technologyUsed;

    @Builder
    private JobResponse(Long id, Company company, String position, int compensation,
        String postingDetails, String technologyUsed) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.compensation = compensation;
        this.postingDetails = postingDetails;
        this.technologyUsed = technologyUsed;
    }

    public static JobResponse of(JobPosting jobPosting) {
        return JobResponse.builder().
            id(jobPosting.getId()).
            company(jobPosting.getCompany()).
            position(jobPosting.getPosition()).
            compensation(jobPosting.getCompensation()).
            postingDetails(jobPosting.getPostingDetails()).
            technologyUsed(jobPosting.getTechnologyUsed()).
            build();
    }
}
