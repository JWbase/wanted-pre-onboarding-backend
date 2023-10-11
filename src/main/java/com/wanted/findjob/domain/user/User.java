package com.wanted.findjob.domain.user;

import com.wanted.findjob.domain.jobposting.JobPosting;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplyHistory> applyHistory = new ArrayList<>();

    public void applyCompany(JobPosting jobPosting) {
        this.applyHistory.add(new ApplyHistory(this, jobPosting));
    }

    @Builder
    private User(Long id, String name, List<ApplyHistory> applyHistory) {
        this.id = id;
        this.name = name;
        this.applyHistory = applyHistory;
    }

}