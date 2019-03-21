/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.controller;


import com.pablinchapin.tlizalfa.entity.Category;
import com.pablinchapin.tlizalfa.entity.Product;
import com.pablinchapin.tlizalfa.service.CategoryServiceImpl;
import com.pablinchapin.tlizalfa.service.ProductServiceImpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pvargas
 */

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    CategoryServiceImpl categoryService;
    
    @Autowired
    ProductServiceImpl productService;
    
    
    @RequestMapping("/403")
    public String accessDenied(){
        return "/403";
    }
    
    
    @GetMapping("/")
    public ModelAndView home(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        
        final int maxResult = 5;
        final int maxNavigationPage = 10;
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        
        PageRequest pageable = PageRequest.of(page -1, size);
        
        Page<Category> categoryPage = categoryService.getAllCategories(pageable);
        
        int totalPages = categoryPage.getTotalPages();
        
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }
        
        //model.addObject("lachucha", "lachocha");
        
        
        //mav.addObject("articleList", articlePage.getContent());
        //return modelAndView;
        
        //mav.addAttribute("paginationResult", null);
        //mav.addAttribute("paginationResultCategory", null);
        mav.addObject("paginationResult", null);
        mav.addObject("paginationResultCategory", categoryPage);
        
    return mav;
    }
    
    
    @GetMapping("/productList")
    public ModelAndView listProductHandler(
            @RequestParam(value = "categoryId") Long categoryId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("productList");
        
        PageRequest pageable = PageRequest.of(page -1, size);
        PageRequest pageableCat = PageRequest.of(0, size);
        
        Page<Category> categoryPage = categoryService.getAllCategories(pageableCat);
        Page<Product> productPage = productService.getAllProductsByCategoryId(pageable, categoryId);
        
        int totalPages = productPage.getTotalPages();
        
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }
        
        
        mav.addObject("paginationResult", productPage);
        mav.addObject("paginationResultCategory", categoryPage);
        
        
    return mav;
    }
    
    
    @GetMapping("/productDetail")
    public ModelAndView viewProductHandler(
            HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "") Long id
    ){
        Optional<Product> product = null;
        
        product = productService.getProductDetail(id);
        
        logger.info(product.toString());
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("productDetail");
        
        mav.addObject("productInfo", product);
        
    return mav;
    }
    
    
}
