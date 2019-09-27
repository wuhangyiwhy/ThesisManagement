package cn.why.thesis.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.protobuf.TextFormat.ParseException;

import cn.why.thesis.entity.ExcelBean;
import cn.why.thesis.entity.Major;
import cn.why.thesis.mapper.MajorMapper;
import cn.why.thesis.utils.ExcelUtil;

@Service
public class MajorService {
	@Autowired
	private MajorMapper majorMapper;
	public List<Major> querymajor(int page, int limit) {
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		hm.put("start", page*limit-limit);
		hm.put("pageCount",limit);
		List<Major> lsColl =majorMapper.querymajor(hm);
		return lsColl;
	}

	public int querymajorCount() {
		return majorMapper.querymajorCount();
	}
	
	public XSSFWorkbook exportExcelInfo(String salaryDate) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
	    //根据条件查询数据，把数据装载到一个list中
	    List<Major> list = majorMapper.selectMajorInfo();
	    List<ExcelBean> excel=new ArrayList<>();
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
	    XSSFWorkbook xssfWorkbook=null;
	    //设置标题栏
	    excel.add(new ExcelBean("学院","xy",0));
	    excel.add(new ExcelBean("专业","zy",0));
	    map.put(0, excel);
	    String sheetName = "学院信息表";
	    //调用ExcelUtil的方法
	    xssfWorkbook = ExcelUtil.createExcelFile(Major.class, list, map, sheetName);
	    return xssfWorkbook;

	}

	public int delmajor(Major major) {
		return majorMapper.delmajor(major);
	}

	public Major findById(int id) {
		return majorMapper.findById(id);
	}

	public int updateMajor(Major major) {
		
		return majorMapper.updatemajor(major);
	}

	public int insertMajor(Major major) {
		// TODO Auto-generated method stub
		return majorMapper.insertMajor(major);
	}

	public List<Major> findAllXy() {
		return majorMapper.findAllXy();
	}

	public List<Major> findZyByXy(String xy) {
		return majorMapper.findZyByXy(xy);
	}
}
