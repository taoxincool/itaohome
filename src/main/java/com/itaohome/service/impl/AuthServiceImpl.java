package com.itaohome.service.impl;

import com.itaohome.model.TbRole;
import com.itaohome.model.TbUser;
import com.itaohome.repository.RoleRepository;
import com.itaohome.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Mr tao on 2015/1/26.
 */
@Service(value = "authServiceImpl")
public class AuthServiceImpl implements UserDetailsService {

    private final static Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUser tbUser = userRepository.findByUsername(username);
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        if (tbUser == null) {
            throw new UsernameNotFoundException(username);
        } else {
            if (tbUser.getRoleId() != null) {
                TbRole tbRole = roleRepository.findById(tbUser.getRoleId());
                if (tbRole != null) {
                    authorities.add(new SimpleGrantedAuthority(tbRole.getName()));
                    //authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                }
            }
        }
        UserDetails user = new User(username, tbUser.getPassword(), authorities);
        return user;
    }
}
