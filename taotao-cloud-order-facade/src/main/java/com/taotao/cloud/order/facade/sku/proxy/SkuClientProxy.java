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

package com.taotao.cloud.order.facade.sku.proxy;

import com.taotao.boot.common.model.RpcRequest;
import com.taotao.boot.common.model.RpcResponse;
import com.taotao.cloud.goods.api.dubbo.GoodsRpcService;
import com.taotao.cloud.goods.api.dubbo.request.GoodsQueryRpcRequest;
import com.taotao.cloud.goods.api.dubbo.response.GoodsQueryRpcResponse;
import com.taotao.cloud.goods.api.grpc.GoodsSkuGrpcResponse;
import com.taotao.cloud.order.facade.sku.adapter.SkuClientAdapter;
import com.taotao.cloud.order.facade.sku.grpc.SkuGrpcClient;
import com.taotao.cloud.order.facade.sku.vo.SkuVO;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class SkuClientProxy {

    // @Resource
    // private GoodsSkuApi goodsSkuApi;
    @Resource private SkuClientAdapter skuClientAdapter;

    @Resource private SkuGrpcClient skuGrpcClient;

    @DubboReference private GoodsRpcService goodsRpcService;

    // 查询用户
    public SkuVO getUserInfo(Long skuId) {
        //		GoodsSkuSpecGalleryApiResponse user = goodsSkuApi.getGoodsSkuByIdFromCache(skuId);
		RpcResponse<GoodsQueryRpcResponse> goodsQueryResponse =
			goodsRpcService.queryGoodsByParams(RpcRequest.success(new GoodsQueryRpcRequest()));

        GoodsSkuGrpcResponse helloReply = skuGrpcClient.getGoodsSkuByIdFromCache("");

        return skuClientAdapter.convert(null, goodsQueryResponse.getData(), helloReply);
    }
}
