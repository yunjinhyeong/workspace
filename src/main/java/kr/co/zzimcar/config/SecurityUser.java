package kr.co.zzimcar.config;

import kr.co.zzimcar.domain.member.MemberDto;
import kr.co.zzimcar.domain.member.MemberRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.ArrayList;
import java.util.List;

//@Getter
//public class SecurityUser extends User {
//
//	private static final String ROLE_PREFIX = "ROLE_";
//
//	private MemberDto member;
//
//	public SecurityUser(MemberDto member) {
//
//		super(member.getId(), member.getPassword(), makeGrantedAuthority(member.getRoles()));
//		this.member = member;
//	}
//
//	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {
//
//		List<GrantedAuthority> list = new ArrayList<>();
//		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRole())));
//		return list;
//	}
//
//}
