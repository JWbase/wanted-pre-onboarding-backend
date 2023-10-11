package com.wanted.findjob.dto.jobposting.response;

import com.wanted.findjob.domain.company.City;
import com.wanted.findjob.domain.company.Country;
import com.wanted.findjob.domain.jobposting.JobPosting;
import com.wanted.findjob.dto.company.response.CompanyResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JobPostingResponse {

    private Long id;

    private String companyName;

    private Country country;

    private City city;

    private String position;

    private int compensation; //채용보상금

    private String postingDetails;

    private String technologyUsed;

    private List<Long> otherPostings;

    @Builder
    private JobPostingResponse(Long id, String companyName, Country country, City city,
        String position,
        int compensation, String postingDetails, String technologyUsed, List<Long> otherPostings) {
        this.id = id;
        this.companyName = companyName;
        this.country = country;
        this.city = city;
        this.position = position;
        this.compensation = compensation;
        this.postingDetails = postingDetails;
        this.technologyUsed = technologyUsed;
        this.otherPostings = otherPostings;
    }

    public static JobPostingResponse of(JobPosting jobPosting) {

        List<Long> otherJobPostingIds = jobPosting.getCompany().getJobPosting().stream()
            .filter(otherPosting -> !otherPosting.getId().equals(jobPosting.getId()))
            .map(JobPosting::getId)
            .collect(Collectors.toList());

        return JobPostingResponse.builder().
            id(jobPosting.getId()).
            companyName(CompanyResponse.of(jobPosting.getCompany()).getName()).
            country(CompanyResponse.of(jobPosting.getCompany()).getCountry()).
            city(CompanyResponse.of(jobPosting.getCompany()).getCity()).
            position(jobPosting.getPosition()).
            compensation(jobPosting.getCompensation()).
            postingDetails(jobPosting.getPostingDetails()).
            technologyUsed(jobPosting.getTechnologyUsed()).
            otherPostings(otherJobPostingIds).
            build();
    }
}
