package cn.jjxx.modules.qs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.jjxx.modules.qs.entity.QsPatrolRecord;
 
/**   
 * @Title: 巡视记录数据库控制层接口
 * @Description: 巡视记录数据库控制层接口
 * @author jjxx
 * @date 2018-03-29 15:40:34
 * @version V1.0   
 *
 */
public interface QsPatrolRecordMapper extends BaseMapper<QsPatrolRecord> {
	/**
	 * 
	 * @title: 查找数据列表
	 * @description: 查找数据列表
	 * @param wrapper
	 * @author Huangqiling
	 * @date 2018-3-30 
	 * @return: List<QsPatrolRecord>
	 */
	List<QsPatrolRecord> selectDataList(Pagination page, @Param("ew") Wrapper<QsPatrolRecord> wrapper);
}