package com.cetpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cetpa.models.Product;
import com.cetpa.services.ProductService;

@Controller
public class ProductController 
{
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String getHomeView()
	{
		return "home.jsp";
	}

	@RequestMapping("insert-record")
	public String getInsertRecordView()
	{
		return "insert.jsp";
	}
	@RequestMapping("save-record")
	public String saveProductRecord(Product product)
	{
		productService.addProduct(product);
		return "save.jsp";
	}
	@RequestMapping("list")
	public String getProductList(Model model)
	{
		List<Product> productList=productService.getList();
		model.addAttribute("list",productList);
		return "product-list.jsp";
	}
	@RequestMapping("search-record")
	public String getSearchRecordView()
	{
		return "search.jsp";
	}
	@RequestMapping("show-record")
	public String getPrtoductRecord(int pid,Model model)
	{
		Product product=productService.getProduct(pid);
		if(product==null)
		{
			model.addAttribute("msg","Product with id "+pid+" not found");
			return "search.jsp";
		}
		model.addAttribute("product",product);
		return "search-record.jsp";
	}
}
