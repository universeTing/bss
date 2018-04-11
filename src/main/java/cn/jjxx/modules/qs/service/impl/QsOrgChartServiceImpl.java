package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsOrgChartMapper;
import cn.jjxx.modules.qs.entity.QsOrgChart;
import cn.jjxx.modules.qs.service.IQsOrgChartService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 组织机构图
 * @Description: 组织机构图
 * @author jjxx
 * @date 2018-03-01 10:20:31
 * @version V1.0   
 *
 */
@Transactional
@Service("qsOrgChartService")
public class QsOrgChartServiceImpl  extends CommonServiceImpl<QsOrgChartMapper,QsOrgChart> implements  IQsOrgChartService {

    /**
     * 重写jqgrid查询列表方法
     * @author mali
     * @param page
     * @param wrapper
     * @date 2018-03-02
     * @return
     */
    @Override
    public Page<QsOrgChart> selectPage(Page<QsOrgChart> page, Wrapper<QsOrgChart> wrapper) {
        wrapper.eq("1", "1");
        page.setRecords(baseMapper.selectPage(page, wrapper));
        return page;
    }
}
