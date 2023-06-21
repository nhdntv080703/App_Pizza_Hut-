package com.example.demo.config;

import com.example.demo.filter.AuthTokenFilter;
import com.example.demo.service.impl.UserDetailServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint authEntryPointJWT;
    private final UserDetailServiceImp userDetailServiceImp;

    public WebSecurityConfig(AuthenticationEntryPoint authEntryPointJWT, UserDetailServiceImp userDetailServiceImp) {
        this.authEntryPointJWT = authEntryPointJWT;
        this.userDetailServiceImp = userDetailServiceImp;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImp();
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(authEntryPointJWT).and()
                .authorizeRequests()
                .antMatchers("/api/product/one_product", "/api/product/product_by_name").hasAuthority("ROLE_USER")
                .antMatchers( "/api/product/**", "api/category/**", "api/user?**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/user/login", "/api/user/create-user").permitAll()
                .anyRequest().permitAll()
                .and()
                //UsernamePasswordAuthenticationFilter : bộ lọc mặc định của Spring Boot
                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class)//Giai thich
                .csrf().disable()//1 loại tấn công bảo mật sử dụng yêu cầu từ trang web khác
                .cors().disable();//Chính sách bảo mật trong tình duyệt web kiểm soat chia sẻ tài nguyên giữua các nguôn

    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //tạo 1 đối tượng UserDetailService và quản lý nó trong SpringContext

    //Cấu hình AuthenticationManager xử lý quá trình authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //không cần truyền cả 2 cái như này, vi gọi hàm authenticationProvider thì trong hàm đã được thiết lập rồi
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailServiceImp);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception{
//        web.ignoring().antMatchers("/v2/api-docs",
//                                   "'configuration/ui",
//                                   "swagger-resources/**",
//                                   "configuration/security",
//                                   "swagger-ui.html",
//                                   "webjars/**");
//    }

    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/h2-console/**", "/favicon.ico")
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**");
    }
}
