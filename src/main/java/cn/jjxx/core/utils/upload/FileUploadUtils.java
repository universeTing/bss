package cn.jjxx.core.utils.upload;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.core.utils.upload.exception.FileNameLengthLimitExceededException;
import cn.jjxx.core.utils.upload.exception.InvalidExtensionException;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileUploadUtils {

	// 默认大小 50M
	public static final long DEFAULT_MAX_SIZE = 52428800;

	// 默认上传的地址
	private static String defaultBaseDir = "upload";
	
	//压缩文件的后缀
	public static final String MIN_FILE = "_min";

	// 默认的文件名最大长度
	public static final int DEFAULT_FILE_NAME_LENGTH = 200;

	public static final String[] IMAGE_EXTENSION = { "bmp", "gif", "jpg", "jpeg", "png" };

	public static final String[] FLASH_EXTENSION = { "swf", "flv" };

	public static final String[] MEDIA_EXTENSION = { "swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
			"asf", "rm", "rmvb" };

	public static final String[] DEFAULT_ALLOWED_EXTENSION = {
			// 图片
			"bmp", "gif", "jpg", "jpeg", "png",
			// word excel powerpoint
			"doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
			// 压缩文件
			"rar", "zip", "gz", "bz2",
			// pdf
			"pdf" };

	public static void setDefaultBaseDir(String defaultBaseDir) {
		FileUploadUtils.defaultBaseDir = defaultBaseDir;
	}

	public static String getDefaultBaseDir() {
		return defaultBaseDir;
	}

	/**
	 * 以默认配置进行文件上传
	 *
	 * @param request
	 *            当前请求
	 * @param file
	 *            上传的文件
	 * @param result
	 *            添加出错信息
	 * @return
	 * @throws IOException
	 * @throws FileNameLengthLimitExceededException
	 * @throws InvalidExtensionException
	 * @throws FileSizeLimitExceededException
	 */
	public static final String upload(HttpServletRequest request, MultipartFile file)
			throws FileSizeLimitExceededException, InvalidExtensionException, FileNameLengthLimitExceededException,
			IOException {
		return upload(request, file, DEFAULT_ALLOWED_EXTENSION);
	}

	/**
	 * 以默认配置进行文件上传
	 *
	 * @param request
	 *            当前请求
	 * @param file
	 *            上传的文件
	 * @param result
	 *            添加出错信息
	 * @param allowedExtension
	 *            允许上传的文件类型
	 * @return
	 * @throws IOException
	 * @throws FileNameLengthLimitExceededException
	 * @throws InvalidExtensionException
	 * @throws FileSizeLimitExceededException
	 */
	public static final String upload(HttpServletRequest request, MultipartFile file, String[] allowedExtension)
			throws FileSizeLimitExceededException, InvalidExtensionException, FileNameLengthLimitExceededException,
			IOException {
		return upload(request, getDefaultBaseDir(), file, allowedExtension, DEFAULT_MAX_SIZE, true);
	}

	/**
	 * 文件上传
	 *
	 * @param request
	 *            当前请求 从请求中提取 应用上下文根
	 * @param baseDir
	 *            相对应用的基目录
	 * @param file
	 *            上传的文件
	 * @param allowedExtension
	 *            允许的文件类型 null 表示允许所有
	 * @param maxSize
	 *            最大上传的大小 -1 表示不限制
	 * @param needDatePathAndRandomName
	 *            是否需要日期目录和随机文件名前缀
	 * @return 返回上传成功的文件名
	 * @throws InvalidExtensionException
	 *             如果MIME类型不允许
	 * @throws FileSizeLimitExceededException
	 *             如果超出最大大小
	 * @throws FileNameLengthLimitExceededException
	 *             文件名太长
	 * @throws IOException
	 *             比如读写文件出错时
	 */
	public static final String upload(HttpServletRequest request, String baseDir, MultipartFile file,
			String[] allowedExtension, long maxSize, boolean needDatePathAndRandomName)
			throws InvalidExtensionException, FileSizeLimitExceededException, IOException,
			FileNameLengthLimitExceededException {

		int fileNamelength = file.getOriginalFilename().length();
		if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
			throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNamelength,
					FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
		}
		final String filePath =extractUploadDir(request);
		assertAllowed(file, allowedExtension, maxSize);
		final String filename = extractFilename(file, baseDir, needDatePathAndRandomName);

		final File desc = getAbsoluteFile(extractUploadDir(request), filename);
		if(Arrays.asList(FileUploadUtils.IMAGE_EXTENSION).contains(StringUtils.getExtensionName(filename))){
			Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.2f).toFile(desc);	
			new Thread(new Runnable() {
		        @Override
		        public void run() {
		        	try {
						//存储小图
						String minBaseDir = filename.substring(0,filename.lastIndexOf("/")+1);
						String extend = filename.substring(filename.lastIndexOf("."));
						String minFileName = filename.substring(filename.lastIndexOf("/")+1,filename.lastIndexOf("."))+FileUploadUtils.MIN_FILE+extend;
						File minFile = getAbsoluteFile(filePath, minBaseDir+minFileName);
						Thumbnails.of(new FileInputStream(desc)).scale(0.1f).outputQuality(0.2f).toFile(minFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
		        }
		    }).start();
		}else{
			file.transferTo(desc);
		}
		return filename;
	}

	private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException {

		uploadDir = FilenameUtils.normalizeNoEndSeparator(uploadDir);

		File desc = new File(uploadDir + "/" + filename);

		if (!desc.getParentFile().exists()) {
			desc.getParentFile().mkdirs();
		}
		if (!desc.exists()) {
			desc.createNewFile();
		}
		return desc;
	}

	public static final String extractFilename(MultipartFile file, String baseDir, boolean needDatePathAndRandomName)
			throws UnsupportedEncodingException {
		String filename = file.getOriginalFilename();
		int slashIndex = filename.indexOf("/");
		if (slashIndex >= 0) {
			filename = filename.substring(slashIndex + 1);
		}
		if (needDatePathAndRandomName) {
			filename = baseDir + "/" + datePath() + "/" + StringUtils.randomUUID() + "."
					+ StringUtils.getExtensionName(filename);
		} else {
			filename = baseDir + "/" + filename;
		}
		return filename;
	}

	/**
	 * 日期路径 即年/月/日 如2013/01/03
	 *
	 * @return
	 */
	private static final String datePath() {
		Date now = new Date();
		return DateFormatUtils.format(now, "yyyy/MM/dd");
	}

	/**
	 * 是否允许文件上传
	 *
	 * @param file
	 *            上传的文件
	 * @param allowedExtension
	 *            文件类型 null 表示允许所有
	 * @param maxSize
	 *            最大大小 字节为单位 -1表示不限制
	 * @return
	 * @throws InvalidExtensionException
	 *             如果MIME类型不允许
	 * @throws FileSizeLimitExceededException
	 *             如果超出最大大小
	 */
	public static final void assertAllowed(MultipartFile file, String[] allowedExtension, long maxSize)
			throws InvalidExtensionException, FileSizeLimitExceededException {

		String filename = file.getOriginalFilename();
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
			if (allowedExtension == IMAGE_EXTENSION) {
				throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
						filename);
			} else if (allowedExtension == FLASH_EXTENSION) {
				throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
						filename);
			} else if (allowedExtension == MEDIA_EXTENSION) {
				throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
						filename);
			} else {
				throw new InvalidExtensionException(allowedExtension, extension, filename);
			}
		}

		long size = file.getSize();
		if (maxSize != -1 && size > maxSize) {
			throw new FileSizeLimitExceededException("not allowed upload upload", size, maxSize);
		}
	}

	/**
	 * 判断MIME类型是否是允许的MIME类型
	 *
	 * @param extension
	 * @param allowedExtension
	 * @return
	 */
	public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
		for (String str : allowedExtension) {
			if (str.trim().equalsIgnoreCase(extension.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 提取上传的根目录 默认是应用的根
	 *
	 * @param request
	 * @return
	 */
	public static final String extractUploadDir(HttpServletRequest request) {
		return request.getServletContext().getRealPath("/");
	}

	public static final void delete(HttpServletRequest request, String filename) throws IOException {
		if (StringUtils.isEmpty(filename)) {
			return;
		}
		File desc = getAbsoluteFile(extractUploadDir(request), filename);
		if (desc.exists()) {
			desc.delete();
		}
	}
	
	/**
	 * @Description: 获取指定文件夹下的文件和文件夹 .<br>
	 * @param @param path 文件夹路径.<br>
	 * @param fileList 文件集合.<br>
	 * @param floderList 文件夹集合.<br>   
	 * @author 郑成功 .<br>
	 * @date 2018-3-28 上午11:50:38.<br>
	 */
	public static void traverseFolder(String path,List<String> fileList,List<String> floderList){
		  File file = new File(path);
		  if(fileList==null){
			  fileList = new ArrayList<String>();
		  }
		  if(floderList==null){
			  floderList = new ArrayList<String>();
		  }
		  if (file.exists()) {
			  File[] files = file.listFiles();
			  if(files.length>0){
				  for (File file2 : files) {
					  if (file2.isDirectory()) {
						  floderList.add(file2.getAbsolutePath());
						  traverseFolder(file2.getAbsolutePath(),fileList,floderList);
					  }else{
						  fileList.add(file2.getAbsolutePath());
					  }
				  }
			  }
		  }
	  }
}
