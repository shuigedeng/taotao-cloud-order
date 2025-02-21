package com.taotao.cloud.order.domain.order.valueobject.detail;

import com.taotao.cloud.order.domain.order.valueobject.plan.PlanType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

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
}
