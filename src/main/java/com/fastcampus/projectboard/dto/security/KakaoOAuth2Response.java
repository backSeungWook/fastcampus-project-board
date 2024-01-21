package com.fastcampus.projectboard.dto.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/*
:: 카카오 인증 정보 정의
  카카오 인증 API 의 결과값을 효율적으로 사용할 수 있도록
  자바 record로 표현하고, 동작을 확인할 수 있는 테스트 작성
 */
@SuppressWarnings("unchecked") // TODO: Map -> Object 변환 로직이 있어 제네릭 타입 캐스팅 문제를 무시한다. 더 좋은 방법이 있다면 고려할 수 있음.
public record KakaoOAuth2Response(
        Long id,
        LocalDateTime connectedAt,
        Map<String, Object> properties,
        KakaoAccount kakaoAccount
) {
    public record KakaoAccount(
            Boolean profileNicknameNeedsAgreement,
            Profile profile,
            Boolean hasEmail,
            Boolean emailNeedsAgreement,
            Boolean isEmailValid,
            Boolean isEmailVerified,
            String email
    ) {
        public record Profile(String nickname) {
            public static Profile from(Map<String, Object> attributes) {
                return new Profile(String.valueOf(attributes.get("nickname")));
            }
        }

        public static KakaoAccount from(Map<String, Object> attributes) {
            return new KakaoAccount(
                    Boolean.valueOf(String.valueOf(attributes.get("profile_nickname_needs_agreement"))),
                    Profile.from((Map<String, Object>) attributes.get("profile")),
                    Boolean.valueOf(String.valueOf(attributes.get("has_email"))),
                    Boolean.valueOf(String.valueOf(attributes.get("email_needs_agreement"))),
                    Boolean.valueOf(String.valueOf(attributes.get("is_email_valid"))),
                    Boolean.valueOf(String.valueOf(attributes.get("is_email_verified"))),
                    String.valueOf(attributes.get("email"))
            );
        }

        public String nickname() { return this.profile().nickname(); }
    }

    public static KakaoOAuth2Response from(Map<String, Object> attributes) {
        return new KakaoOAuth2Response(
                Long.valueOf(String.valueOf(attributes.get("id"))),
                LocalDateTime.parse(
                        String.valueOf(attributes.get("connected_at")),
                        DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault())
                ),
                (Map<String, Object>) attributes.get("properties"),
                KakaoAccount.from((Map<String, Object>) attributes.get("kakao_account"))
        );
    }

    public String email() { return this.kakaoAccount().email(); }
    public String nickname() { return this.kakaoAccount().nickname(); }
}