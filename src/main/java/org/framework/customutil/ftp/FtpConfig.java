package org.framework.customutil.ftp;

/**
 * @Title: FtpConfig.java .<br>
 * @Package org.framework.customutil.ftp .<br>
 * @Description: ftp登陆配置信息  .<br>
 * @author 郑成功 .<br>
 * @email a876459952@qq.com .<br>
 * @date 2017-11-24 上午10:44:54.<br>
 * @version V1.0.<br>
 */
public class FtpConfig {  
    //服务器地址名称  
    private String server;  
    //端口号  
    private int port;  
    //用户名称  
    private String username;  
    //密码  
    private String password;  
    //工作目录  
    private String location;  
     
    public String getServer() {  
        return server;  
    }  
  
    public void setServer(String server) {  
        this.server = server;  
    }  
  
    public int getPort() {  
        return port;  
    }  
  
    public void setPort(int port) {  
        this.port = port;  
    }  
  
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public String getLocation() {  
        return location;  
    }  
  
    public void setLocation(String location) {  
        this.location = location;  
    }  
}  