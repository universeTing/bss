package cn.jjxx.modules.sms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jjxx.core.common.controller.BaseCRUDController;
import cn.jjxx.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jjxx.modules.sms.entity.SmsTemplate;

/**   
 * @Title: 短信模版
 * @Description: 短信模版
 * @author jeeweb
 * @date 2017-06-08 10:50:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/sms/smstemplate")
@RequiresPathPermission("sms:smstemplate")
public class SmsTemplateController extends BaseCRUDController<SmsTemplate, String> {

}
