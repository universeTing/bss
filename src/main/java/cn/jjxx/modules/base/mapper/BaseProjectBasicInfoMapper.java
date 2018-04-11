package cn.jjxx.modules.base.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.jjxx.modules.base.entity.BaseProjectBasicInfo;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 * @Title: base_project_basic_info数据库控制层接口
 * @Description: base_project_basic_info数据库控制层接口
 * @author jjxx
 * @date 2018-03-23 13:50:40
 * @version V1.0   
 *
 */
public interface BaseProjectBasicInfoMapper extends BaseMapper<BaseProjectBasicInfo> {
    /**
     *
     * @title: 查找数据列表
     * @description: 查找数据列表
     * @param wrapper
     * @author mali .<br>
     * @date 2018-3-2 11:11:50.<br>
     * @return: List<QsOrgChart>
     */
    List<BaseProjectBasicInfo> selectPage(Pagination page, @Param("ew") Wrapper<BaseProjectBasicInfo> wrapper);
}