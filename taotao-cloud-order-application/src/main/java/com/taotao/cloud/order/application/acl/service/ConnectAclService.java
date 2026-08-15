package com.taotao.cloud.order.application.acl.service;


import com.taotao.cloud.order.application.acl.dto.connect.req.ConnectReq;
import com.taotao.cloud.order.application.acl.dto.connect.res.ConnectRes;

public interface ConnectAclService {
	ConnectRes connect( ConnectReq connectReq);
}
