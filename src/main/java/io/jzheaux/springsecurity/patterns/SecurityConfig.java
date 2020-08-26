package io.jzheaux.springsecurity.patterns;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new CustomJwtGrantedAuthoritiesConverter());
        return converter;
    }

    public static class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            Collection<GrantedAuthority> grantedAuthorities = jwtGrantedAuthoritiesConverter.convert(jwt);
            Collection<GrantedAuthority> updatedGrantedAuthorities = new ArrayList<>(grantedAuthorities);
            Set<String> grantedAuthorityStrings = AuthorityUtils.authorityListToSet(grantedAuthorities);
            if (grantedAuthorityStrings.contains("SCOPE_aircraft.fly") || grantedAuthorityStrings.contains("SCOPE_boat.sail")){
                updatedGrantedAuthorities.add(new SimpleGrantedAuthority("captain"));
            }
            return updatedGrantedAuthorities;
        }
    }
}
