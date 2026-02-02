package com.taotao.cloud.order.facade.sys.invoker;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * SysInvoker
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
@Component
@RequiredArgsConstructor
public class SysInvoker {

//    private final DictApi dictApi;
//
//    @DubboReference
//    private final DictRpcService dictRpcService;
//
//    public GatewayResponse<DictApiResponse> findByCode( GatewayRequest<DictQueryApiRequest> gatewayRequest ) {
//        return new GatewayInvokeBuilder<DictQueryApiRequest, DictApiResponse>()
//                .description("sys系统-字典信息查询")
//                .gatewayRouter(request -> dictApi.findByCode(Request.from(request)))
//                .addFirst(new SysInterceptor<>())
//                .build()
//                .invoke(gatewayRequest);
//    }


}
