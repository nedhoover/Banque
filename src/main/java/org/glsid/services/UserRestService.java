package org.glsid.services;

import java.util.List;

import org.glsid.dao.RoleRepository;
import org.glsid.dao.UserRepository;
import org.glsid.entities.Role;
import org.glsid.entities.User;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(name = "User and Role API", description = "Provides a list of methods that manage Users and Roles", stage=ApiStage.RC)
@Secured(value={"ROLE_ADMIN"})
public class UserRestService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	
	@RequestMapping(value="/addUser",  method=RequestMethod.POST)
	@ApiMethod(description="Create a User and save it to the database")
	public User save(@RequestBody User u){
		return userRepository.save(u);
	}
	
	@RequestMapping(value="/findUsers", method=RequestMethod.GET)
	@ApiMethod(description="Get all Users from the database")
	public List<User> findAll(){
		return userRepository.findAll();
	}

	@RequestMapping(value="/addRole", method=RequestMethod.POST)
	@ApiMethod(description="Create a Role and save it to the database")
	public Role save(@RequestBody Role r){
		return roleRepository.save(r);
	}
	
	@RequestMapping(value="/findRoles", method=RequestMethod.GET)
	@ApiMethod(description="Get all Roles from the database")
	public List<Role> findRoles(){
		return roleRepository.findAll();
	}
	
	@RequestMapping(value="/addRoleToUser", method=RequestMethod.POST)
	@ApiMethod(description="Add Role To a User")
	public User addRoleToUser(
			@RequestBody   String username, 
			@RequestBody   String role){
		  User u=userRepository.findOne(username);
		  Role r=roleRepository.findOne(role);
		u.getRoles().add(r);
		userRepository.save(u);
		return u;		
	}

	
	

	
	
}
