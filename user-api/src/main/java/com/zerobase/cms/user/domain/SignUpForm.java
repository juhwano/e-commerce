package com.zerobase.cms.user.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // Getter만 만들면 테스트 given when에서 인젝션 주입이 어렵다.
@Builder // 그래서 @Builder, @NoArgs, @AllArgs를 만들어주지만, 사실 필드를 모킹해서 작성하는 게 정석
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
	private String email;
	private String name;
	private String password;
	private LocalDateTime birth;
	private String phone;


}
