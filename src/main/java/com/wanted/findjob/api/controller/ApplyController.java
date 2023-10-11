package com.wanted.findjob.api.controller;

import com.wanted.findjob.api.service.JobPostingService;
import com.wanted.findjob.api.service.ApplyService;
import com.wanted.findjob.dto.userjobposting.request.ApplyCompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApplyController {

    private final JobPostingService jobPostingService;
    private final ApplyService applyService;

    @PostMapping("/api/apply")
    public void applyCompany(@RequestBody ApplyCompanyRequest request) {
        applyService.applyCompany(request);
    }
}
