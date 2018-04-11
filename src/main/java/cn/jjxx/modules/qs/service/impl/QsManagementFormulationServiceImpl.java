package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsManagementFormulationMapper;
import cn.jjxx.modules.qs.entity.QsManagementFormulation;
import cn.jjxx.modules.qs.service.IQsManagementFormulationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 质量管理体系及制度制定
 * @Description: 质量管理体系及制度制定
 * @author jjxx
 * @date 2018-03-01 10:23:59
 * @version V1.0   
 *
 */
@Transactional
@Service("qsManagementFormulationService")
public class QsManagementFormulationServiceImpl  extends CommonServiceImpl<QsManagementFormulationMapper,QsManagementFormulation> implements  IQsManagementFormulationService {
	/**
	  *@function 重写加载列表的方法
	  *@author Huangqiling
	  *@date 2018-3-1
	  */
	@Override
	public Page<QsManagementFormulation> selectPage(Page<QsManagementFormulation> page,
			Wrapper<QsManagementFormulation> wrapper) {
		// TODO Auto-generated method stub
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectDataList(page, wrapper));
		return page;
	}
}
