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

package com.taotao.cloud.order.domain.valobj.detail;

import com.taotao.cloud.order.domain.valobj.invoice.InvoiceTitle;
import com.taotao.cloud.order.domain.valobj.plan.PlanType;

import java.time.Instant;

/**
 * Tenant
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public class Tenant {

    /**
     * 验证
     *
     * @param amount 金额
     * @since 2022.03
     */
    public void validateAddExtraMembers( int amount ) {
    }

    /**
    /**
    /**
    /**
    /**
    /**
    /**

    /**
    /**
    /**
    /**
     * 判断
     *
     * @return 是否成功
     * @since 2022.03
     */
    public boolean isEffectiveFreePlan() {

        return false;
    }

    /**
    /**
    /**
    /**
    /**
    /**
    /**

    /**
    /**
    /**
    /**
     * 判断
     *
     * @return 是否成功
     * @since 2022.03
     */
    public boolean isMryManageTenant() {
        return false;
    }

    /**
    /**
    /**
    /**
    /**
    /**
    /**

    /**
    /**
    /**
    /**
     * 判断
     *
     * @return 是否成功
     * @since 2022.03
     */
    public boolean isMryTestingTenant() {
        return false;
    }

    /**
     * 验证
     *
     * @param amount 金额
     * @since 2022.03
     */
    public void validateAddExtraVideoTraffic( int amount ) {
    }

    /**
     * 验证
     *
     * @param yearDuration yearDuration
     * @since 2022.03
     */
    public void validateAddPlanDuration( int yearDuration ) {
    }

    /**
     * effectivePlanType 方法
     *
     * @return PlanType
     * @since 2022.03
     */
    public PlanType effectivePlanType() {

        return PlanType.FREE;
    }

    /**
     * planVersion 方法
     *
     * @return 字符串
     * @since 2022.03
     */
    public String planVersion() {

        return "";
    }

    /**
    /**
    /**
    /**
    /**
    /**
    /**

    /**
    /**
    /**
    /**
     * 获取
     *
     * @return Object
     * @since 2022.03
     */
    public Object getTenantId() {
        return null;
    }

    /**
     * 验证
     *
     * @param amount 金额
     * @since 2022.03
     */
    public void validateAddExtraStorage( int amount ) {
    }

    /**
     * packagesExpiredAt 方法
     *
     * @return Instant
     * @since 2022.03
     */
    public Instant packagesExpiredAt() {
        return null;
    }

    /**
    /**
    /**
    /**
    /**
    /**
    /**

    /**
    /**
    /**
    /**
     * 获取
     *
     * @return InvoiceTitle
     * @since 2022.03
     */
    public InvoiceTitle getInvoiceTitle() {
        return null;
    }
}
