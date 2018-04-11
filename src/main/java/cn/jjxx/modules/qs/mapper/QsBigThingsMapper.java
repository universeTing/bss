package cn.jjxx.modules.qs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.jjxx.modules.qs.entity.QsBigThings;
 
/**   
 * @Title: 质量管理大事记数据库控制层接口
 * @Description: 质量管理大事记数据库控制层接口
 * @author jjxx
 * @date 2018-04-03 17:36:56
 * @version V1.0   
 *
 */
public interface QsBigThingsMapper extends BaseMapper<QsBigThings> {
	/**
	 * 
	 * @title: 查找数据列表
	 * @description: 查找数据列表
	 * @param wrapper
	 * @author Huangqiling .<br>
	 * @date 2018-4-3 上午10:25:26.<br>
	 * @return: List<QsBigThings>
	 */
	List<QsBigThings> selectDataList(Pagination page, @Param("ew") Wrapper<QsBigThings> wrapper);
}