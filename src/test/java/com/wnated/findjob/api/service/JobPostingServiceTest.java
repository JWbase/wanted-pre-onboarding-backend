package com.wnated.findjob.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.wnated.findjob.domain.company.City;
import com.wnated.findjob.domain.company.Company;
import com.wnated.findjob.domain.company.Country;
import com.wnated.findjob.domain.jobposting.JobPosting;
import com.wnated.findjob.dto.jobposting.requeset.JobCreateRequest;
import com.wnated.findjob.dto.jobposting.requeset.JobUpdateRequest;
import com.wnated.findjob.dto.jobposting.response.JobResponse;
import com.wnated.findjob.repository.company.CompanyRepository;
import com.wnated.findjob.repository.jobposting.JobPostingRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobPostingServiceTest {

    @Autowired
    JobPostingService jobPostingService;

    @Autowired
    JobPostingRepository jobPostingRepository;

    @Autowired
    CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        jobPostingRepository.deleteAllInBatch();
        companyRepository.deleteAllInBatch();
    }

    @DisplayName("회사정보를 받아 채용공고를 생성한다.")
    @Test
    void createJobPosting() {
        //given
        Company company = createCompany();

        Company savedCompany = companyRepository.save(company);

        JobCreateRequest request = JobCreateRequest.builder()
            .companyId(savedCompany.getId())
            .position("백엔드")
            .postingDetails("백엔드 개발자 채용합니다.")
            .compensation(500000)
            .technologyUsed("Java")
            .build();

        //when
        JobResponse jobResponse = jobPostingService.saveJob(request);

        //then
        assertThat(jobResponse.getId()).isNotNull();
    }

    @DisplayName("회사ID를 제외한 채용공고를 수정할 수 있다.")
    @Test
    void updateJob() {
        //given
        Company company = createCompany();

        Company savedCompany = companyRepository.save(company);

        JobCreateRequest request = JobCreateRequest.builder()
            .companyId(savedCompany.getId())
            .position("백엔드")
            .postingDetails("백엔드 개발자 채용합니다.")
            .compensation(500000)
            .technologyUsed("Java")
            .build();
        jobPostingService.saveJob(request);

        JobUpdateRequest updateRequest = JobUpdateRequest.builder()
            .id(1L)
            .technologyUsed("Python")
            .postingDetails("백엔드 개발자 채용합니다.")
            .compensation(1000000)
            .position("백엔드")
            .build();

        //when
        jobPostingService.updateJob(updateRequest);
        JobPosting findJobPosting = jobPostingRepository.findById(1L)
            .orElseThrow(IllegalArgumentException::new);

        //then
        assertThat(findJobPosting.getCompensation()).isEqualTo(1000000);
    }

    private static Company createCompany() {
        Company company = Company.builder()
            .name("원티드랩")
            .country(Country.KOREA)
            .city(City.SEOUL)
            .build();
        return company;
    }

}