
package com.crypto.project.auth;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.crypto.project.auth.handler.CustomLogoutSuccessHandler;
import com.crypto.project.auth.handler.LoginSuccessHandler;
@EnableWebSecurity
@Configuration
@Order(1)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//    private AuthenticationEntryPointJwt authenticationEntryPointJwt;
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
	    return new CustomLogoutSuccessHandler();
	}
	
	@Autowired
    private LoginSuccessHandler loginSuccessHandler;
	
    private UserDetailsService userDetailsService;
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    public WebSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
      //  this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**", 
            "/resources/**"
    };
    
    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web)
    		throws Exception {
    	// TODO Auto-generated method stub
    	super.configure(web);
    	 web
         .ignoring()
         .antMatchers(HttpMethod.POST,"/users/signUp")
         .antMatchers(HttpMethod.POST,"/users/sendOTPThroughEmail")
         .antMatchers(HttpMethod.POST,"/users/fetchUser/{userId}")
         .antMatchers(HttpMethod.GET,"/users/checkOtp")
         .antMatchers(HttpMethod.POST,"/users/UpdPwd")
         .antMatchers(HttpMethod.POST,"/support/getInqury")
         
         
         .antMatchers(AUTH_WHITELIST);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println("track2");
    	http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/case/downloadRpt").permitAll().antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),authenticationSuccessHandler()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .logout()
                .logoutUrl("/doLogout")
                .logoutSuccessHandler(logoutSuccessHandler());
    }
    
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LoginSuccessHandler();
    }
//    @Bean
//    public AuthenticationEntryPointJwt getAuthenticationEntryPointJwt() {
//        return new AuthenticationEntryPointJwt();
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("track1");
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS"));
        //configuration.setExposedHeaders(Arrays.asList("header1", "header2"));
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", 
        		"Authorization","responseType","userNumId"));
        configuration.addExposedHeader("Authorization,UserRole, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With,Content-Disposition, userNumId");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}