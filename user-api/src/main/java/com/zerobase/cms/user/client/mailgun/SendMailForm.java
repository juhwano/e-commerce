package com.zerobase.cms.user.client.mailgun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드 매개변수 생성자 자동 생성
@Getter // Getter 메서드 자동 생성
@Builder // 빌더 패턴 사용할 수 있게 해줌. 객체 생성시 가독성이 좋아진다.
@Data // 객체의 일반적인 메서드 자동생성(Getter, Setter, toString, equals, hashCode)
public class SendMailForm {
	private String from;
	private String to;
	private String subject;
	private String text;


}
