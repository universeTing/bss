package cn.jjxx.modules.qs.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.jjxx.modules.qs.entity.QsOrgChart;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 * @Title: 组织机构图数据库控制层接口
 * @Description: 组织机构图数据库控制层接口
 * @author jjxx
 * @date 2018-03-01 10:20:31
 * @version V1.0   
 *
 */
public interface QsOrgChartMapper extends BaseMapper<QsOrgChart> {
    /**
     *
     * @title: 查找数据列表
     * @description: 查找数据列表
     * @param wrapper
     * @author mali .<br>
     * @date 2018-3-2 11:11:50.<br>
     * @return: List<QsOrgChart>
     */
    List<QsOrgChart> selectPage(Pagination page, @Param("ew") Wrapper<QsOrgChart> wrapper);
}