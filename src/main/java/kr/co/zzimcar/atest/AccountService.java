package kr.co.zzimcar.atest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kr.co.zzimcar.dao.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

  @Autowired
  MemberDao accounts;

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    log.info("## loadUserByUsername ##");

    Account account = accounts.findById(username);

    if( account == null ) {
      throw new UsernameNotFoundException(username);
    }

    account.setAuthorities(getAuthorities(username));

    return account;

  }

  public Collection<GrantedAuthority> getAuthorities(String username) {

    List<String> string_authorities = accounts.findauthoritiesbyid(username);

    if( string_authorities == null ) {
      throw new UsernameNotFoundException(username);
    }

    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    for (String authority : string_authorities) {
      authorities.add(new SimpleGrantedAuthority(authority));
    }

    return authorities;

  }
}