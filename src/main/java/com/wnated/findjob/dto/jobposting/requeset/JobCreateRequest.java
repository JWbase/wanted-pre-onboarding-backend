package com.wnated.findjob.dto.jobposting.requeset;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobCreateRequest {

    private Long companyId;

    private String position;

    private int compensation; //채용보상금

    private String postingDetails;

    private String technologyUsed;

    public JobCreateRequest(Long companyId, String position, int compensation,
        String postingDetails,
        String technologyUsed) {
        this.companyId = companyId;
        this.position = position;
        this.compensation = compensation;
        this.postingDetails = postingDetails;
        this.technologyUsed = technologyUsed;
    }
}