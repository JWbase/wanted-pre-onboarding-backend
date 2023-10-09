package com.wnated.findjob.domain.company;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Country {

    KOREA("한국"),
    TAIWAN("대만"),
    JAPAN("일본"),
    USA("미국"),
    ETC("기타");

    private final String text;
}
