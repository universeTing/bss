package cn.jjxx.modules.qs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.jjxx.modules.qs.entity.QsQualityFormulation;
 
/**   
 * @Title: 品质工程制度制定详细需求描述数据库控制层接口
 * @Description: 品质工程制度制定详细需求描述数据库控制层接口
 * @author jjxx
 * @date 2018-04-03 09:03:57
 * @version V1.0   
 *
 */
public interface QsQualityFormulationMapper extends BaseMapper<QsQualityFormulation> {
	/**
	 * 
	 * @title: 查找数据列表
	 * @description: 查找数据列表
	 * @param wrapper
	 * @author Huangqiling .<br>
	 * @date 2018-4-3 上午10:25:26.<br>
	 * @return: List<QsQualityFormulation>
	 */
	List<QsQualityFormulation> selectDataList(Pagination page, @Param("ew") Wrapper<QsQualityFormulation> wrapper);
    
}