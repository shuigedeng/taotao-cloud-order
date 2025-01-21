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

package com.taotao.cloud.order.infrastructure.persistent.persistence.order;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.boot.webagg.entity.BaseSuperEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

/**
 * 订单超时信息表
 *
 * @author shuigedeng
 * @since 2020/4/30 15:44
 */
@Getter
@Setter
@ToString(callSuper = true)
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TableName(OrderOvertimePO.TABLE_NAME)
@Table(name = OrderOvertimePO.TABLE_NAME)
@org.springframework.data.relational.core.mapping.Table(name = OrderOvertimePO.TABLE_NAME)
public class OrderOvertimePO extends BaseSuperEntity<OrderOvertimePO, Long> {

    public static final String TABLE_NAME = "order_overtime";

    /** 收货人姓名 */
    @Column(name = "receiver_name", columnDefinition = "varchar(32) not null comment '收货人姓名'")
    private String receiverName;

    /** 收货人电话 */
    @Column(name = "receiver_phone", columnDefinition = "varchar(32) not null comment '收货人电话'")
    private String receiverPhone;

    /** 支付时间--支付成功后的时间 */
    @Column(name = "pay_success_time", columnDefinition = "datetime comment '支付时间--支付成功后的时间'")
    private LocalDateTime paySuccessTime;

    /** 超时类型 */
    @Column(name = "type", columnDefinition = "int not null default 0 comment '超时类型 0-未支付超时 1-未处理售后超时'")
    private Integer type;

    /** 超时时间 */
    @Column(name = "over_time", columnDefinition = "datetime not null comment '超时时间'")
    private LocalDateTime overTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        OrderOvertimePO that = (OrderOvertimePO) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
