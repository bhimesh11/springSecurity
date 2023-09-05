package com.example.demo.security;


import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class applicationSecurityConfig extends WebSecurityConfigurerAdapter
{

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public applicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()//pending
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*")
                .permitAll()
                .antMatchers("/api/**").hasRole(applicationUserEnumRole.STUDENT.name()) // role based authentication
                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyAuthority(applicationUserPermissions.COURSE_READ.getPermission())
                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAnyAuthority(applicationUserPermissions.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAnyAuthority(applicationUserPermissions.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAnyAuthority(applicationUserPermissions.COURSE_WRITE.getPermission())
                .antMatchers("/management/api/**").hasAnyRole(applicationUserEnumRole.ADMIN.name(),applicationUserEnumRole.AdMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails bhimeshUser = User.builder()
                .username("bhimesh")
                .password(passwordEncoder.encode("Easy@7044"))
                //.roles(String.valueOf(applicationUserEnumRole.STUDENT)) //ROLE_STUDENT
                .authorities(applicationUserEnumRole.STUDENT.getGuranteedAuthorties())
                .build();

     UserDetails dihithUser =
             User.builder()
                .username("Dihith")
                .password(passwordEncoder.encode("dihith"))
               // .roles(String.valueOf(applicationUserEnumRole.ADMIN)) //ROLE_ADMIN
                     .authorities(applicationUserEnumRole.ADMIN.getGuranteedAuthorties())
                     .build();

        UserDetails chinniUser =
                User.builder()
                        .username("chinni")
                        .password(passwordEncoder.encode("chinni"))
                        //.roles(String.valueOf(applicationUserEnumRole.AdMINTRAINEE)) //ROLE_ADMINTRAINEE
                        .authorities(applicationUserEnumRole.AdMINTRAINEE.getGuranteedAuthorties())
                        .build();

        return new InMemoryUserDetailsManager(bhimeshUser
                ,dihithUser,chinniUser);

    }
}
