package com.biz.sec.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
// javax.persistence로 import 해야함
// hibernate를 위한 설정
@Entity(name = "tbl_users")
public class UserVO implements UserDetails{

	/*
	 * JPA의 Entity를 선언할 때 
	 * id 칼럼(필드변수)는 반드시 class type으로 선언하자
	 * 
	 * 그렇지 않으면 JPA의 자동 method가 작동되지 않는다.
	 * int => Integer
	 * long => Long
	 */
	// mySQL은 strategy = GenerationType를 Identity로 해줘야함
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 이 속성이 빠지면 오류가 나는 경우가 발생해서 넣어줌(정확히 어떤 오류인지는 파악 불가)
	@Column(name = "id", columnDefinition = "bigint")
	private Long id;
	
	// length 기본값은 255개(설정 따로 안하면 기본값임)
	@Column(name="username", columnDefinition = "varchar(64)", unique = true, length = 64)
	private String username;
	private String password;
	
	/*
	 * 아래 설정은 spring security를 위해 implements한 UserDetails 설정을 위해 꼭 존재해야할 변수들임
	 */
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	// @Transient : 현재 칼럼을 DB의 칼럼으로 생성하지 말라는 설정(collection은 dataType이 없어서 오류남)
	// authority와 테이블 연계를 위해 필요한 변수
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	
	/*
	 * 아래 변수들은 선택사항
	 */
	private String email;
	private String phone;
	private String address;
	
	
	/*
	 * masterTable의 데이터를 모두 select한 후 
	 * 바로 Slave table을 selet하지 않고
	 * slave table의 데이터가 필요한 시점에 
	 * select를 수행하도록 하는 지연 옵션
	 */
	@OneToMany(mappedBy = "userVO", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<UserRole> userRoles;
	
}
