package com.wnated.findjob.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.wnated.findjob.domain.company.City;
import com.wnated.findjob.domain.company.Company;
import com.wnated.findjob.domain.company.Country;
import com.wnated.findjob.dto.jobposting.requeset.JobCreateRequest;
import com.wnated.findjob.dto.jobposting.response.JobResponse;
import com.wnated.findjob.repository.company.CompanyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobPostingServiceTest {

    @Autowired
    JobPostingService jobPostingService;

    @Autowired
    CompanyRepository companyRepository;

    @DisplayName("회사정보를 받아 채용공고를 생성한다.")
    @Test
    void createJobPosting() {
        //given
        Company company = Company.builder()
            .name("원티드랩")
            .country(Country.KOREA)
            .city(City.SEOUL)
            .build();

        Company savedCompany = companyRepository.save(company);

        JobCreateRequest request = new JobCreateRequest(savedCompany.getId(), "백엔드", 10000, "채용공고",
            "자바");

        //when
        JobResponse jobResponse = jobPostingService.saveJob(request);

        //then
        assertThat(jobResponse.getId()).isNotNull();
    }

}