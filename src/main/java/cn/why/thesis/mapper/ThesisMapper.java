package cn.why.thesis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.why.thesis.entity.Thesis;

public interface ThesisMapper {
	
	public List<Thesis> selThesisByAdminAndxhAndsfzh(@Param("xh")String xh,@Param("xm")String xm,@Param("sfzh")String sfzh);
	
	public int insertThesis(Thesis thesis);

	public Thesis queryByXh(String xh);

	public int updateThesis(Thesis thesis);
}
