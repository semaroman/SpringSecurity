import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Roman")
                .password("{noop}password")
                .authorities("closed_endpoint");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/opened_endpoint")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/closed_endpoint")
                .hasAuthority("closed_endpoint")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}