package com.wingstop.tomeet.serviceImpl;

import com.wingstop.tomeet.constants.EnumContainer;
import com.wingstop.tomeet.dto.ResponseObject;
import com.wingstop.tomeet.dto.UserDetailsDto;
import com.wingstop.tomeet.model.LoginDetails;
import com.wingstop.tomeet.model.User;
import com.wingstop.tomeet.repository.LoginDetailsDao;
import com.wingstop.tomeet.repository.UserDetailsDao;
import com.wingstop.tomeet.service.UserDetailsService;
import com.wingstop.tomeet.util.JwtUtil;
import com.wingstop.tomeet.util.PassWordEncryption;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsDao userDetailsDao;
    private final LoginDetailsDao loginDetailsDao;
    private final JwtUtil jwtTokenUtil;
    private final  JpaUserDetailsService jpaUserDetailsService;

    @Override
    public ResponseObject saveUser(UserDetailsDto userDetailsDto) throws EmailException {
        User userDetails = new User();
        BeanUtils.copyProperties(userDetailsDto, userDetails);
        Optional<User> userData = userDetailsDao.findByUserName(userDetails.getUserName());
        if (userData.isEmpty()) {
            userDetails.setUserVerificationStatus(EnumContainer.UserVerificationStatus.PENDING);
            String uuid = UUID.nameUUIDFromBytes(userDetails.getUserName().getBytes()).toString();
            System.out.println("uuid" + uuid);
            userDetails.setToken(uuid);
            userDetails.setStatus(1);
            userDetails.setGender(EnumContainer.Gender.valueOf(userDetailsDto.getGender()));
            long userId = userDetailsDao.save(userDetails).getUserId();
            this.sendSimpleEmail(userDetails.getUserName(),uuid,userId);
            log.info("userDetails",userDetails);
            return new ResponseObject(200, "User has successfully created please check email for registration link ", "success", null);

        } else {
            return new ResponseObject(500, "User has already registered with this mail id  ", "success", null);

        }

    }

    @Override
    public ResponseObject forgotPin(String userName) throws EmailException {
        Optional<User> userData = userDetailsDao.findByUserName(userName);
        if (userData.isEmpty()) {
            return new ResponseObject(500, "User not registered", "success", null);
        } else {
            userData.get().setUserVerificationStatus(EnumContainer.UserVerificationStatus.PENDING);
            String uuid = UUID.nameUUIDFromBytes(userData.get().getUserName().getBytes()).toString();
            System.out.println("uuid" + uuid);
            userData.get().setToken(uuid);
            long userId = userDetailsDao.save(userData.get()).getUserId();
            this.sendSimpleEmail(userData.get().getUserName(),uuid,userId);
            log.info("userDetails",userData.get());
            return new ResponseObject(200, "Please check email for forgot password link ", "success", null);
        }

    }

    @Override
    public ResponseObject verifyToken(String verifyToken, Long userId) {
        Optional<User> userData = userDetailsDao.findById(userId);
        if (userData.isPresent()) {
            User userDetails = userData.get();
            boolean isToken = userDetails.getToken().equalsIgnoreCase(verifyToken);
            if (isToken) {
                userDetails.setUserVerificationStatus(EnumContainer.UserVerificationStatus.COMPLETED);
                userDetailsDao.save(userDetails);

                return new ResponseObject(200, "Token has matched successfully ", "success", userData.get());
            } else
                return new ResponseObject(500, "Invalid Token ", "failed", null);
        } else {
            return new ResponseObject(500, "User is not present please register the user ", "failed", null);
        }
    }

    @Override
    public ResponseObject setMpin(String userName, String password) {
        Optional<User> userData = userDetailsDao.findByUserName(userName);
        if (userData.isPresent()) {
            LoginDetails loginDetails = new LoginDetails();
            loginDetails.setUser(userData.get());
            loginDetails.setPassword(new PassWordEncryption().getPasswordEncription(password));
            loginDetailsDao.save(loginDetails);
            return new ResponseObject(200, "Mpin has set successfully ", "success", null);
        } else {
            return new ResponseObject(500, "User is not present please register the user ", "failed", null);
        }

    }

    @Override
    @Transactional
    public ResponseObject login(String userName, String password) {
        Optional<User> userData = userDetailsDao.findByUserName(userName);
        Map<String, Object> map = new HashMap();
        if (userData.isPresent()) {
            Optional<LoginDetails> loginData = loginDetailsDao.findByUser(userData.get());
            if (loginData.isPresent()) {
                PassWordEncryption pe = new PassWordEncryption();
                boolean isMatched = pe.getpasswordDecode(password, loginData.get().getPassword());
                log.info("isMatched",isMatched);
                if (isMatched) {
                    loginData.get().setLoggedInTime(new Date());
                    loginData.get().setStatus(1);
                    loginDetailsDao.save(loginData.get());
                    UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(userName);
                    String jwt =jwtTokenUtil.generateToken(userDetails);
                    map.put("userDetails",userData.get());
                    map.put("jwt",jwt);
                    return new ResponseObject(200, "User Has Loggedin successfully ", "success",map);
                } else {
                    return new ResponseObject(500, "Please check you password ", "failed", null);
                }
            } else {
                return new ResponseObject(500, "Please Set Your MPIN ", "failed", null);

            }
        }
       else {
           return new ResponseObject(500, "Please Register the user or check the username", "failed", null);

            }
        }

    @Override
    public List<UserDetailsDto> getListOfUsers() {

        return userDetailsDao.findAllData();
    }




    public String sendSimpleEmail(String empEmailId, String token,Long userId) throws EmailException {
        String password = "gsftkhbvwiowobhl";
        String subject = "Verification Link For ToMeet Registration";
        String plainTextMessage = "Click the link below For Verification:\n" +
                "https://tomeet.wing.com/CreationPassword?token=" + token + "&userId=" + userId;

//        String htmlMessage = "Click the link below For Verification:<br>" +
//                "<a href=\"https://tomeet.wing.com/CreationPassword?token=" + token +"&userId="+userId+">Verification Link For ToMeet</a>";

        Email email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("tejaprathap999@gmail.com", password));
        email.setStartTLSRequired(true); // Use STARTTLS
        email.setFrom("tejaprathap999@gmail.com");
        email.setSubject(subject);
        ((HtmlEmail) email).setHtmlMsg(plainTextMessage);

        email.addTo(empEmailId);

        email.send();

        log.info("Mail Sent Successfully........");
        return "Mail Sent Successfully";
    }
}
