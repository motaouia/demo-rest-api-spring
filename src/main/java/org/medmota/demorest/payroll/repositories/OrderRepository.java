package org.medmota.demorest.payroll.repositories;

import org.medmota.demorest.payroll.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
