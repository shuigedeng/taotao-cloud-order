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

import static org.springframework.http.HttpStatus.CREATED;

import com.taotao.cloud.order.application.dto.order.command.CreateOrderCommand;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderResponse;
import com.taotao.cloud.order.application.dto.order.command.RequestInvoiceCommand;
import com.taotao.cloud.order.application.dto.order.query.*;
import com.taotao.cloud.order.application.service.command.OrderCommandService;
import com.taotao.cloud.order.application.service.query.OrderQueryService;
import com.taotao.cloud.order.domain.order.valobj.OrderStatus;
import com.taotao.cloud.order.domain.order.valobj.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CreateOrderResponse createOrder(
            @RequestBody @Valid CreateOrderCommand command, @AuthenticationPrincipal User user) {
        return orderCommandService.createOrder(command, user);
    }

    @PostMapping(value = "/{orderId}/invoice-request")
    public void requestInvoice(
            @PathVariable("orderId") @NotBlank String orderId,
            @RequestBody @Valid RequestInvoiceCommand command,
            @AuthenticationPrincipal User user) {
        orderCommandService.requestInvoice(orderId, command, user);
    }

    @GetMapping(value = "/{orderId}/status")
    public OrderStatus fetchOrderStatus(
            @PathVariable("orderId") @NotBlank String orderId, @AuthenticationPrincipal User user) {
        return orderQueryService.fetchOrderStatus(orderId, user);
    }

    // @PostMapping(value = "/list")
    // public PagedList<QListOrder> listOrders(@RequestBody @Valid ListOrdersQuery queryCommand,
    //										@AuthenticationPrincipal User user) {
    //	return orderQueryService.listOrders(queryCommand, user);
    // }

    @GetMapping(value = "/{orderId}")
    public QDetailedOrder fetchDetailedOrder(
            @PathVariable("orderId") @NotBlank String orderId, @AuthenticationPrincipal User user) {
        return orderQueryService.fetchDetailedOrder(orderId, user);
    }

    @GetMapping(value = "/{orderId}/shipment")
    public QOrderShipment fetchOrderShipment(
            @PathVariable("orderId") @NotBlank String orderId, @AuthenticationPrincipal User user) {
        return orderQueryService.fetchOrderShipment(orderId, user);
    }

    @PostMapping(value = "/quotations")
    public QPriceQuotation requestQuote(
            @RequestBody @Valid QuotePriceQuery queryCommand, @AuthenticationPrincipal User user) {
        return orderQueryService.quoteOrderPrice(queryCommand, user);
    }
}
