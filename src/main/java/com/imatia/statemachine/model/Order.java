package com.imatia.statemachine.model;

import java.util.Date;

public class Order {

	private String orderId;
	private int tackingStatusId;
	private Date changeStatusDate;
	
	public Order() {
		
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getTackingStatusId() {
		return tackingStatusId;
	}

	public void setTackingStatusId(int tackingStatusId) {
		this.tackingStatusId = tackingStatusId;
	}

	public Date getChangeStatusDate() {
		return changeStatusDate;
	}

	public void setChangeStatusDate(Date changeStatusDate) {
		this.changeStatusDate = changeStatusDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", tackingStatusId=" + tackingStatusId + ", changeStatusDate="
				+ changeStatusDate + "]";
	}
	
}
