package kr.co.zzimcar.config;

import kr.co.zzimcar.domain.test.MemberRole;
import kr.co.zzimcar.domain.test.Memberr;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ZerockSecurityUser extends User {

	private static final String ROLE_PREFIX = "ROLE_";

	private Memberr member;

	public ZerockSecurityUser(Memberr member) {

		super(member.getUid(), member.getUpw(), makeGrantedAuthority(member.getRoles()));

		this.member = member;

	}

	// roles를 리스트 형태로 저장 리턴
	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {

		List<GrantedAuthority> list = new ArrayList<>();

		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));

		return list;
	}

}
