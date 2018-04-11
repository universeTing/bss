package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsSupervisionImplementationDetailsMapper;
import cn.jjxx.modules.qs.entity.QsSupervisionImplementationDetails;
import cn.jjxx.modules.qs.service.IQsSupervisionImplementationDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 监理实施细则
 * @Description: 监理实施细则
 * @author jjxx
 * @date 2018-03-07 13:17:34
 * @version V1.0   
 *
 */
@Transactional
@Service("qsSupervisionImplementationDetailsService")
public class QsSupervisionImplementationDetailsServiceImpl  extends CommonServiceImpl<QsSupervisionImplementationDetailsMapper,QsSupervisionImplementationDetails> implements  IQsSupervisionImplementationDetailsService {
	/**
	  *@function 重写加载列表的方法
	  *@author Wangyuanting
	  *@date 2018-3-7
	  */
	@Override
	public Page<QsSupervisionImplementationDetails> selectPage(Page<QsSupervisionImplementationDetails> page,
			Wrapper<QsSupervisionImplementationDetails> wrapper) {
		// TODO Auto-generated method stub
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectDataList(page, wrapper));
		return page;
	}
}
