package cn.jjxx.modules.qs.service.impl;

import java.util.List;

import cn.jjxx.core.common.service.impl.TreeCommonServiceImpl;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.core.query.parse.QueryToWrapper;
import cn.jjxx.modules.qs.mapper.QsQualityUnitDivisionMapper;
import cn.jjxx.modules.qs.entity.QsQualityUnitDivision;
import cn.jjxx.modules.qs.service.IQsQualityUnitDivisionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;

/**   
 * @Title: 质量单元划分
 * @Description: 质量单元划分
 * @author jjxx
 * @date 2018-03-14 12:06:30
 * @version V1.0   
 *
 */
@Transactional
@Service("qsQualityUnitDivisionService")
public class QsQualityUnitDivisionServiceImpl  extends TreeCommonServiceImpl<QsQualityUnitDivisionMapper,QsQualityUnitDivision,String> implements  IQsQualityUnitDivisionService {

	/**
	 * @function 重写查询树节点的方法
	 * @author huangqiling
	 * @date 2018-3-16
	 */
	@Override
	public List<QsQualityUnitDivision> selectTreeList(Queryable queryable,
			Wrapper<QsQualityUnitDivision> wrapper) {
		// TODO Auto-generated method stub
		QueryToWrapper<QsQualityUnitDivision> queryToWrapper = new QueryToWrapper<QsQualityUnitDivision>();
		wrapper.eq("1", "1");
		// 排序问题
		queryToWrapper.parseSort(wrapper, queryable);
		List<QsQualityUnitDivision> content = baseMapper.selectTreeList(wrapper);
		return content;
	}

	@Override
	public List<QsQualityUnitDivision> selectByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return baseMapper.selectByOrgId(orgId);
	}

	@Override
	public boolean saveQsUnitExcels(
			List<QsQualityUnitDivision> importQsUnitMainList) {
		// TODO Auto-generated method stub
		try {
			insertBatch(importQsUnitMainList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
