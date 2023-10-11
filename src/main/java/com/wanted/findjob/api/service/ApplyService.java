package com.wanted.findjob.api.service;

import com.wanted.findjob.domain.jobposting.JobPosting;
import com.wanted.findjob.domain.user.User;
import com.wanted.findjob.dto.userjobposting.request.ApplyCompanyRequest;
import com.wanted.findjob.exception.ResourceNotFoundException;
import com.wanted.findjob.repository.applyHistory.ApplyHistoryRepository;
import com.wanted.findjob.repository.jobposting.JobPostingRepository;
import com.wanted.findjob.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ApplyService {

    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;
    private final ApplyHistoryRepository userJobPostingRepository;

    @Transactional
    public void applyCompany(ApplyCompanyRequest request) {
        // 1. 채용공고 정보 찾기
        JobPosting jobPosting = jobPostingRepository.findById(request.getJobPostingId())
            .orElseThrow(
                () -> new ResourceNotFoundException("jobPosting", request.getJobPostingId()));

        // 2. 유저 정보 가져오기
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("user", request.getUserId()));

        // 3. 지원 유무 확인
        // 3-1. 지원 중이면 예외 발생
        if (userJobPostingRepository.existsByJobPostingAndUser(jobPosting, user)) {
            throw new IllegalArgumentException("이미 지원하신 회사입니다.");
        }

        user.applyCompany(jobPosting);
    }
}
