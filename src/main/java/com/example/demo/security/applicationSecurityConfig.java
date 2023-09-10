package com.example.demo.security;


import com.example.demo.JWT.JwtConfig;
import com.example.demo.JWT.JwtTokenVerifer;
import com.example.demo.JWT.JwtUserNameAndPasswordAuthenticationFilter;
import com.example.demo.dto.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class applicationSecurityConfig extends WebSecurityConfigurerAdapter
{


    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final PasswordEncoder passwordEncoder;
   private final ApplicationUserService applicationUserService;
    @Autowired
    public applicationSecurityConfig(SecretKey secretKey, JwtConfig jwtConfig, PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.passwordEncoder = passwordEncoder;
       // this.applicationUserService = applicationUserService;
        this.applicationUserService = applicationUserService;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
              //  .and()
                .sessionManagement().
                           sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUserNameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig,secretKey ))
                .addFilterAfter(new JwtTokenVerifer(secretKey, jwtConfig),JwtUserNameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*")
                .permitAll()
                .antMatchers("/api/**").hasRole(applicationUserEnumRole.STUDENT.name())
               // .antMatchers("/api/**").hasAnyRole(applicationUserEnumRole.STUDENT.name())// role based authentication
              // .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyAuthority(applicationUserPermissions.COURSE_READ.getPermission())
             //  .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAnyAuthority(applicationUserPermissions.COURSE_WRITE.getPermission())
             // .antMatchers(HttpMethod.POST,"/management/api/**").hasAnyAuthority(applicationUserPermissions.COURSE_WRITE.getPermission())
               //.antMatchers(HttpMethod.PUT,"/management/api/**").hasAnyAuthority(applicationUserPermissions.COURSE_WRITE.getPermission())
             //  .antMatchers("/management/api/**").hasAnyRole(applicationUserEnumRole.ADMIN.name(),applicationUserEnumRole.AdMINTRAINEE.name())
                .anyRequest()
                .authenticated();


                //used for formlogin authentivcationMethod
//                 .and()
//                .formLogin().
//                loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/courses",true)
//                .passwordParameter("password")
//                .usernameParameter("username")
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                .key("somethingSecured")
//                .rememberMeParameter("remember-me")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID","XSRF-TOKEN","remember-me")
//                .logoutSuccessUrl("/login");
               // .httpBasic(); //used for basic auth
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
public DaoAuthenticationProvider daoAuthenticationProvider()

    {
        System.out.println("Set");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        System.out.println(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        System.out.println(applicationUserService.toString());
        return provider;
    }
//}
//
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails bhimeshUser = User.builder()
//                .username("bhimesh")
//                .password(passwordEncoder.encode("Easy@7044"))
//                //.roles(String.valueOf(applicationUserEnumRole.STUDENT)) //ROLE_STUDENT
//                .authorities(applicationUserEnumRole.STUDENT.getGuranteedAuthorties())
//                .build();
//
//     UserDetails dihithUser =
//             User.builder()
//                .username("dihith")
//                .password(passwordEncoder.encode("dihith"))
//               // .roles(String.valueOf(applicationUserEnumRole.ADMIN)) //ROLE_ADMIN
//                     .authorities(applicationUserEnumRole.ADMIN.getGuranteedAuthorties())
//                     .build();
//
//        UserDetails chinniUser =
//                User.builder()
//                        .username("chinni")
//                        .password(passwordEncoder.encode("chinni"))
//                        //.roles(String.valueOf(applicationUserEnumRole.AdMINTRAINEE)) //ROLE_ADMINTRAINEE
//                        .authorities(applicationUserEnumRole.ADMINTRAINEE.getGuranteedAuthorties())
//                        .build();
//
//        return new InMemoryUserDetailsManager(bhimeshUser
//                ,dihithUser,chinniUser);
//
//    }
}


