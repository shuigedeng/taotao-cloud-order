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

package com.taotao.cloud.order.api.inner.dto.query;

import com.taotao.boot.common.model.ddd.types.MarkerRequest;
import com.taotao.boot.common.model.ddd.types.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * 公司查询对象
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 16:31:52
 */
@Setter
@Getter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "公司查询对象")
public class OrderApiQuery implements Query {

    @Serial private static final long serialVersionUID = -4132785717179910025L;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "租户密钥")
    private String tenantSecret;

    @Schema(description = "公司名称")
    private String name;

    @Schema(description = "企业全称")
    private String fullName;

    @Pattern(regexp = "^|[a-zA-Z0-9]{18}$", message = "信用代码格式错误")
    @Schema(description = "信用代码")
    private String creditCode;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "联系人")
    private String username;

    @Schema(description = "联系人手机号")
    private String phone;

    @Schema(description = "联系人地址")
    private String address;

    @Schema(description = "请求域名")
    private String domain;

    @Schema(description = "公司网址")
    private String webSite;

    @Schema(description = "所在地区")
    private String regionInfo;

    @Schema(description = "公司类型")
    private Integer type;

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置
     *
     * @param tenantId tenantId
     * @since 2022.03
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getTenantSecret() {
        return tenantSecret;
    }

    /**
     * 设置
     *
     * @param tenantSecret tenantSecret
     * @since 2022.03
     */
    public void setTenantSecret(String tenantSecret) {
        this.tenantSecret = tenantSecret;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name name
     * @since 2022.03
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置
     *
     * @param fullName fullName
     * @since 2022.03
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * 设置
     *
     * @param creditCode creditCode
     * @since 2022.03
     */
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     *
     * @param email email
     * @since 2022.03
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     *
     * @param username username
     * @since 2022.03
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     *
     * @param phone phone
     * @since 2022.03
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     *
     * @param address address
     * @since 2022.03
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置
     *
     * @param domain domain
     * @since 2022.03
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * 设置
     *
     * @param webSite webSite
     * @since 2022.03
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */
    public String getRegionInfo() {
        return regionInfo;
    }

    /**
     * 设置
     *
     * @param regionInfo regionInfo
     * @since 2022.03
     */
    public void setRegionInfo(String regionInfo) {
        this.regionInfo = regionInfo;
    }

    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置
     *
     * @param type type
     * @since 2022.03
     */
    public void setType(Integer type) {
        this.type = type;
    }
}
