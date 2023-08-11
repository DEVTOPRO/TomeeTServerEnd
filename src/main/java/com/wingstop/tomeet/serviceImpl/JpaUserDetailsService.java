package com.wingstop.tomeet.serviceImpl;




import com.wingstop.tomeet.Entity.MyUserDetails;
import com.wingstop.tomeet.model.User;
import com.wingstop.tomeet.repository.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOnboardingDetails = userDetailsDao.findByUserName(userName);
        userOnboardingDetails.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return userOnboardingDetails.map(MyUserDetails::new).get();

    }

}
