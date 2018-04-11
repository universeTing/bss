package cn.jjxx.modules.qs.service;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jjxx.core.common.service.ITreeCommonService;
import cn.jjxx.core.query.data.Queryable;
import cn.jjxx.modules.qs.entity.QsQualityUnitDivision;

/**   
 * @Title: 质量单元划分
 * @Description: 质量单元划分
 * @author jjxx
 * @date 2018-03-14 12:06:30
 * @version V1.0   
 *
 */
public interface IQsQualityUnitDivisionService extends ITreeCommonService<QsQualityUnitDivision,String>{
	/**
	 * @Description: 通过组织Id,获取质量单元集合
	 * @param orgId 组织Id
	 * @return List<QsQualityUnitDivision>
	 * @author 黄启玲
	 * @date 2018-3-20 上午11:31:26
	 */
	List<QsQualityUnitDivision> selectByOrgId(String orgId);
	
	/**
	 * @Description: 保存excel导入的质量单元的数据
	 * @param importQsUnitMainList 质量单元实体集合
	 * @return boolean 是否保存成功
	 * @author 郑成功 
	 * @date 2018-3-21 下午2:29:55
	 */
	boolean saveQsUnitExcels(List<QsQualityUnitDivision> importQsUnitMainList);

}

