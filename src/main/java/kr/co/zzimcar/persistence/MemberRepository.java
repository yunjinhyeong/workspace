package kr.co.zzimcar.persistence;

import kr.co.zzimcar.domain.member.MemberDto;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberDto, String> {

	
}
