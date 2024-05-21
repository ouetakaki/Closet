package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.data.ClosetDataInterface;
import com.example.demo.data.ClosetDataService;
import com.example.demo.model.Closet;
import com.example.demo.repositories.ClosetRepository;


@RestController
public class ClosetController {
	@Autowired
	ClosetDataService service;
	@Autowired
	private ClosetRepository repository;

	// 動的データが関係しないので廃止
	@GetMapping("/")
	public ModelAndView index(ModelAndView mv) {
		System.out.println("index");
		mv.setViewName("index");
		mv.addObject("title", "サンプル");
		mv.addObject("msg", "これはサンプルのメッセージです。");
		return mv;
	}
	
	@GetMapping("/closet")
	@CrossOrigin
	public List<? extends ClosetDataInterface> fruits() {
		System.out.println("closet/index");
		return service.getAll();
	}

	@GetMapping("/closet/{id}")
	@CrossOrigin
	public ClosetDataInterface closetById(@PathVariable int id) {
		System.out.println("closet/findById");
		return service.getById(id);
	}

	// 動的データが関係しないので廃止
	@GetMapping("/closet/add")
	public ModelAndView add(ModelAndView mv) {
		System.out.println("closet/add");
		mv.setViewName("closet/add");
		mv.addObject("title", "新規登録");
		mv.addObject("msg", "新たに情報を登録します");
		return mv;
	}
	
//	 動的データが関係しないので廃止
	@GetMapping("/closet/update")
	public ModelAndView update(ModelAndView mv, @RequestParam("id") Integer id) {
		System.out.println("closet/update");
		mv.setViewName("closet/update");
		mv.addObject("title", "更新");
		mv.addObject("msg", "ID=" + id + "の登録情報を更新します");
		Optional<Closet> form = this.repository.findById(id);
		mv.addObject("form", form.orElse(new Closet()));
		return mv;
	}

	// 動的データが関係しないので廃止
	@GetMapping("/closet/delete")
	public ModelAndView delete(ModelAndView mv, @RequestParam("id") Integer id) {
		System.out.println("closet/delete");
		mv.setViewName("closet/delete");
		mv.addObject("title", "削除");
		mv.addObject("msg", "ID=" + id + "の登録情報を削除します");
		Optional<Closet> form = this.repository.findById(id);
		mv.addObject("form", form.orElse(new Closet()));
		return mv;
	}

	@PostMapping("/closet/add")
	@CrossOrigin
	public int add(@RequestBody Closet closet) {
		System.out.println("closet/add(post)");
		return service.add(closet);
	}

	@PostMapping("/closet/update")
	@CrossOrigin
	public int update(@RequestBody Closet closet) {
		System.out.println("fruits/update(post)");
		return service.add(closet);
	}

	@PostMapping("/closet/delete")
	@CrossOrigin
	public void delete(@RequestBody Closet closet) {
		System.out.println("closet/delete(post)");
		service.delete(closet);
	}
	
	// 動的データが関係しないので廃止
	@GetMapping("/closet/find")
	public ModelAndView find(ModelAndView mv) {
	  mv.setViewName("/closet/find");
	  mv.addObject("title", "検索");
	  mv.addObject("msg", "クローゼットを検索します。");
	  mv.addObject("find","");
	  return mv;
	}
	 
	@PostMapping("/closet/find")
	@CrossOrigin
	public List<? extends ClosetDataInterface> find(@RequestParam("find") String find) {
		System.out.println("closet/find(post)");
		return service.findByNameLike(find);
	}
}
