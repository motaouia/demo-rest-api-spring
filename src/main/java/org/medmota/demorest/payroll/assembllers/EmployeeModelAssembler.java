package org.medmota.demorest.payroll.assembllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.medmota.demorest.payroll.controllers.EmployeeController;
import org.medmota.demorest.payroll.models.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

	@Override
	  public EntityModel<Employee> toModel(Employee employee) {

	    return EntityModel.of(employee, //
	        linkTo(methodOn(EmployeeController.class).getEmployee(employee.getId())).withSelfRel(),
	        linkTo(methodOn(EmployeeController.class).listAllEmployees()).withRel("employees"));
	  }

}
