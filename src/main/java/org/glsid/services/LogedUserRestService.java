package org.glsid.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(name = "Loged User API", description = "Get informations about Loged User", stage=ApiStage.RC)
public class LogedUserRestService {
	
	
	@Secured(value={"ROLE_ADMIN","ROLE_CLIENT","ROLE_EMPLOYE"})
	@RequestMapping(value="/getLogedUser",method=RequestMethod.GET)
	@ApiMethod(description="Get Loged User")
	public Map<String,Object> getLogedUser(HttpServletRequest httpServletRequest){
		HttpSession httpSession=httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext)
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=securityContext.getAuthentication().getName();
		List<String> roles=new ArrayList<>();
		for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
			 roles.add(ga.getAuthority());
			}
		Map<String,Object> params= new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return params;
	
	}
	

}
