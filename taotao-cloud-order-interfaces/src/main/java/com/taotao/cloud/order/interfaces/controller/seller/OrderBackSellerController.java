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

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.web.utils.OperationalJudgment;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.cart.result.OrderExportResult;
import com.taotao.cloud.order.application.dto.order.query.OrderPageQuery;
import com.taotao.cloud.order.application.dto.order.result.OrderDetailResult;
import com.taotao.cloud.order.application.dto.order.result.OrderResult;
import com.taotao.cloud.order.application.dto.order.result.OrderSimpleResult;
import com.taotao.cloud.order.application.service.command.OrderCommandService;
import com.taotao.cloud.order.application.service.command.OrderPriceCommandService;
import com.taotao.cloud.order.application.service.query.OrderQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-订单API", description = "店铺端-订单API")
@RequestMapping("/seller/order/order")
public class OrderBackSellerController extends BusinessController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;
    private final OrderPriceCommandService orderPriceCommandService;

    @Operation(summary = "查询订单列表", description = "查询订单列表")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping("/page")
    public Result<PageResult<OrderSimpleResult>> queryMineOrder(OrderPageQuery orderPageQry) {
        return Result.success(orderQueryService.pageQuery(orderPageQry));
    }

    @Operation(summary = "订单明细", description = "订单明细")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/{orderSn}")
    public Result<OrderDetailResult> queryBySn(@NotNull @PathVariable String orderSn) {
        OperationalJudgment.judgment(orderQueryService.queryBySn(orderSn));
        return Result.success(orderQueryService.queryDetail(orderSn));
    }

    @Operation(summary = "修改收货人信息", description = "修改收货人信息")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/update/{orderSn}/consignee")
    public Result<OrderResult> consignee(
            @NotNull(message = "参数非法") @PathVariable String orderSn,
            @Validated OrderResult.MemberAddressDTO memberAddressDTO) {
        return Result.success(orderCommandService.updateConsignee(orderSn, memberAddressDTO));
    }

    @Operation(summary = "修改订单价格", description = "修改订单价格")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/{orderSn}/price")
    public Result<Void> updateOrderPrice(
            @PathVariable String orderSn,
            @NotNull(message = "订单价格不能为空") @RequestParam BigDecimal orderPrice) {
        orderPriceCommandService.updatePrice(orderSn, orderPrice);
        return Result.success();
    }

    @Operation(summary = "订单发货", description = "订单发货")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/{orderSn}/delivery")
    public Result<OrderResult> delivery(
            @NotNull(message = "参数非法") @PathVariable String orderSn,
            @NotNull(message = "发货单号不能为空") String logisticsNo,
            @NotNull(message = "请选择物流公司") Long logisticsId) {
        return Result.success(orderCommandService.delivery(orderSn, logisticsNo, logisticsId));
    }

    @Operation(summary = "取消订单", description = "取消订单")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/{orderSn}/cancel")
    public Result<OrderResult> cancel(@PathVariable String orderSn, @RequestParam String reason) {
        return Result.success(orderCommandService.cancel(orderSn, reason));
    }

    @Operation(summary = "根据核验码获取订单信息", description = "根据核验码获取订单信息")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/verificationCode/{verificationCode}")
    public Result<OrderResult> queryOrderByVerificationCode(@PathVariable String verificationCode) {
        return Result.success(orderQueryService.queryOrderByVerificationCode(verificationCode));
    }

    @Operation(summary = "订单核验", description = "订单核验")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/take/{orderSn}/{verificationCode}")
    public Result<OrderResult> take(@PathVariable String orderSn, @PathVariable String verificationCode) {
        return Result.success(orderCommandService.take(orderSn, verificationCode));
    }

    @Operation(summary = "查询物流踪迹", description = "查询物流踪迹")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/traces/{orderSn}")
    public Result<?> queryTraces(
            @NotBlank(message = "订单编号不能为空") @PathVariable String orderSn) {
        OperationalJudgment.judgment(orderQueryService.queryBySn(orderSn));
        return Result.success(orderQueryService.queryTraces(orderSn));
    }

    @Operation(summary = "下载待发货的订单列表", description = "下载待发货的订单列表")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/downLoadDeliverExcel")
    public Result<Void> downLoadDeliverExcel() {
        orderCommandService.downLoadDeliver(null, null);
        return Result.success();
    }

    @Operation(summary = "上传文件进行订单批量发货", description = "上传文件进行订单批量发货")
    @RequestLogger
    @PostMapping(value = "/batchDeliver", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('dept:tree:data')")
    public Result<Void> batchDeliver(@RequestPart("files") MultipartFile files) {
        orderCommandService.batchDeliver(files);
        return Result.success();
    }

    @Operation(summary = "查询订单导出列表", description = "查询订单导出列表")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping("/queryExportOrder")
    public Result<List<OrderExportResult>> queryExportOrder(OrderPageQuery orderPageQry) {
        return Result.success(orderQueryService.queryExportOrder(orderPageQry));
    }
}
