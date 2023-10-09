package com.wnated.findjob.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.wnated.findjob.dto.jobposting.requeset.JobCreateRequest;
import com.wnated.findjob.dto.jobposting.response.JobResponse;
import com.wnated.findjob.repository.company.CompanyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@Sql("/sql/company-repository-test-data.sql")
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
        JobCreateRequest request = new JobCreateRequest(1L, "백엔드", 10000, "채용공고", "자바");

        //when
        JobResponse jobResponse = jobPostingService.saveJob(request);

        //then
        assertThat(jobResponse.getId()).isNotNull();
    }

}