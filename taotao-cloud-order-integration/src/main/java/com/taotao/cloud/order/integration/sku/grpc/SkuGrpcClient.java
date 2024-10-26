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

package com.taotao.cloud.order.integration.sku.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.cloud.goods.api.grpc.GoodsSkuGrpcRequest;
import com.taotao.cloud.goods.api.grpc.GoodsSkuGrpcResponse;
import com.taotao.cloud.goods.api.grpc.GoodsSkuGrpcServiceGrpc;
import com.taotao.cloud.goods.api.grpc.GoodsSkuGrpcServiceGrpc.GoodsSkuGrpcServiceFutureStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class SkuGrpcClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public GoodsSkuGrpcResponse getGoodsSkuByIdFromCache(String name) {
        try {
            final ServiceInstance instanceInfo =
                    discoveryClient.getInstances("my-service-name").get(0);
            final ManagedChannel channel = ManagedChannelBuilder.forAddress(
                            instanceInfo.getUri().toString(), instanceInfo.getPort())
                    .usePlaintext()
                    .build();
            final GoodsSkuGrpcServiceFutureStub stub = GoodsSkuGrpcServiceGrpc.newFutureStub(channel);
            GoodsSkuGrpcRequest helloRequest = GoodsSkuGrpcRequest.parseFrom(ByteString.copyFromUtf8(name));
            ListenableFuture<GoodsSkuGrpcResponse> helloReplyListenableFuture =
                    stub.getGoodsSkuByIdFromCache(helloRequest);
            return helloReplyListenableFuture.get();
        } catch (InvalidProtocolBufferException | InterruptedException | ExecutionException e) {
            LogUtils.error("sayHello---------------");
            throw new RuntimeException(e);
        }
    }
}
