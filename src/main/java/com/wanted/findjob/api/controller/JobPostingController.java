package com.wanted.findjob.api.controller;

import com.wanted.findjob.api.service.JobPostingService;
import com.wanted.findjob.dto.jobposting.requeset.JobPostingCreateRequest;
import com.wanted.findjob.dto.jobposting.requeset.JobPostingUpdateRequest;
import com.wanted.findjob.dto.jobposting.response.JobPostingListResponse;
import com.wanted.findjob.dto.jobposting.response.JobPostingResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @GetMapping("/api/jobs")
    public List<JobPostingListResponse> getJobPostings() {
        return jobPostingService.findJobPostings();
    }

    @GetMapping("/api/{jobPostingId}")
    public JobPostingResponse jobPosting(@PathVariable Long jobPostingId) {
        return jobPostingService.findOne(jobPostingId);
    }

    @PostMapping("/api/jobs")
    public JobPostingListResponse createJobPosting(@RequestBody JobPostingCreateRequest request) {
        return jobPostingService.createJobPosting(request);
    }

    @PutMapping("/api/jobs/{jobPostingId}/edit")
    public void updateJobPosting(@RequestBody JobPostingUpdateRequest request,
        @PathVariable Long jobPostingId) {
        request.setId(jobPostingId);
        jobPostingService.updateJobPosting(request);
    }

    @DeleteMapping("/api/jobs/{jobPostingId}/delete")
    public void deleteJobPosting(@PathVariable Long jobPostingId) {
        jobPostingService.deleteJobPosting(jobPostingId);
    }

}
