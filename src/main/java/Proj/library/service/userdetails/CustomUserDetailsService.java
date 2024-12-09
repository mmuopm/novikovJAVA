package Proj.library.service.userdetails;

import Proj.library.constants.UserRolesConstants;
import Proj.library.model.Role;
import Proj.library.repository.UserRepository;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {
    private final UserRepository userRepository;

    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUserName)) {
            return new CustomUserDetails(null, username, adminPassword, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        } else {
            User user = userRepository.findUserByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority(user.getRole().getId() == 1L
                    ? "ROLE_" + UserRolesConstants.USER
                    : "ROLE_" + UserRolesConstants.LIBRARIAN));

            return new CustomUserDetailsService(user.getId().intValue(),username, user.getPassword(),authorities);
        }
    }
}
