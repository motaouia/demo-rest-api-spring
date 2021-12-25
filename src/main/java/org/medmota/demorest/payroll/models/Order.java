package org.medmota.demorest.payroll.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.medmota.demorest.payroll.utils.Status;

@Entity
@Table(name = "CUSTOM_ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Status status;
	public Order() {
		super();
	}
	public Order(String description, Status status) {
		super();
		this.description = description;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", description=" + description + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.description, this.status);
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
		      return true;
		    if (!(o instanceof Order))
		      return false;
		    Order order = (Order) o;
		    return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description)
		        && this.status == order.status;
	}
	
	
	
	
}
