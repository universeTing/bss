package cn.jjxx.modules.qs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.jjxx.modules.qs.entity.QsCulturalReporting;
 
/**   
 * @Title: 文化宣传和报到数据库控制层接口
 * @Description: 文化宣传和报到数据库控制层接口
 * @author jjxx
 * @date 2018-04-03 13:21:13
 * @version V1.0   
 *
 */
public interface QsCulturalReportingMapper extends BaseMapper<QsCulturalReporting> {
	/**
	 * 
	 * @title: 查找数据列表
	 * @description: 查找数据列表
	 * @param wrapper
	 * @author Huangqiling .<br>
	 * @date 2018-4-3 上午10:25:26.<br>
	 * @return: List<QsCulturalReporting>
	 */
	List<QsCulturalReporting> selectDataList(Pagination page, @Param("ew") Wrapper<QsCulturalReporting> wrapper);
}