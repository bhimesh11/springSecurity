package com.example.demo.dto;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import static com.example.demo.security.applicationUserEnumRole.*;


@Repository("fake") //simplly tells this class needs to be intlialized

public class FakeApplicationUserDaoService implements ApplicationUserDAO
{
    private  final PasswordEncoder passwordEncoder;
    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private List<ApplicationUser> getApplicationUsers()
    {

        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser( "bhimesh",
                        passwordEncoder.encode("Easy@7044"),
                        STUDENT.getGuranteedAuthorties(),
                        true,
                        true,
                        true,
                        true),

                        new ApplicationUser(
                        "dihith",
                        passwordEncoder.encode("password"),
                        ADMIN.getGuranteedAuthorties(),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        "chinni",
                        passwordEncoder.encode("password"),
                        ADMIN.getGuranteedAuthorties(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;



    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserBYUserName(String UserName) {

        System.out.println("Hi");

        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> UserName.equals(applicationUser.getUsername()))
                .findFirst();

    }

//    @Override
//    public Optional<ApplicationUser> selectApplicationUserBYUserName(String UserName) {
//        return Optional.empty();
//    }
}
