package com.taotao.cloud.order.application.acl.service;


import com.taotao.cloud.order.application.acl.dto.credit.req.CreditReq;
import com.taotao.cloud.order.application.acl.dto.credit.res.CreditRes;

public interface CreditAclService {
	CreditRes credit( CreditReq creditReq);
}
