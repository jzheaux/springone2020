package io.jzheaux.springsecurity.patterns;

import java.util.Set;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	public String moveTo(String location) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (authorities.contains("captain")) {
			return "You have moved to " + location;
		}
		throw new AccessDeniedException("You will not move today");
	}
}
