package com.biz.sec.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
// 1:n 관계에서 toString을 사용하면 내부적으로 method가 생성되어 계속 돌고있기 때문에
// stackOverFlow Exception이 발생할 수 있으므로 toString은 빼야함
// @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 이 속성이 빠지면 오류가 나는 경우가 발생해서 넣어줌(정확히 어떤 오류인지는 파악 불가)
	@Column(name = "id", columnDefinition = "bigint")
	private Long id;
	
	// length 기본값은 255개(설정 따로 안하면 기본값임)
	// length 기본값은 255개(설정 따로 안하면 기본값임)
	@Column(name="username", columnDefinition = "varchar(64)", length = 64)
	private String username;
	
	private String roleName;

	/*
	 * JPA에서 1:n 관계를 설정할때는 연결된 클래스에 각각 
	 * OneToMany와 ManyToOne Annotaion을 설정해줘야함
	 */
	@ManyToOne
	@JoinColumn(name="username", referencedColumnName = "username", insertable = false, updatable = false)
	private UserVO userVO;
	
}
