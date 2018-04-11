package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsQualityFormulationMapper;
import cn.jjxx.modules.qs.entity.QsQualityFormulation;
import cn.jjxx.modules.qs.service.IQsQualityFormulationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 品质工程制度制定详细需求描述
 * @Description: 品质工程制度制定详细需求描述
 * @author jjxx
 * @date 2018-04-03 09:03:57
 * @version V1.0   
 *
 */
@Transactional
@Service("qsQualityFormulationService")
public class QsQualityFormulationServiceImpl  extends CommonServiceImpl<QsQualityFormulationMapper,QsQualityFormulation> implements  IQsQualityFormulationService {
	/**
	  *@function 重写加载列表的方法
	  *@author Huangqiling
	  *@date 2018-4-3
	  */
	@Override
	public Page<QsQualityFormulation> selectPage(Page<QsQualityFormulation> page,
			Wrapper<QsQualityFormulation> wrapper) {
		// TODO Auto-generated method stub
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectDataList(page, wrapper));
		return page;
	}
}
