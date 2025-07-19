/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.order.domain.order.valueobject.detail;

import com.taotao.cloud.order.domain.order.valueobject.invoice.InvoiceTitle;
import com.taotao.cloud.order.domain.order.valueobject.plan.PlanType;
import java.time.Instant;

public class Tenant {
    public void validateAddExtraMembers(int amount) {}

    public boolean isEffectiveFreePlan() {

        return false;
    }

    public boolean isMryManageTenant() {
        return false;
    }

    public boolean isMryTestingTenant() {
        return false;
    }

    public void validateAddExtraVideoTraffic(int amount) {}

    public void validateAddPlanDuration(int yearDuration) {}

    public PlanType effectivePlanType() {

        return PlanType.FREE;
    }

    public String planVersion() {

        return "";
    }

    public Object getTenantId() {
        return null;
    }

    public void validateAddExtraStorage(int amount) {}

    public Instant packagesExpiredAt() {
        return null;
    }

    public InvoiceTitle getInvoiceTitle() {
        return null;
    }
}
