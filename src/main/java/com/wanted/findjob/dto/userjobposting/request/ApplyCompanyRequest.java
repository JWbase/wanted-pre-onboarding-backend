package com.wanted.findjob.dto.userjobposting.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyCompanyRequest {

    private Long jobPostingId;
    private Long userId;
}
