package cn.why.thesis.mapper;


import java.util.HashMap;
import java.util.List;

import cn.why.thesis.entity.Major;
import cn.why.thesis.entity.Student;

public interface MajorMapper {

	public List<Major> querymajor(HashMap<String,Integer> hm);
	public int querymajorCount();
	
	public int updatemajor(Major major);
	
	public List<Major> selectMajorInfo();
	
	public int delmajor(Major major);
	
	public Major findById(int id);
	
	public int insertMajor(Major major);
	
	public List<Major> findAllXy();
	
	public List<Major> findZyByXy(String xy);
}
