package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsBigThingsMapper;
import cn.jjxx.modules.qs.entity.QsBigThings;
import cn.jjxx.modules.qs.service.IQsBigThingsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 质量管理大事记
 * @Description: 质量管理大事记
 * @author jjxx
 * @date 2018-04-03 17:36:56
 * @version V1.0   
 *
 */
@Transactional
@Service("qsBigThingsService")
public class QsBigThingsServiceImpl  extends CommonServiceImpl<QsBigThingsMapper,QsBigThings> implements  IQsBigThingsService {
	/**
	  *@function 重写加载列表的方法
	  *@author Huangqiling
	  *@date 2018-4-3
	  */
	@Override
	public Page<QsBigThings> selectPage(Page<QsBigThings> page,
			Wrapper<QsBigThings> wrapper) {
		// TODO Auto-generated method stub
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectDataList(page, wrapper));
		return page;
	}
}
