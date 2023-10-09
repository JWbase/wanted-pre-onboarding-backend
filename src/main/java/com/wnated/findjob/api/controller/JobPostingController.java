package com.wnated.findjob.api.controller;

import com.wnated.findjob.api.service.JobPostingService;
import com.wnated.findjob.dto.jobposting.requeset.JobCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping("/api/jobs")
    public void createJob(@RequestBody JobCreateRequest request) {
        jobPostingService.saveJob(request);
    }

}
