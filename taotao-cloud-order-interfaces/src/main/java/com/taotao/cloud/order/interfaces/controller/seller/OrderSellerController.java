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

package com.taotao.cloud.order.interfaces.controller.seller;

import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderCommand;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderResponse;
import com.taotao.cloud.order.application.dto.order.command.RequestInvoiceCommand;
import com.taotao.cloud.order.application.dto.order.query.QDetailedOrder;
import com.taotao.cloud.order.application.dto.order.query.QOrderShipment;
import com.taotao.cloud.order.application.dto.order.query.QPriceQuotation;
import com.taotao.cloud.order.application.dto.order.query.QuotePriceQuery;
import com.taotao.cloud.order.application.service.command.OrderCommandService;
import com.taotao.cloud.order.application.service.query.OrderQueryService;
import com.taotao.cloud.order.domain.valobj.OrderStatus;
import com.taotao.cloud.order.domain.valobj.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderSellerController extends BusinessController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;

    @PostMapping
    public Result<CreateOrderResponse> createOrder(
            @RequestBody @Valid CreateOrderCommand command, @AuthenticationPrincipal User user) {
        return Result.success(orderCommandService.createOrder(command, user));
    }

    @PostMapping(value = "/{orderId}/invoice-request")
    public Result<Void> requestInvoice(
            @PathVariable("orderId") @NotBlank String orderId,
            @RequestBody @Valid RequestInvoiceCommand command,
            @AuthenticationPrincipal User user) {
        orderCommandService.requestInvoice(orderId, command, user);
        return Result.success();
    }

    @GetMapping(value = "/{orderId}/status")
    public Result<OrderStatus> fetchOrderStatus(
            @PathVariable("orderId") @NotBlank String orderId, @AuthenticationPrincipal User user) {
        return Result.success(orderQueryService.fetchOrderStatus(orderId, user));
    }

    @GetMapping(value = "/{orderId}")
    public Result<QDetailedOrder> fetchDetailedOrder(
            @PathVariable("orderId") @NotBlank String orderId, @AuthenticationPrincipal User user) {
        return Result.success(orderQueryService.fetchDetailedOrder(orderId, user));
    }

    @GetMapping(value = "/{orderId}/shipment")
    public Result<QOrderShipment> fetchOrderShipment(
            @PathVariable("orderId") @NotBlank String orderId, @AuthenticationPrincipal User user) {
        return Result.success(orderQueryService.fetchOrderShipment(orderId, user));
    }

    @PostMapping(value = "/quotations")
    public Result<QPriceQuotation> requestQuote(
            @RequestBody @Valid QuotePriceQuery queryCommand, @AuthenticationPrincipal User user) {
        return Result.success(orderQueryService.quoteOrderPrice(queryCommand, user));
    }
}
