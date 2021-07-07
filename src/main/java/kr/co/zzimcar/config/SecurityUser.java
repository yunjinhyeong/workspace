package kr.co.zzimcar.config;

import kr.co.zzimcar.domain.member.MemberDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SecurityUser extends User {

	private static final String ROLE_PREFIX = "ROLE_";

	private MemberDto memberDto;

	private List<GrantedAuthority> list;

	public SecurityUser(MemberDto memberDto) {

		super(memberDto.getId(), memberDto.getPassword(), makeGrantedAuthority(memberDto.getName()));

		this.memberDto = memberDto;

	}

	// roles를 리스트 형태로 저장 리턴
	private static List<GrantedAuthority> makeGrantedAuthority(String name) {

		List<GrantedAuthority> list = new ArrayList<>();

		list.add(new SimpleGrantedAuthority(ROLE_PREFIX + name));

		return list;
	}

}
