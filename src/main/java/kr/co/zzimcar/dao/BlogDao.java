package kr.co.zzimcar.dao;


import kr.co.zzimcar.domain.blog.BlogDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BlogDao {
  void save(BlogDto blogDto);
}