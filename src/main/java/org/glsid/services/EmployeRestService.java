package org.glsid.services;

import java.util.List;

import org.glsid.entities.Employe;
import org.glsid.metier.EmployeMetier;
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
@Api(name = "Employee API", description = "Provides a list of methods that manage Employees", stage=ApiStage.RC)
public class EmployeRestService {

	
	@Autowired
	private EmployeMetier employeMetier;

	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/employes",method=RequestMethod.POST)
	@ApiMethod(description="Create an Employee and save it to the database")
	public Employe saveEmploye(@RequestBody Employe e) {
		return employeMetier.saveEmploye(e);
	}

	@Secured(value={"ROLE_ADMIN","ROLE_EMPLOYE"})
	@RequestMapping(value="/employes",method=RequestMethod.GET)
	@ApiMethod(description="Get all Employees from the database")
	public List<Employe> listEmploye() {
		return employeMetier.listEmploye();
	}
}
