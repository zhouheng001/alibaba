package com.aliyun.or.util;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * ftp上传下载工具类
 * <p>Title: FtpUtil</p>
 * <p>Description: </p>
 * <p>Company: www.zpark.com</p> 
 * @author	WangCF
 * @date	2017年11月5日下午8:11:51
 * @version 1.0
 */
public class FtpUtil {

	/**
	 * Description: 向FTP服务器上传文件 
	 * @param host FTP服务器hostname 
	 * @param port FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2017/11/05。文件的路径为默认目录+filePath
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile(String host, int port, String username, String password,
			String filename, String filePath, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}

			//配置文件的路径
			if (!ftp.changeWorkingDirectory(filePath)) {
				//如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)) continue;
					if (!ftp.changeWorkingDirectory(dir)) {
						if (!ftp.makeDirectory(dir)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(dir);
						}
					}
				}
			}

			//设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			//上传文件
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			try {
				SendEmailBySpringApi.sendEmailBySpringApi("1020886351@qq.com", "中国服务大厦微服务", e.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
}
