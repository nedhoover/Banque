package org.glsid.services;

import org.glsid.entities.Compte;
import org.glsid.metier.CompteMetier;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(name = "Account API", description = "Provides a list of methods that manage Accounts", stage=ApiStage.RC)
public class CompteRestService {
	@Autowired
	private CompteMetier compteMetier;

	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/comptes" ,method=RequestMethod.POST)
	@ApiMethod(description="Create an account and save it to the database")
	public Compte saveCompte(@RequestBody Compte cp) {
		return compteMetier.saveCompte(cp);
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/comptes/{code}" ,method=RequestMethod.GET)
	@ApiMethod(description="Get all accounts where the code is equal than the provided value ")
	public Compte getCompte(@ApiPathParam(name = "code")@PathVariable String code) {
		return compteMetier.getCompte(code);
	}
}
