package cn.jjxx.modules.qs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import cn.jjxx.modules.qs.entity.QsSupervisionImplementationDetails;
 
/**   
 * @Title: 监理实施细则数据库控制层接口
 * @Description: 监理实施细则数据库控制层接口
 * @author jjxx
 * @date 2018-03-07 13:17:34
 * @version V1.0   
 *
 */
public interface QsSupervisionImplementationDetailsMapper extends BaseMapper<QsSupervisionImplementationDetails> {
	/**
	 * 
	 * @title: 查找数据列表
	 * @description: 查找数据列表
	 * @param wrapper
	 * @author Wangyuanting .<br>
	 * @date 2018-3-1 上午5:25:26.<br>
	 * @return: List<QsSupervisionImplementationDetails>
	 */
	List<QsSupervisionImplementationDetails> selectDataList(Pagination page, @Param("ew") Wrapper<QsSupervisionImplementationDetails> wrapper);
}