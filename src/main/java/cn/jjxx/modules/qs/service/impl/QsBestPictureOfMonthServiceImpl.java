package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsBestPictureOfMonthMapper;
import cn.jjxx.modules.qs.entity.QsBestPictureOfMonth;
import cn.jjxx.modules.qs.service.IQsBestPictureOfMonthService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: qs_best_picture_of_month
 * @Description: qs_best_picture_of_month
 * @author jjxx
 * @date 2018-04-02 14:12:11
 * @version V1.0   
 *
 */
@Transactional
@Service("qsBestPictureOfMonthService")
public class QsBestPictureOfMonthServiceImpl  extends CommonServiceImpl<QsBestPictureOfMonthMapper,QsBestPictureOfMonth> implements  IQsBestPictureOfMonthService {

    /**
     * 重写jqgrid查询列表方法
     * @author mali
     * @param page
     * @param wrapper
     * @date 2018-04-02
     * @return
     */
    @Override
    public Page<QsBestPictureOfMonth> selectPage(Page<QsBestPictureOfMonth> page, Wrapper<QsBestPictureOfMonth> wrapper) {
        wrapper.eq("1", "1");
        page.setRecords(baseMapper.selectPage(page, wrapper));
        return page;
    }
}
