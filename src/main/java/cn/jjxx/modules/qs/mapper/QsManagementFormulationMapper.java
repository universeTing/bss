package cn.jjxx.modules.qs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.jjxx.modules.qs.entity.QsManagementFormulation;
 
/**   
 * @Title: 质量管理体系及制度制定数据库控制层接口
 * @Description: 质量管理体系及制度制定数据库控制层接口
 * @author jjxx
 * @date 2018-03-01 10:23:59
 * @version V1.0   
 *
 */
public interface QsManagementFormulationMapper extends BaseMapper<QsManagementFormulation> {
	/**
	 * 
	 * @title: 查找数据列表
	 * @description: 查找数据列表
	 * @param wrapper
	 * @author Huangqiling .<br>
	 * @date 2018-3-1 上午5:25:26.<br>
	 * @return: List<QsManagementFormulation>
	 */
	List<QsManagementFormulation> selectDataList(Pagination page, @Param("ew") Wrapper<QsManagementFormulation> wrapper);
}