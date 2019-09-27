package cn.why.thesis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.why.thesis.entity.Item;
import cn.why.thesis.mapper.ItemMapper;

@Service
public class ItemService {
	@Autowired
	private ItemMapper itemMapper;

	public List<Item> queryItemList() {
		return itemMapper.queryItemList();
	}
	
}
