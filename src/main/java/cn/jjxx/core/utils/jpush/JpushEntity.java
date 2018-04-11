package cn.jjxx.core.utils.jpush;

import java.util.LinkedHashMap;

/**
 * @Title: JpushEntity.java .<br>
 * @Package cn.jjxx.core.utils.jpush .<br>
 * @Description: jpush推送消息封装实体 .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2018-3-16 上午11:44:36.<br>
 * @version V1.0.<br>
 */
public class JpushEntity {

	private String registerId;//注册Id
	private String noticeTitle;//通知的标题
	private String msgTitle;//消息标题
	private String msgContent;//消息内容
	private LinkedHashMap<String, Object> extendParams = new LinkedHashMap<String, Object>();;//扩展参数
	
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public LinkedHashMap<String, Object> getExtendParams() {
		return extendParams;
	}
	public void setExtendParams(LinkedHashMap<String, Object> extendParams) {
		this.extendParams = extendParams;
	}
	public void put(String key, Object value) {
		extendParams.put(key, value);
	}
	public void remove(String key) {
		extendParams.remove(key);
	}
	
}
