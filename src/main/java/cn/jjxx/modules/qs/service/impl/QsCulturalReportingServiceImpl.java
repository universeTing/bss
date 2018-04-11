package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsCulturalReportingMapper;
import cn.jjxx.modules.qs.entity.QsCulturalReporting;
import cn.jjxx.modules.qs.service.IQsCulturalReportingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 文化宣传和报到
 * @Description: 文化宣传和报到
 * @author jjxx
 * @date 2018-04-03 13:21:13
 * @version V1.0   
 *
 */
@Transactional
@Service("qsCulturalReportingService")
public class QsCulturalReportingServiceImpl  extends CommonServiceImpl<QsCulturalReportingMapper,QsCulturalReporting> implements  IQsCulturalReportingService {
	/**
	  *@function 重写加载列表的方法
	  *@author Huangqiling
	  *@date 2018-4-3
	  */
	@Override
	public Page<QsCulturalReporting> selectPage(Page<QsCulturalReporting> page,
			Wrapper<QsCulturalReporting> wrapper) {
		// TODO Auto-generated method stub
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectDataList(page, wrapper));
		return page;
	}
}
