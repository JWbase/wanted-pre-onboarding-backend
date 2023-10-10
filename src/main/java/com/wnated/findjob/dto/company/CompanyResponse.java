package com.wnated.findjob.dto.company;

import com.wnated.findjob.domain.company.City;
import com.wnated.findjob.domain.company.Company;
import com.wnated.findjob.domain.company.Country;
import lombok.Getter;

@Getter
public class CompanyResponse {

    private String name;

    private Country country;

    private City city;

    public CompanyResponse(String name, Country country, City city) {
        this.name = name;
        this.country = country;
        this.city = city;
    }

    public static CompanyResponse of(Company company) {
        return new CompanyResponse(
            company.getName(),
            company.getCountry(),
            company.getCity());
    }
}
