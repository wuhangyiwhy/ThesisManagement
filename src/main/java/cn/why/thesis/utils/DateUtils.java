package cn.why.thesis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String formatDate(Date date,String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		return sdf.format(date);
	}
}
