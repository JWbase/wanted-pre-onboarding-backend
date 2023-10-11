package com.wanted.findjob.api.service;

import com.wanted.findjob.domain.jobposting.JobPosting;
import com.wanted.findjob.domain.user.User;
import com.wanted.findjob.dto.userjobposting.request.ApplyCompanyRequest;
import com.wanted.findjob.repository.applyHistory.ApplyHistoryRepository;
import com.wanted.findjob.repository.company.CompanyRepository;
import com.wanted.findjob.repository.jobposting.JobPostingRepository;
import com.wanted.findjob.repository.user.UserRepository;
import com.wanted.findjob.domain.company.City;
import com.wanted.findjob.domain.company.Company;
import com.wanted.findjob.domain.company.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    JobPostingService jobPostingService;

    @Autowired
    ApplyService applyService;

    @Autowired
    JobPostingRepository jobPostingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplyHistoryRepository applyHistoryRepository;

    @Autowired
    CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        applyHistoryRepository.deleteAllInBatch();
        jobPostingRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        companyRepository.deleteAllInBatch();
    }

    @DisplayName("사용자는 채용 공고를 지원 할 수 있다.")
    @Test
    @Transactional
    void applyCompany() {
        //given
        User user = User.builder()
            .id(1L)
            .name("jw")
            .build();

        User savedUser = userRepository.save(user);

        Company company = Company.builder()
            .name("company1")
            .country(Country.KOREA)
            .city(City.SEOUL)
            .build();
        Company savedCompany = companyRepository.save(company);

        JobPosting jobPosting = JobPosting.builder()
            .company(savedCompany)
            .position("백엔드")
            .postingDetails("백엔드 개발자 채용합니다.")
            .compensation(500000)
            .technologyUsed("Java")
            .build();

        JobPosting savedJobPosting = jobPostingRepository.save(jobPosting);

        ApplyCompanyRequest request = new ApplyCompanyRequest();
        request.setUserId(savedUser.getId());
        request.setJobPostingId(savedJobPosting.getId());

        //when
        applyService.applyCompany(request);

        //then


    }

}