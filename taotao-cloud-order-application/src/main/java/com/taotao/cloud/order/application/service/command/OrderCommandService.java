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

package com.taotao.cloud.order.application.service.command;

import com.taotao.cloud.order.application.dto.order.command.CreateOrderCommand;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderResponse;
import com.taotao.cloud.order.application.dto.order.command.MemberAddressDTO;
import com.taotao.cloud.order.application.dto.order.command.RequestInvoiceCommand;
import com.taotao.cloud.order.application.dto.order.command.TradeDTO;
import com.taotao.cloud.order.application.dto.order.command.OrderMessage;
import com.taotao.cloud.order.domain.valobj.User;
import com.taotao.cloud.order.domain.valobj.delivery.Delivery;
import com.taotao.cloud.order.domain.valobj.invoice.UploadedFile;
import com.taotao.cloud.order.application.dto.order.result.OrderResult;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface OrderCommandService {

    CreateOrderResponse createOrder(CreateOrderCommand command, User user);

    void requestInvoice(String orderId, RequestInvoiceCommand command, User user);

    void wxPay(String orderId, String wxTxnId, Instant paidAt, User user);

    void wxTransferPay(String orderId, List<UploadedFile> screenShots, Instant paidAt, User user);

    void bankTransferPay(String orderId, String accountId, String bankName, Instant paidAt, User user);

    void updateDelivery(String orderId, Delivery delivery, User user);

    void issueInvoice(String orderId, List<UploadedFile> files, User user);

    void refund(String orderId, String reason, User user);

    void delete(String orderId);

    void systemCancel(String orderSn, String reason);

    void payOrder(String orderSn, String paymentMethod, String receivableNo);

    void afterOrderConfirm(String orderSn);

    OrderResult cancel(String orderSn, String reason);

    OrderResult updateConsignee(String orderSn, MemberAddressDTO memberAddressDTO);

    OrderResult delivery(String orderSn, String invoiceNumber, Long logisticsId);

    OrderResult take(String orderSn, String verificationCode);

    void complete(String orderSn);

    void systemComplete(String orderSn);

    void deleteOrder(String sn);

    void invoice(String sn);

    void agglomeratePintuanOrder(Long pintuanId, String parentOrderSn);

    void downLoadDeliver(HttpServletResponse response, List<String> logisticsName);

    void batchDeliver(MultipartFile files);

    void sendUpdateStatusMessage(OrderMessage orderMessage);

    void intoDB(TradeDTO tradeDTO);
}
