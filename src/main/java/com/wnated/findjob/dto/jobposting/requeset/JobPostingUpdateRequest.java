package com.wnated.findjob.dto.jobposting.requeset;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobPostingUpdateRequest {

    private Long id;

    private String position;

    private int compensation; //채용보상금

    private String postingDetails;

    private String technologyUsed;

    @Builder
    private JobPostingUpdateRequest(Long id, String position, int compensation, String postingDetails,
        String technologyUsed) {
        this.id = id;
        this.position = position;
        this.compensation = compensation;
        this.postingDetails = postingDetails;
        this.technologyUsed = technologyUsed;
    }
}
