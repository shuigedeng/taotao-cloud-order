package com.taotao.cloud.order.domain.order.valueobject.detail;

import com.taotao.cloud.order.domain.order.valueobject.invoice.InvoiceTitle;
import com.taotao.cloud.order.domain.order.valueobject.plan.PlanType;

import java.time.Instant;

public class Tenant {
	public void validateAddExtraMembers(int amount) {



	}

	public boolean isEffectiveFreePlan() {


		return false;
	}

	public boolean isMryManageTenant() {
		return false;

	}

	public boolean isMryTestingTenant() {
		return false;
	}

	public void validateAddExtraVideoTraffic(int amount) {

	}

	public void validateAddPlanDuration(int yearDuration) {


	}

	public PlanType effectivePlanType() {


		return PlanType.FREE;
	}

	public String planVersion() {

		return "";
	}

	public Object getTenantId() {
		return null;
	}

	public void validateAddExtraStorage(int amount) {

	}

	public Instant packagesExpiredAt() {
		return null;
	}

	public InvoiceTitle getInvoiceTitle() {
		return null;
	}
}
