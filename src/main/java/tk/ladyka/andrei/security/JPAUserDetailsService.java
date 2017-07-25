package tk.ladyka.andrei.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.ladyka.andrei.jpa.domain.sec.User;
import tk.ladyka.andrei.jpa.domain.sec.UserAuthority;
import tk.ladyka.andrei.jpa.domain.sec.enums.Authority;
import tk.ladyka.andrei.jpa.repository.sec.UserAuthorityRepository;
import tk.ladyka.andrei.jpa.repository.sec.UserRepository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User secUser = userRepo.findByUsernameIgnoreCase(username);
        if (secUser == null) {
            throw new UsernameNotFoundException(username + " is an unknown username");
        }
        Set<GrantedAuthority> grantedAuthories = userAuthorityRepository.getUserAuthorities(secUser.getId())
                .stream()
                .map(UserAuthority::getAuthorityId)
                .map(Authority::findOne)
                .map(Authority::getCode)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new UserDetail(secUser.getUsername(), secUser.getPassword(), secUser.getEnabled(),
                (secUser.getAccountExpiration() == null || secUser.getAccountExpiration().isAfter(LocalDateTime.now())),
                (secUser.getCredentialsExpiration() == null || secUser.getCredentialsExpiration().isAfter(LocalDateTime.now())),
                !secUser.getLocked(), grantedAuthories);
    }
}
