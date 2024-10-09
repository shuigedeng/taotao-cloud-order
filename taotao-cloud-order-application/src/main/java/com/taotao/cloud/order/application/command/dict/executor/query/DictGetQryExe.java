

package com.taotao.cloud.order.application.command.dict.executor.query;

import com.taotao.boot.common.model.Result;
import com.taotao.cloud.order.application.adapter.DictAdapter;
import com.taotao.cloud.order.application.command.dict.dto.DictGetQry;
import com.taotao.cloud.order.application.command.dict.dto.clientobject.DictCO;
import com.taotao.cloud.order.application.converter.DictConvert;
import com.taotao.cloud.order.domain.dict.entity.DictEntity;
import com.taotao.cloud.order.domain.dict.service.DictDomainService;
import com.taotao.cloud.order.infrastructure.persistent.dict.mapper.DictMapper;
import com.taotao.cloud.order.infrastructure.persistent.dict.po.DictPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * 查看字典执行器.
 */
@Component
@RequiredArgsConstructor
public class DictGetQryExe {

	private final DictDomainService dictDomainService;
	//private final DictAdapter dictAdapter;
	private final DictConvert dictConvert;
	private final DictMapper dictMapper;

	/**
	 * 执行查看字典.
	 * @param dictGetQry 查看字典参数
	 * @return 字典
	 */
	//@DS(TENANT)
	public DictCO execute(DictGetQry dictGetQry) {
		DictPO dictPO = dictMapper.selectById(dictGetQry.getId());
		return dictConvert.convert(dictPO);
	}

}
