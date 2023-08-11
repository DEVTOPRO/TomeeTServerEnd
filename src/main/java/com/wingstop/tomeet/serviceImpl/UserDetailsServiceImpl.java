package com.wingstop.tomeet.serviceImpl;

import com.wingstop.tomeet.constants.EnumContainer;
import com.wingstop.tomeet.dto.ResponseObject;
import com.wingstop.tomeet.dto.UserDetailsDto;
import com.wingstop.tomeet.model.User;
import com.wingstop.tomeet.repository.UserDetailsDao;
import com.wingstop.tomeet.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsDao userDetailsDao;
    @Override
    public ResponseObject saveUser(UserDetailsDto userDetailsDto) throws EmailException {
        User userDetails = new User();
        BeanUtils.copyProperties(userDetailsDto,userDetails);
        Optional<User> userData = userDetailsDao.findByUserName(userDetails.getUserName());
        if(userData.isEmpty()) {
            userDetails.setUserVerificationStatus(EnumContainer.UserVerificationStatus.PENDING);
            String uuid = UUID.nameUUIDFromBytes(userDetails.getUserName().getBytes()).toString();
            System.out.println("uuid" + uuid);
            userDetails.setToken(uuid);
            this.sendSimpleEmail(userDetails.getUserName(), uuid);
            userDetailsDao.save(userDetails);
            return new ResponseObject(200, "User has successfully created please check email for registration link ", "success", null);

        }
        else{
            return new ResponseObject(500, "User has already registered with this mail id  ", "success", null);

        }

    }


    public String sendSimpleEmail(String empEmailId, String otp) throws EmailException {


        String password = "gsftkhbvwiowobhl";
        String subject = "Your Verificaion Link is";

        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("tejaprathap999@gmail.com", password));
        email.setSSLOnConnect(true);
        // email.setSSLOnConnect(auth);
        email.setFrom("tejaprathap999@gmail.com");
        email.setSubject(subject);
        email.setMsg(otp);

        email.addTo(empEmailId);

        email.send();

        return "Mail Sent Succesfully";
    }
}
