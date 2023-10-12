package com.wanted.findjob.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.wanted.findjob.dto.jobposting.requeset.JobPostingUpdateRequest;
import com.wanted.findjob.dto.jobposting.response.JobPostingListResponse;
import com.wanted.findjob.repository.jobposting.JobPostingRepository;
import com.wanted.findjob.domain.company.City;
import com.wanted.findjob.domain.company.Company;
import com.wanted.findjob.domain.company.Country;
import com.wanted.findjob.domain.jobposting.JobPosting;
import com.wanted.findjob.dto.jobposting.requeset.JobPostingCreateRequest;
import com.wanted.findjob.dto.jobposting.response.JobPostingResponse;
import com.wanted.findjob.repository.company.CompanyRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        Company company = createCompany("원티드랩");
        Company savedCompany = companyRepository.save(company);

        JobPostingCreateRequest request = JobPostingCreateRequest.builder()
            .companyId(savedCompany.getId())
            .position("백엔드")
            .postingDetails("백엔드 개발자 채용합니다.")
            .compensation(500000)
            .technologyUsed("Java")
            .build();

        //when
        JobPostingListResponse jobPostingListResponse = jobPostingService.createJobPosting(request);

        //then
        assertThat(jobPostingListResponse.getId()).isNotNull();
    }

    @DisplayName("회사ID를 제외한 채용공고를 수정할 수 있다.")
    @Test
    void updateJob() {
        //given
        Company company = createCompany("원티드랩");
        Company savedCompany = companyRepository.save(company);

        JobPostingCreateRequest request = JobPostingCreateRequest.builder()
            .companyId(savedCompany.getId())
            .position("백엔드")
            .postingDetails("백엔드 개발자 채용합니다.")
            .compensation(500000)
            .technologyUsed("Java")
            .build();

        JobPostingListResponse jobPosting = jobPostingService.createJobPosting(request);

        JobPostingUpdateRequest updateRequest = JobPostingUpdateRequest.builder()
            .id(jobPosting.getId())
            .technologyUsed("Python")
            .postingDetails("백엔드 개발자 채용합니다.")
            .compensation(1000000)
            .position("백엔드")
            .build();

        //when
        jobPostingService.updateJobPosting(updateRequest);
        JobPosting findJobPosting = jobPostingRepository.findById(updateRequest.getId())
            .orElseThrow(IllegalArgumentException::new);

        //then
        assertThat(findJobPosting.getCompensation()).isEqualTo(1000000);
    }

    @DisplayName("검색어가 없을 때 전체 채용 공고 리스트를 조회 한다.")
    @Test
    void findJobPostings() {
        //given
        Company company = createCompany("원티드랩");
        Company savedCompany = companyRepository.save(company);

        JobPosting request1 = createJobPosting(savedCompany, "백엔드");
        JobPosting request2 = createJobPosting(savedCompany, "프론트엔드");
        JobPosting request3 = createJobPosting(savedCompany, "솔루션");

        jobPostingRepository.saveAll(List.of(request1, request2, request3));

        //when
        List<JobPostingListResponse> jobs = jobPostingService.findJobPostings(null);

        //then
        assertThat(jobs).hasSize(3);
    }

    @DisplayName("특정 키워드로 채용공고를 검색할 수 있다.")
    @Test
    void findJobPostingsBySearch() {
        //given
        Company company = createCompany("원티드랩");
        Company savedCompany = companyRepository.save(company);

        JobPosting request1 = createJobPosting(savedCompany, "백엔드");
        JobPosting request2 = createJobPosting(savedCompany, "프론트엔드");
        JobPosting request3 = createJobPosting(savedCompany, "솔루션");

        jobPostingRepository.saveAll(List.of(request1, request2, request3));
        String search = "프론트엔드";

        //when
        List<JobPostingListResponse> jobs = jobPostingService.findJobPostings(search);

        //then
        assertThat(jobs).hasSize(1);
    }


    @DisplayName("해당 회사의 다른 채용공고를 함께 출력한다.")
    @Test
    void detailJobPosting() {
        //given
        Company company = createCompany("원티드랩");
        Company savedCompany = companyRepository.save(company);

        JobPosting request1 = createJobPosting(savedCompany, "백엔드");
        JobPosting request2 = createJobPosting(savedCompany, "프론트엔드");
        JobPosting request3 = createJobPosting(savedCompany, "솔루션");

        jobPostingRepository.saveAll(List.of(request1, request2, request3));

        //when
        JobPostingResponse findJobPosting = jobPostingService.findOne(request1.getId());

        //then
        assertThat(findJobPosting.getOtherPostings()).hasSize(2);
    }

    @DisplayName("채용 공고를 삭제할 수 있다.")
    @Test
    void deleteJobPosting() {
        //given
        Company company = createCompany("원티드랩");
        Company savedCompany = companyRepository.save(company);

        JobPostingCreateRequest request = JobPostingCreateRequest.builder()
            .companyId(savedCompany.getId())
            .position("백엔드")
            .postingDetails("백엔드 개발자 채용합니다.")
            .compensation(500000)
            .technologyUsed("Java")
            .build();
        JobPostingListResponse jobPostingListResponse = jobPostingService.createJobPosting(request);

        //when
        jobPostingService.deleteJobPosting(jobPostingListResponse.getId());

        //then
        assertThrows(IllegalArgumentException.class,
            () -> jobPostingService.findOne(jobPostingListResponse.getId()));
    }

    private static JobPosting createJobPosting(Company company, String position) {
        return JobPosting.builder()
            .company(company)
            .position(position)
            .postingDetails("개발자 채용합니다.")
            .compensation(500000)
            .technologyUsed("Java")
            .build();
    }

    private static Company createCompany(String name) {
        Company company = Company.builder()
            .name(name)
            .country(Country.KOREA)
            .city(City.SEOUL)
            .build();
        return company;
    }

}