package com.order.utils;

import com.order.config.ImageConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;


/**
 * 图像工具类
 */
@Slf4j
public class ImageUtil {
    private static final long SALT = 1233251;
	private static final String PATHSEPERATOR="/";

	/**
	 * 获取详情图片上传相对目录
	 * @param imgFileName
	 * @return
	 */
	public static String getDetailImageSourceFileRelativePath(String imgFileName) {
		StringBuilder sb = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		sb.append(calendar.get(Calendar.YEAR)).append(PATHSEPERATOR)
				.append(getMonthDay(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)))
                .append("/")
                .append(imgFileName)
                .append(".jpg");
		return sb.toString();
	}

	/**
	 * 获取详情图片上传绝对目录（原图片目录）
	 * @param imgFileName
	 * @return
	 */
	public static String getDetailImageSourceFileAbsolutePath(String BasePath,String imgFileName) {
	    log.info("【获得商品图片根目录】，路径为{}",BasePath);
		return BasePath + getDetailImageSourceFileRelativePath(imgFileName);
	}

	/**
	 * 获取详情图片生成相对目录
	 * @param imgId
	 * @param moduleName
	 * @param imageIndex
	 * @return
	 *//*
	public static String getDetailImageGenerateFileRelativePath(Long imgId, String moduleName, Integer imageIndex) {
		Calendar calendar = Calendar.getInstance();
		String imgFileName = generateFileName(imageConfig.getModule(), imgId);
		StringBuilder sb = new StringBuilder();
		sb.append(calendar.get(Calendar.YEAR)).append(PATHSEPERATOR)
				.append(getMonthDay(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))).append("/").append(imgFileName);
		return sb.toString();
	}*/

	/**
	 *生成图片名称
	 * @param moduleName
	 * @param imageId
     * @return
     */
	public static String generateFileName(String moduleName, Long imageId) {
		String fileNameHex = toHex32(imageId ^ 4376823).toLowerCase();
		return (moduleName  + fileNameHex).toLowerCase() + ".jpg";
	}
	/**
     * 数字转成32位
     * @param number
     * @return
     */
    //十进制:32 => 32进制:10
    private static String toHex32(Long number) {
        String result = "";
        if (number <= 0) {
            return "0";
        } else {
            while (number != 0) {
                result = "0123456789ABCDEFGHIJKLMNOPQRSTUV".substring((int)(number % 32), (int)(number % 32 + 1)) + result;
                number = number / 32;
            }
            return result;
        }
    }

    private static String getMonthDay(int month, int day) {
        String monthDay;
        if (month < 9) {
            monthDay = "0" + (month + 1);
        } else {
            monthDay = (month + 1) + "";
        }
        if (day < 10) {
            monthDay = monthDay + "0" + day;
        } else {
            monthDay = monthDay + day;
        }
        return monthDay;
    }
}