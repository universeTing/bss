package cn.jjxx.core.utils.jpush;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import cn.jjxx.core.utils.MessageUtils;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.*;
 

/**
 * @Title: JpushClientUtil.java .<br>
 * @Package cn.jjxx.core.utils.jpush .<br>
 * @Description: jpush消息推送类 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2018-3-16 下午12:57:28.<br>
 * @version V1.0.<br>
 */
public class JpushClientUtil {
 
    private final static String appKey = MessageUtils.getMessage("jpush.app.key");
 
    private final static String masterSecret = MessageUtils.getMessage("jpush.master.secret");
 
    private static JPushClient jPushClient = new JPushClient(masterSecret,appKey);
	
	private static final String REGISTRATION_IDS = "registration_ids";
	
    /**
     * @Description: TODO .<br>
     * @param alias 别名,可以是userId.<br>
     * @param device 设备通过Platform获取设备类型.<br>
     * @return List<String> 设备注册Id.<br> 
     * @author 郑成功 .<br>
     * @date 2018-3-16 下午3:49:54.<br>
     */
    @SuppressWarnings("unchecked")
	public static List<String> getJpushAliasDeviceList(String alias,String device){
    	List<String> list = new ArrayList<String>();
    	try {
    		AliasDeviceListResult result = jPushClient.getAliasDeviceList(alias,device);
    		JSONObject json = JSONObject.fromObject(String.valueOf(result));
    		list = (List<String>) json.get(REGISTRATION_IDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
    
    /**
     * @Description: 推送给设备标识参数的用户 .<br>
     * @param jpush 激光推送参数实体类 .<br>
     * @return boolean false:推送失败，true:推送成功 .<br> 
     * @author 郑成功 .<br>
     * @date 2018-3-16 上午11:59:02.<br>
     */
    public static boolean sendToRegister(JpushEntity jpush) {
    	boolean result = false;
        try {
        	PushPayload pushPayload= JpushClientUtil.buildPushObjByRegister(jpush);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
                result=true;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
         return result;
    }
 
    /**
     * @Description: 发送给所有安卓用户 .<br>
     * @param jpush 激光推送参数实体类 .<br>
     * @return boolean false:推送失败，true:推送成功 .<br> 
     * @author 郑成功 .<br>
     * @date 2018-3-16 上午11:59:02.<br>
     */
    public static boolean sendToAllAndroid(JpushEntity jpush) {
    	boolean result = false;
        try {
            PushPayload pushPayload= JpushClientUtil.buildPushObjToAndroid(jpush);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return result;
    }
 
    /**
     * @Description: 发送给所有IOS用户 .<br>
     * @param jpush 激光推送参数实体类 .<br>
     * @return boolean false:推送失败，true:推送成功 .<br> 
     * @author 郑成功 .<br>
     * @date 2018-3-16 上午11:59:02.<br>
     */
    public static boolean sendToAllIos(JpushEntity jpush) {
    	boolean result = false;
        try {
            PushPayload pushPayload= JpushClientUtil.buildPushObjToIos(jpush);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return result;
    }
 
    /**
     * @Description: 发送给所有用户 .<br>
     * @param jpush 激光推送参数实体类 .<br>
     * @return boolean false:推送失败，true:推送成功 .<br> 
     * @author 郑成功 .<br>
     * @date 2018-3-16 上午11:59:02.<br>
     */
    public static boolean sendToAll(JpushEntity jpush) {
    	boolean result = false;
        try {
            PushPayload pushPayload= JpushClientUtil.buildPushObjAndroidAndIos(jpush);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 
 
    /**
     * @Description: 构建推送消息到所有用户 .<br>
     * @param jpush 激光推送参数实体类 .<br>
     * @return PushPayload 推送加载对象.<br> 
     * @author 郑成功 .<br>
     * @date 2018-3-16 下午12:44:56.<br>
     */
    public static PushPayload buildPushObjAndroidAndIos(JpushEntity jpush) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert(jpush.getNoticeTitle())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(jpush.getNoticeTitle())
                                .setTitle(jpush.getNoticeTitle())
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("androidNotification extras key",jpush.getExtendParams().toString())
                                .build()
                        )
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(jpush.getNoticeTitle())
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("iosNotification extras key",jpush.getExtendParams().toString())
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)
 
                                .build()
                        )
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(jpush.getMsgContent())
                        .setTitle(jpush.getMsgTitle())
                        .addExtra("message extras key",jpush.getExtendParams().toString())
                        .build())
 
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build()
                )
                .build();
    }
 
    /**
     * @Description: 构建推送消息到注册的用户 .<br>
     * @param jpush 推送消息实体类 .<br>
     * @return PushPayload .<br> 
     * @author 郑成功 .<br>
     * @date 2018-3-16 下午12:50:36.<br>
     */
    private static PushPayload buildPushObjByRegister(JpushEntity jpush) {
        //创建一个IosAlert对象，可指定APNs的alert、title等字段
        //IosAlert iosAlert =  IosAlert.newBuilder().setTitleAndBody("title", "alert body").build();
 
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.registrationId(jpush.getRegisterId()))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
 
                                .setAlert(jpush.getNoticeTitle())
                                .setTitle(jpush.getNoticeTitle())
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("androidNotification extras key",jpush.getExtendParams().toString())
 
                                .build())
                        //指定当前推送的iOS通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(jpush.getNoticeTitle())
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("iosNotification extras key",jpush.getExtendParams().toString())
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                //取消此注释，消息推送时ios将无法在锁屏情况接收
                                // .setContentAvailable(true)
 
                                .build())
 
 
                        .build())
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
 
                        .setMsgContent(jpush.getMsgContent())
 
                        .setTitle(jpush.getMsgTitle())
 
                        .addExtra("message extras key",jpush.getExtendParams().toString())
 
                        .build())
 
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；
                        .setTimeToLive(86400)
 
                        .build())
 
                .build();
 
    }
 
    /**
     * @Description: 构建推送消息到所有Android的用户 .<br>
     * @param jpush 推送消息实体类 .<br>
     * @return PushPayload .<br> 
     * @author 郑成功 .<br>
     * @date 2018-3-16 下午12:50:36.<br>
     */
    private static PushPayload buildPushObjToAndroid(JpushEntity jpush) {
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.all())
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(jpush.getNoticeTitle())
                                .setTitle(jpush.getNoticeTitle())
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("androidNotification extras key",jpush.getExtendParams().toString())
                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(jpush.getMsgContent())
                        .setTitle(jpush.getMsgTitle())
                        .addExtra("message extras key",jpush.getExtendParams().toString())
                        .build())
 
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    }
 
    /**
     * @Description: 构建推送消息到所有IOS用户 .<br>
     * @param jpush 推送消息实体类 .<br>
     * @return PushPayload .<br> 
     * @author 郑成功 .<br>
     * @date 2018-3-16 下午12:50:36.<br>
     */
    private static PushPayload buildPushObjToIos(JpushEntity jpush) {
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.ios())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.all())
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(jpush.getNoticeTitle())
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("iosNotification extras key",jpush.getExtendParams().toString())
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                               // .setContentAvailable(true)
 
                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(jpush.getMsgContent())
                        .setTitle(jpush.getMsgTitle())
                        .addExtra("message extras key",jpush.getExtendParams().toString())
                        .build())
 
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    }
 
    /*public static void main(String[] args){
    	List<String> list = getJpushAliasDeviceList("4028ea815a3d2a8c015a3d2f8d2a0002",null);
    	for(String id:list){
    		JpushEntity jpush = new JpushEntity();
    		jpush.setRegisterId(id);
        	jpush.setNoticeTitle("汪明莎你好");
        	jpush.setMsgTitle("品质工程");
        	jpush.setMsgContent("恭喜你中奖100万");
        	jpush.put("other", "其他参数");
            if(JpushClientUtil.sendToRegister(jpush)){
                System.out.println("success");
            }
    	}
    	System.out.println(list);
    }*/
}
 
 
