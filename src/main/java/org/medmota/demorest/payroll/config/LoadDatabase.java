package org.medmota.demorest.payroll.config;

import org.medmota.demorest.payroll.models.Employee;
import org.medmota.demorest.payroll.models.Order;
import org.medmota.demorest.payroll.repositories.EmployeeRepository;
import org.medmota.demorest.payroll.repositories.OrderRepository;
import org.medmota.demorest.payroll.utils.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

	private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
		return args -> {
			logger.info("Preloading " + employeeRepository.save(new Employee("Mohamed", "MOTAOUIA", "burglar")));
			logger.info("Preloading " + employeeRepository.save(new Employee("Adam", "JABRI", "thief")));

			employeeRepository.findAll().forEach(employee -> logger.info("Preloaded " + employee));

			orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
			orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

			orderRepository.findAll().forEach(order -> {
				logger.info("Preloaded " + order);
			});

		};
	}

}