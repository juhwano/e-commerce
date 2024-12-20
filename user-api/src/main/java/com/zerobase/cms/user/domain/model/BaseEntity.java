package com.zerobase.cms.user.domain.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 목적: MappedSuperclass 어노테이션은 클래스가 JPA 엔티티로 매핑되는 것을 방지하며, 다른 엔티티 클래스들이 상속받을 수 있도록 합니다.
 * 동작: BaseEntity 클래스 자체는 데이터베이스 테이블과 매핑되지 않지만, 이 클래스를 상속받은 자식 클래스는 BaseEntity의 속성(createdAt, modifiedAt)을 포함하여 테이블에 매핑됩니다.
 *
 * 목적: EntityListeners 어노테이션은 엔티티에서 Auditing 기능을 활성화하기 위한 설정입니다.
 * 동작: AuditingEntityListener를 통해 Spring Data JPA는 엔티티의 생성 및 수정 시점에 자동으로 @CreatedDate와 @LastModifiedDate가 적용되도록 합니다. AuditingEntityListener는 엔티티의 변경사항을 추적하고, 특정 필드(예: createdAt, modifiedAt)에 값을 자동으로 채웁니다.
 *
 */
@Getter
@MappedSuperclass
@EntityListeners(value={AuditingEntityListener.class})
public class BaseEntity { // 많이 사용하는 속성

	/**
	 * 목적: CreatedDate 어노테이션은 엔티티가 처음 생성될 때, 해당 필드에 자동으로 생성 일자를 기록하도록 지정합니다.
	 * 동작: 엔티티가 처음 저장될 때 createdAt 필드에 현재 날짜가 자동으로 세팅됩니다. Spring은 @CreatedDate가 붙은 필드를 자동으로 관리하며, 이를 위해 AuditingEntityListener가 필요합니다.
	 */
	@CreatedDate
	private LocalDate createdAt;
	/**
	 * 목적: LastModifiedDate 어노테이션은 엔티티가 수정될 때, 해당 필드에 자동으로 수정 일자를 기록하도록 지정합니다.
	 * 동작: 엔티티가 수정될 때마다 modifiedAt 필드에 현재 날짜가 자동으로 세팅됩니다. @LastModifiedDate가 붙은 필드 역시 AuditingEntityListener를 통해 관리됩니다.
	 */
	@LastModifiedDate
	private LocalDate modifiedAt;
}

/**
 * 동작 원리 (전체적으로)
 * BaseEntity 클래스는 @MappedSuperclass로 정의되어 있으며, createdAt과 modifiedAt 속성을 자식 클래스가 상속받도록 합니다.
 * @EntityListeners(AuditingEntityListener.class)는 AuditingEntityListener가 엔티티의 생성 및 수정 시간을 추적하도록 합니다.
 * @CreatedDate는 엔티티가 처음 생성될 때 createdAt 필드에 자동으로 날짜를 넣고, @LastModifiedDate는 엔티티가 수정될 때 modifiedAt 필드를 자동으로 업데이트합니다.
 */