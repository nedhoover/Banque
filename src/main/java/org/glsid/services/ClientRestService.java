package org.glsid.services;

import java.util.List;


import org.glsid.entities.Client;
import org.glsid.metier.ClientMetier;
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
@Api(name = "Customer API", description = "Provides a list of methods that manage Customers", stage=ApiStage.RC)
public class ClientRestService {
	@Autowired
	private ClientMetier clientMetier;

	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/clients",method=RequestMethod.POST)
	@ApiMethod(description="Create a customer and save it to the database")
	public Client saveClient(@RequestBody Client c) {
		return clientMetier.saveClient(c);
	}
	

	
	
	
	
	@Secured(value={"ROLE_ADMIN","ROLE_CLIENT"})
	@RequestMapping(value="/clients",method=RequestMethod.GET)
	@ApiMethod(description="Get all customer from the database")
	public List<Client> listClient() {
		return clientMetier.listClient();
	}
	
}
