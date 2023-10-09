package com.wnated.findjob.domain.jobposting;

import com.wnated.findjob.domain.company.Company;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "job_posting")
@Entity
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_posting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "posting_position")
    private String position;

    private int compensation; //채용보상금

    @Column(name = "posting_details")
    private String postingDetails;

    @Column(name = "technology_used")
    private String technologyUsed;

    @Builder
    private JobPosting(Company company, String position, int compensation, String postingDetails,
        String technologyUsed) {
        this.company = company;
        this.position = position;
        this.compensation = compensation;
        this.postingDetails = postingDetails;
        this.technologyUsed = technologyUsed;
    }
}
