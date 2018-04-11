package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsAsideRecordMapper;
import cn.jjxx.modules.qs.entity.QsAsideRecord;
import cn.jjxx.modules.qs.entity.QsPatrolRecord;
import cn.jjxx.modules.qs.service.IQsAsideRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: qs_aside_record
 * @Description: qs_aside_record
 * @author jjxx
 * @date 2018-03-28 14:11:51
 * @version V1.0   
 *
 */
@Transactional
@Service("qsAsideRecordService")
public class QsAsideRecordServiceImpl  extends CommonServiceImpl<QsAsideRecordMapper,QsAsideRecord> implements  IQsAsideRecordService {
	/**
	  *@function 重写加载列表的方法
	  *@author Huangqiling
	  *@date 2018-3-30
	  */
	@Override
	public Page<QsAsideRecord> selectPage(Page<QsAsideRecord> page,
			Wrapper<QsAsideRecord> wrapper) {
		// TODO Auto-generated method stub
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectPage(page, wrapper));
		return page;
	}
}
