///*
// * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.taotao.cloud.order.facade.grpc;
//
//import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_07;
//import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_08;
//
//import com.taotao.boot.common.support.info.Caller;
//import com.taotao.boot.common.support.info.Create;
//import com.taotao.boot.common.support.info.GrpcInfo;
//import com.taotao.boot.common.support.info.Update;
//import com.taotao.cloud.order.api.grpc.DictGrpcRequest;
//import com.taotao.cloud.order.api.grpc.DictGrpcResponse;
//import com.taotao.cloud.order.api.grpc.DictGrpcServiceGrpc.DictGrpcServiceImplBase;
//import com.taotao.cloud.order.api.grpc.DictTestGrpcRequest;
//import io.grpc.stub.StreamObserver;
//import lombok.extern.slf4j.Slf4j;
//import net.devh.boot.grpc.server.service.GrpcService;
//
//@Slf4j
//@GrpcService
//public class DictGrpcServiceImpl extends DictGrpcServiceImplBase {
//
//    // @Autowired
//    // private IDevicesFixService deviceService;
//
//    @GrpcInfo(
//            create = @Create(version = V2022_07, date = "2022-07-01 17:11:55"),
//            update = {
//                @Update(version = V2022_07, content = "主要修改了配置信息的接口查询", date = "2022-07-01 17:11:55"),
//                @Update(version = V2022_08, content = "主要修改了配置信息的接口查询08", date = "2022-07-08 15:12:55")
//            },
//            caller = {
//                @Caller(contacts = "张三", desc = "支付系统", sys = "支付系统", use = "调用字典查询获取详情"),
//                @Caller(contacts = "李四", desc = "后台管理-字典管理-添加页面", sys = "后台管理", use = "查询字典")
//            })
//    @Override
//    public void findByCode(DictGrpcRequest request, StreamObserver<DictGrpcResponse> responseObserver) {
//        // super.findByCode(request, responseObserver);
//
//        log.info("findByCode:{}", request.toString());
//        boolean replyTag = false;
//        DictGrpcResponse reply = DictGrpcResponse.newBuilder().setId(1).build();
//        responseObserver.onNext(reply);
//        responseObserver.onCompleted();
//    }
//
//    @Override
//    public void test(DictTestGrpcRequest request, StreamObserver<DictGrpcResponse> responseObserver) {
//        super.test(request, responseObserver);
//    }
//}
