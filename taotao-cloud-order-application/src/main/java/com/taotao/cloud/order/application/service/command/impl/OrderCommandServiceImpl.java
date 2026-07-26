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

package com.taotao.cloud.order.application.service.command.impl;

import com.taotao.cloud.order.application.dto.order.command.CreateOrderCommand;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderResponse;
import com.taotao.cloud.order.application.dto.order.command.MemberAddressDTO;
import com.taotao.cloud.order.application.dto.order.command.OrderMessage;
import com.taotao.cloud.order.application.dto.order.command.RequestInvoiceCommand;
import com.taotao.cloud.order.application.dto.order.command.TradeDTO;
import com.taotao.cloud.order.application.service.command.OrderCommandService;
import com.taotao.cloud.order.domain.aggregate.OrderAgg;
import com.taotao.cloud.order.domain.factory.OrderFactory;
import com.taotao.cloud.order.domain.repository.OrderRepository;
import com.taotao.cloud.order.domain.valobj.User;
import com.taotao.cloud.order.domain.valobj.delivery.Delivery;
import com.taotao.cloud.order.domain.valobj.detail.Tenant;
import com.taotao.cloud.order.domain.valobj.invoice.UploadedFile;
import com.taotao.cloud.order.application.dto.order.result.OrderResult;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class OrderCommandServiceImpl implements OrderCommandService {

	private static final String ORDER_SN_COLUMN = "order_sn";

	private final OrderFactory orderFactory;
	private final OrderRepository orderRepository;

	@Transactional





	/**
	 * 创建订单
	 *
	 * @param command command
	 * @param user 用户
	 * @return CreateOrderResponse
	 * @since 2022.03
	 */

	public CreateOrderResponse createOrder( CreateOrderCommand command, User user ) {
		user.checkIsTenantAdmin();

		Tenant tenant = null;
		OrderAgg order =
			orderFactory.createOrder(
				command.getDetail(), command.getPaymentType(), tenant, user);
		orderRepository.save(order);
		log.info("Created online order[{}] of type[{}].", order.getId(), order.getPaymentType());

		return CreateOrderResponse.builder()
			.id(order.getId())
			.paymentType(order.getPaymentType())
			.wxPayQrUrl(order.getWxPayQrUrl())
			.bankTransferCode(order.getBankTransferCode())
			.price(order.getPrice())
			.payDescription(order.description())
			.build();
	}

	@Transactional





	/**
	 * 请求发票
	 *
	 * @param orderId 订单ID
	 * @param command command
	 * @param user 用户
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void requestInvoice( String orderId, RequestInvoiceCommand command, User user ) {
		user.checkIsTenantAdmin();

		OrderAgg order = orderRepository.byIdAndCheckTenantShip(orderId, user);
		Tenant tenant = null;
		order.requestInvoice(command.getType(), tenant.getInvoiceTitle(), command.getEmail(), user);
		orderRepository.save(order);
		log.info("Requested invoice for order[{}].", orderId);
	}

	@Transactional





	/**
	 * 微信支付
	 *
	 * @param orderId 订单ID
	 * @param wxTxnId 微信交易ID
	 * @param paidAt 支付时间
	 * @param user 用户
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void wxPay( String orderId, String wxTxnId, Instant paidAt, User user ) {
		orderRepository
			.byIdOptional(orderId)
			.ifPresent(
				order -> {
					if (order.atCreated()) {
						order.wxPay(wxTxnId, paidAt, user);
						orderRepository.save(order);
						log.info("Order[{}] paid by WxPay with txn[{}].", orderId, wxTxnId);
					} else {
						order.wxPay(wxTxnId, paidAt, user);
						orderRepository.save(order);
						log.info(
							"Order[{}] WxPay info updated with txn[{}].",
							orderId,
							wxTxnId);
					}
				});
	}

	@Transactional





	/**
	 * 微信转账
	 *
	 * @param orderId 订单ID
	 * @param screenShots 截图列表
	 * @param paidAt 支付时间
	 * @param user 用户
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void wxTransferPay(
		String orderId, List<UploadedFile> screenShots, Instant paidAt, User user ) {
		OrderAgg order = orderRepository.byId(orderId);

		if (order.atCreated()) {
			order.wxTransferPay(screenShots, paidAt, user);
			orderRepository.save(order);
			log.info("Order[{}] paid by WxTransfer.", orderId);
		} else {
			order.wxTransferPay(screenShots, paidAt, user);
			orderRepository.save(order);
		}
		log.info("Order[{}] WxTransfer info updated.", orderId);
	}

	@Transactional





	/**
	 * 银行转账
	 *
	 * @param orderId 订单ID
	 * @param accountId accountId
	 * @param bankName 银行名称
	 * @param paidAt 支付时间
	 * @param user 用户
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void bankTransferPay(
		String orderId, String accountId, String bankName, Instant paidAt, User user ) {
		OrderAgg order = orderRepository.byId(orderId);

		if (order.atCreated()) {
			order.bankTransferPay(accountId, bankName, paidAt, user);
			orderRepository.save(order);
			log.info("Order[{}] paid by bank transfer with account[{}].", orderId, accountId);
		} else {
			order.bankTransferPay(accountId, bankName, paidAt, user);
			orderRepository.save(order);
		}
		log.info("Order[{}] Bank transfer updated with account[{}].", orderId, accountId);
	}

	@Transactional





	/**
	 * 更新配送
	 *
	 * @param orderId 订单ID
	 * @param delivery 配送
	 * @param user 用户
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void updateDelivery( String orderId, Delivery delivery, User user ) {
		OrderAgg order = orderRepository.byId(orderId);
		order.updateDelivery(delivery, user);
		orderRepository.save(order);
		log.info("Order[{}] delivery info updated.", orderId);
	}

	@Transactional





	/**
	 * 签发发票
	 *
	 * @param orderId 订单ID
	 * @param files 文件列表
	 * @param user 用户
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void issueInvoice( String orderId, List<UploadedFile> files, User user ) {
		OrderAgg order = orderRepository.byId(orderId);
		order.issueInvoice(files, user);
		orderRepository.save(order);
		log.info("Order[{}] invoice issued.", orderId);
	}

	@Transactional





	/**
	 * 退款
	 *
	 * @param orderId 订单ID
	 * @param reason 原因
	 * @param user 用户
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void refund( String orderId, String reason, User user ) {
		OrderAgg order = orderRepository.byId(orderId);
		order.refund(reason, user);
		orderRepository.save(order);
		log.info("Order[{}] refunded.", orderId);
	}

	@Transactional





	/**
	 * 删除
	 *
	 * @param orderId 订单ID
	 * @return 无返回值
	 * @since 2022.03
	 */

	public void delete( String orderId ) {
		OrderAgg order = orderRepository.byId(orderId);
		orderRepository.delete(order);
		log.info("Order[{}] deleted.", orderId);
	}

	@Override
	public void systemCancel(String orderSn, String reason) {

	}

	@Override
	public void payOrder(String orderSn, String paymentMethod, String receivableNo) {

	}

	@Override
	public void afterOrderConfirm(String orderSn) {

	}

	@Override
	public OrderResult cancel(String orderSn, String reason) {
		return null;
	}

	@Override
	public OrderResult updateConsignee(String orderSn, MemberAddressDTO memberAddressDTO) {
		return null;
	}

	@Override
	public OrderResult delivery(String orderSn, String invoiceNumber, Long logisticsId) {
		return null;
	}

	@Override
	public OrderResult take(String orderSn, String verificationCode) {
		return null;
	}

	@Override
	public void complete(String orderSn) {

	}

	@Override
	public void systemComplete(String orderSn) {

	}

	@Override
	public void deleteOrder(String sn) {

	}

	@Override
	public void invoice(String sn) {

	}

	@Override
	public void agglomeratePintuanOrder(Long pintuanId, String parentOrderSn) {

	}

	@Override
	public void downLoadDeliver(HttpServletResponse response, List<String> logisticsName) {

	}

	@Override
	public void batchDeliver(MultipartFile files) {

	}

	@Override
	public void sendUpdateStatusMessage(OrderMessage orderMessage) {

	}

	@Override
	public void intoDB(TradeDTO tradeDTO) {

	}
}
