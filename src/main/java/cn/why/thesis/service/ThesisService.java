package cn.why.thesis.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.why.thesis.entity.Thesis;
import cn.why.thesis.mapper.ThesisMapper;

@Service
@Transactional(readOnly=true)
public class ThesisService {
	@Autowired
	private ThesisMapper thesismapper;
	
	@Transactional(readOnly=false)
	public int save(Thesis thesis) {
		
		return thesismapper.insertThesis(thesis);
	}

	public Thesis queryByXh(String xh) {
		
		return thesismapper.queryByXh(xh);
	}

	public int updateByXh(Thesis thesis) {
		
		return thesismapper.updateThesis(thesis);
	}
	
	
}
