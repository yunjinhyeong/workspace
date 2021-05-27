package kr.co.zzimcar.serviceImpl;

import kr.co.zzimcar.dao.BlogDao;
import kr.co.zzimcar.dto.BlogDto;
import kr.co.zzimcar.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

  private final BlogDao blogDao;

  @Override
  public void review(BlogDto blogDto) {
    blogDao.save(blogDto);
  }
}
