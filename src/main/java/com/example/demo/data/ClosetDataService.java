package com.example.demo.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Closet;
import com.example.demo.repositories.ClosetRepository;

@Service
public class ClosetDataService {
	@Autowired
	private ClosetRepository repository;
	
	public List<? extends ClosetDataInterface> getAll() {
		System.out.println("Service:getAll");
		return repository.findAll();
	}
	
	public ClosetDataInterface getById(int id) {
		System.out.println("Service:getById id[" + id + "]");
		return repository.findById(id).orElse(null);
	}
	
	public List<? extends ClosetDataInterface> findByNameLike(String find) {
		System.out.println("Service:getByNameLike find[" + find + "]");
		return repository.findByNameLike("%" + find + "%");
	}
	
	/**
	 * 
	 * @param item
	 * @return 保存されたエンティティのID、成功しない場合0
	 */
	public int add(ClosetDataInterface item) {
		System.out.println("Service:add [" + item + "]");
		if (item instanceof Closet) {
			Closet savedItem = repository.saveAndFlush((Closet)item);
			return savedItem.getId();
		}
		return 0;
	}
	
	public void delete(ClosetDataInterface item) {
		System.out.println("Service:delete[" + item + "]");
		if (item instanceof Closet) {
			repository.delete((Closet) item);
		}
	}
}
