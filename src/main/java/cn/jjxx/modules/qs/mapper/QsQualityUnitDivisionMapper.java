package cn.jjxx.modules.qs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.jjxx.core.common.mapper.BaseTreeMapper;
import cn.jjxx.modules.qs.entity.QsQualityUnitDivision;
 
/**   
 * @Title: 质量单元划分数据库控制层接口
 * @Description: 质量单元划分数据库控制层接口
 * @author jjxx
 * @date 2018-03-14 12:06:30
 * @version V1.0   
 *
 */
public interface QsQualityUnitDivisionMapper extends BaseTreeMapper<QsQualityUnitDivision> {
	/**
	 * @function 重写查询树节点的方法
	 * @author huangqiling
	 * @date 2018-3-16
	 */
	List<QsQualityUnitDivision> selectTreeList(@Param("ew")Wrapper<QsQualityUnitDivision> wrapper);
	
	/**
	 * @Description: 通过组织Id,获取质量单元集合
	 * @param orgId 组织Id
	 * @return List<QsQualityUnitDivision>
	 * @author 黄启玲
	 * @date 2018-3-20 上午11:31:26
	 */
	List<QsQualityUnitDivision> selectByOrgId(String orgId);
}