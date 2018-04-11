package cn.jjxx.modules.qs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.jjxx.modules.qs.entity.QsConstructionOrganizationDesign;
 
/**   
 * @Title: 施工组织设计数据库控制层接口
 * @Description: 施工组织设计数据库控制层接口
 * @author jjxx
 * @date 2018-03-07 11:03:57
 * @version V1.0   
 *
 */
public interface QsConstructionOrganizationDesignMapper extends BaseMapper<QsConstructionOrganizationDesign> {
	/**
	 * 
	 * @title: 查找数据列表
	 * @description: 查找数据列表
	 * @param wrapper
	 * @author Huangqiling .<br>
	 * @date 2018-3-7 上午11:49:26.<br>
	 * @return: List<QsConstructionOrganizationDesign>
	 */
	List<QsConstructionOrganizationDesign> selectDataList(Pagination page, @Param("ew") Wrapper<QsConstructionOrganizationDesign> wrapper);
}