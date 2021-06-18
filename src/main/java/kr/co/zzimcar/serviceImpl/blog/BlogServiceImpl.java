package kr.co.zzimcar.serviceImpl.blog;

import kr.co.zzimcar.dao.BlogDao;
import kr.co.zzimcar.domain.blog.BlogDto;
import kr.co.zzimcar.service.blog.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

  private final BlogDao blogDao;

  @Override
  public void review(BlogDto blogDto) {
    System.out.println("2");
    blogDao.save(blogDto);
  }
}
