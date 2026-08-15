package com.taotao.cloud.order.facade.credit.acl;

import com.taotao.cloud.order.application.acl.dto.credit.req.CreditReq;
import com.taotao.cloud.order.application.acl.dto.credit.res.CreditRes;
import com.taotao.cloud.order.application.acl.service.CreditAclService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CreditAclServiceImpl
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@RequiredArgsConstructor
@Service
public class CreditAclServiceImpl implements CreditAclService {

    @Override
    public CreditRes credit( CreditReq creditReq ) {
        return null;
    }
}
