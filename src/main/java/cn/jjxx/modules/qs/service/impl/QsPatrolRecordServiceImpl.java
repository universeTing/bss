package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsPatrolRecordMapper;
import cn.jjxx.modules.qs.entity.QsPatrolRecord;
import cn.jjxx.modules.qs.service.IQsPatrolRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 巡视记录
 * @Description: 巡视记录
 * @author jjxx
 * @date 2018-03-29 15:40:34
 * @version V1.0   
 *
 */
@Transactional
@Service("qsPatrolRecordService")
public class QsPatrolRecordServiceImpl  extends CommonServiceImpl<QsPatrolRecordMapper,QsPatrolRecord> implements  IQsPatrolRecordService {
	/**
	  *@function 重写加载列表的方法
	  *@author Huangqiling
	  *@date 2018-3-30
	  */
	@Override
	public Page<QsPatrolRecord> selectPage(Page<QsPatrolRecord> page,
			Wrapper<QsPatrolRecord> wrapper) {
		// TODO Auto-generated method stub
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectDataList(page, wrapper));
		return page;
	}
}
