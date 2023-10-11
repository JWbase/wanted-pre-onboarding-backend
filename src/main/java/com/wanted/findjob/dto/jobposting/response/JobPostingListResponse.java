package com.wanted.findjob.dto.jobposting.response;

import com.wanted.findjob.domain.company.City;
import com.wanted.findjob.domain.company.Country;
import com.wanted.findjob.dto.company.response.CompanyResponse;
import com.wanted.findjob.domain.jobposting.JobPosting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JobPostingListResponse {

    private Long id;

    private String companyName;

    private Country country;

    private City city;

    private String position;

    private int compensation; //채용보상금

    private String technologyUsed;

    @Builder
    private JobPostingListResponse(Long id, String companyName, Country country, City city,
        String position, int compensation, String technologyUsed) {
        this.id = id;
        this.companyName = companyName;
        this.country = country;
        this.city = city;
        this.position = position;
        this.compensation = compensation;
        this.technologyUsed = technologyUsed;
    }

    @Builder

    public static JobPostingListResponse of(JobPosting jobPosting) {
        return JobPostingListResponse.builder().
            id(jobPosting.getId()).
            companyName(CompanyResponse.of(jobPosting.getCompany()).getName()).
            country(CompanyResponse.of(jobPosting.getCompany()).getCountry()).
            city(CompanyResponse.of(jobPosting.getCompany()).getCity()).
            position(jobPosting.getPosition()).
            compensation(jobPosting.getCompensation()).
            technologyUsed(jobPosting.getTechnologyUsed()).
            build();
    }
}
