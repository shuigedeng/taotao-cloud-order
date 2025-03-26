package com.taotao.cloud.order.interfaces.controller.seller;

import com.taotao.cloud.order.application.dto.order.cmmond.CreateOrderCommand;
import com.taotao.cloud.order.application.dto.order.cmmond.CreateOrderResponse;
import com.taotao.cloud.order.application.dto.order.cmmond.RequestInvoiceCommand;
import com.taotao.cloud.order.application.dto.order.query.*;
import com.taotao.cloud.order.application.service.order.OrderCommandService;
import com.taotao.cloud.order.application.service.order.OrderQueryService;
import com.taotao.cloud.order.domain.order.valueobject.OrderStatus;
import com.taotao.cloud.order.domain.order.valueobject.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
	private final OrderQueryService orderQueryService;
	private final OrderCommandService orderCommandService;

	@PostMapping
	@ResponseStatus(CREATED)
	public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderCommand command,
										   @AuthenticationPrincipal User user) {
		return orderCommandService.createOrder(command, user);
	}

	@PostMapping(value = "/{orderId}/invoice-request")
	public void requestInvoice(@PathVariable("orderId") @NotBlank  String orderId,
							   @RequestBody @Valid RequestInvoiceCommand command,
							   @AuthenticationPrincipal User user) {
		orderCommandService.requestInvoice(orderId, command, user);
	}

	@GetMapping(value = "/{orderId}/status")
	public OrderStatus fetchOrderStatus(@PathVariable("orderId") @NotBlank  String orderId,
										@AuthenticationPrincipal User user) {
		return orderQueryService.fetchOrderStatus(orderId, user);
	}

	//@PostMapping(value = "/list")
	//public PagedList<QListOrder> listOrders(@RequestBody @Valid ListOrdersQuery queryCommand,
	//										@AuthenticationPrincipal User user) {
	//	return orderQueryService.listOrders(queryCommand, user);
	//}

	@GetMapping(value = "/{orderId}")
	public QDetailedOrder fetchDetailedOrder(@PathVariable("orderId") @NotBlank  String orderId,
											 @AuthenticationPrincipal User user) {
		return orderQueryService.fetchDetailedOrder(orderId, user);
	}

	@GetMapping(value = "/{orderId}/shipment")
	public QOrderShipment fetchOrderShipment(@PathVariable("orderId") @NotBlank  String orderId, @AuthenticationPrincipal User user) {
		return orderQueryService.fetchOrderShipment(orderId, user);
	}

	@PostMapping(value = "/quotations")
	public QPriceQuotation requestQuote(@RequestBody @Valid QuotePriceQuery queryCommand, @AuthenticationPrincipal User user) {
		return orderQueryService.quoteOrderPrice(queryCommand, user);
	}

}
