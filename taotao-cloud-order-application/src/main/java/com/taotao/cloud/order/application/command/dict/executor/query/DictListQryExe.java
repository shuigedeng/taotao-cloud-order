

package com.taotao.cloud.order.application.command.dict.executor.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.order.application.adapter.DictAdapter;
import com.taotao.cloud.order.application.command.dict.dto.DictListQry;
import com.taotao.cloud.order.application.command.dict.dto.clientobject.DictCO;
import com.taotao.cloud.order.application.converter.DictConvert;
import com.taotao.cloud.order.domain.dict.entity.DictEntity;
import com.taotao.cloud.order.domain.dict.service.DictDomainService;
import com.taotao.cloud.order.infrastructure.persistent.dict.mapper.DictMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * 查询部门列表执行器.
 *
 */
@Component
@RequiredArgsConstructor
public class DictListQryExe {

	private final DictDomainService dictDomainService;
	//private final DictAdapter dictAdapter;
	//private final DictConvert dictConvert;
	private final DictMapper dictMapper;

	/**
	 * 执行查询部门列表.
	 * @param qry 查询部门列表参数
	 * @return 部门列表
	 */
//	@DS(TENANT)
	public IPage<DictCO> execute(DictListQry qry) {
//		DictEntity dictEntity = dictConvert.convert(qry);
//		IPage<DictEntity> data = dictDomainService.list(dictEntity, qry);
//		return data.convert(dictConvert::convert);

		return null;
	}

}
