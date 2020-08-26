package io.jzheaux.springsecurity.patterns;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	@PreAuthorize("hasAuthority('captain')")
	public String moveTo(String location) {
		return "You have moved to " + location;
	}
}
