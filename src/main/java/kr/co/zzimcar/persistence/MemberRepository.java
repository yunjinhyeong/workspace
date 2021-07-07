package kr.co.zzimcar.persistence;

import kr.co.zzimcar.domain.member.MemberDto;
import kr.co.zzimcar.domain.test.Memberr;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Memberr, String> {


}
