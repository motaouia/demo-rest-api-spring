package org.medmota.demorest.payroll.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.medmota.demorest.payroll.assembllers.EmployeeModelAssembler;
import org.medmota.demorest.payroll.exceptions.EmployeeNotFoundException;
import org.medmota.demorest.payroll.models.Employee;
import org.medmota.demorest.payroll.repositories.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	
	private final EmployeeRepository employeeRepository;
	private final EmployeeModelAssembler assembler;


	EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler assembler) {
	  this.employeeRepository = employeeRepository;
	  this.assembler = assembler;
	}
	
	@GetMapping("/employees/{id}")
	public EntityModel<Employee> getEmployee(@PathVariable Long id) {
		 Employee employee = employeeRepository.findById(id)//
			      .orElseThrow(() -> new EmployeeNotFoundException(id));
		 return assembler.toModel(employee);
	}
	
	@GetMapping("/employees")
	public CollectionModel<EntityModel<Employee>>  listAllEmployees(){
		List<EntityModel<Employee>> employees = employeeRepository.findAll().stream() //
			      .map(assembler::toModel) //
			      .collect(Collectors.toList());

	    return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).listAllEmployees()).withSelfRel());	}
	
	@PostMapping("/employees")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		EntityModel<Employee> entityModel = assembler.toModel(employeeRepository.save(employee));
		return ResponseEntity //
			      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
			      .body(entityModel);
	}
	
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		Employee updatedEmployee = employeeRepository.findById(id) //
			      .map(employee -> {
			        employee.setName(newEmployee.getName());
			        employee.setRole(newEmployee.getRole());
			        return employeeRepository.save(employee);
			      }) //
			      .orElseGet(() -> {
			        newEmployee.setId(id);
			        return employeeRepository.save(newEmployee);
			      });

			  EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

			  return ResponseEntity //
			      .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
			      .body(entityModel);
	}
	
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?>  deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
