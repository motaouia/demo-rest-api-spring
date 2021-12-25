package org.medmota.demorest.payroll.assembllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.medmota.demorest.payroll.controllers.OrderController;
import org.medmota.demorest.payroll.models.Order;
import org.medmota.demorest.payroll.utils.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

	@Override
	public EntityModel<Order> toModel(Order order) {

		// Unconditional links to single-item resource and aggregate root
		EntityModel<Order> orderModel = EntityModel.of(order,
				linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel(),
				linkTo(methodOn(OrderController.class).listAllOrders()).withRel("orders"));

		// Conditional links based on state of the order
		if (order.getStatus() == Status.IN_PROGRESS) {
			orderModel.add(linkTo(methodOn(OrderController.class).deleteOrder(order.getId())).withRel("cancel"));
			orderModel.add(linkTo(methodOn(OrderController.class).completeOrder(order.getId())).withRel("complete"));
		}

		return orderModel;

	}

}
