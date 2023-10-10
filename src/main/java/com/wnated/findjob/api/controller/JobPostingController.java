package com.wnated.findjob.api.controller;

import com.wnated.findjob.api.service.JobPostingService;
import com.wnated.findjob.dto.jobposting.requeset.JobCreateRequest;
import com.wnated.findjob.dto.jobposting.requeset.JobUpdateRequest;
import com.wnated.findjob.dto.jobposting.response.JobResponse;
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
    public List<JobResponse> getJobs() {
        return jobPostingService.findJobs();
    }


    @PostMapping("/api/jobs")
    public JobResponse createJob(@RequestBody JobCreateRequest request) {
        return jobPostingService.saveJob(request);
    }

    @PutMapping("/api/jobs/{jobPostingId}/edit")
    public void updateJob(@RequestBody JobUpdateRequest request, @PathVariable Long jobPostingId) {
        request.setId(jobPostingId);
        jobPostingService.updateJob(request);
    }

    @DeleteMapping("/api/jobs/{jobPostingId}/delete")
    public void deleteJob(@PathVariable Long jobPostingId) {
        jobPostingService.deleteJob(jobPostingId);
    }
}
