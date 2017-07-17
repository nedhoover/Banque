package org.glsid.services;

import org.glsid.metier.OperationMetier;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(name = "Operation API", description = "Provides a list of methods that manage Operations", stage=ApiStage.RC)
public class OperationRestService {
	@Autowired
	private OperationMetier operationMetier;

	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/versement" ,method=RequestMethod.PUT)
	@ApiMethod(description="Versement")
	public boolean verser(
			@RequestParam @ApiQueryParam(name = "codeCompte", description = "The code of account") String codeCompte, 
			@RequestParam @ApiQueryParam(name = "montant", description = "The  amount") double montant,
			@RequestParam @ApiQueryParam(name = "codeEmp", description = "The code of Emp") Long codeEmp) {
		return operationMetier.verser(codeCompte, montant, codeEmp);
	}

	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/retrait" ,method=RequestMethod.PUT)
	@ApiMethod(description="Retrait") 
	public boolean retirer(
			@RequestParam @ApiQueryParam(name = "codeCompte", description = "The code of account") String codeCompte, 
			@RequestParam @ApiQueryParam(name = "montant", description = "The amount")double montant,
			@RequestParam @ApiQueryParam(name = "codeEmp", description = "The code of Emp")Long codeEmp) {
		return operationMetier.retirer(codeCompte, montant, codeEmp);
	}

	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/virement" ,method=RequestMethod.PUT)
	@ApiMethod(description="virement") 
	public boolean virement(
			@RequestParam @ApiQueryParam(name = "codeCompte1", description = "The code of account 1") String codeCompte1, 
			@RequestParam @ApiQueryParam(name = "codeCompte2", description = "The code of account 2") String codeCompte2,
			@RequestParam @ApiQueryParam(name = "montant", description = "The amount") double montant,
			@RequestParam @ApiQueryParam(name = "codeEmp", description = "The code of Emp") Long codeEmp) {
		return operationMetier.virement(codeCompte1, codeCompte2, montant, codeEmp);
	}

	
}
