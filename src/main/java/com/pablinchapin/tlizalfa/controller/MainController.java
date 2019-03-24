/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.controller;


import com.pablinchapin.tlizalfa.entity.Category;
import com.pablinchapin.tlizalfa.entity.CustomerForm;
import com.pablinchapin.tlizalfa.entity.Product;
import com.pablinchapin.tlizalfa.model.CartInfo;
import com.pablinchapin.tlizalfa.model.CustomerInfo;
import com.pablinchapin.tlizalfa.model.ProductInfo;
import com.pablinchapin.tlizalfa.service.CategoryServiceImpl;
import com.pablinchapin.tlizalfa.service.ProductServiceImpl;
import com.pablinchapin.tlizalfa.util.CartUtils;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
            @RequestParam(value = "categoryId", defaultValue="1", required=false) Long categoryId,
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
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("productDetail");
        
        Optional<Product> product;
        Product productData = new Product();
        product = productService.getProductDetail(id);
        
        if(product.isPresent()){
            productData = product.get();
        }
        
        logger.info(productData.toString());
        
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        
        mav.addObject("productInfo", productData);
        mav.addObject("cartForm", cartInfo);
        
    return mav;
    }
    
    
    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCartHandler(
            HttpServletRequest request
    ){
    
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shoppingCart");
        
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        
        mav.addObject("cartForm", cartInfo);
        
    return mav;
    }
    
    
    @PostMapping("/shoppingCart")
    public ModelAndView shoppingCartAddProduct(
            HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "") Long id,
            @RequestParam(value = "quantity", defaultValue = "1") int quantity,
            @RequestParam(value = "action", defaultValue = "add") String action
    ){
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shoppingCart");
        
        Optional<Product> product;
        Product productData = new Product();
        
        if(id != null && id > 0){
            product = productService.getProductDetail(id);
            
            if(product.isPresent()){
                productData = product.get();
                
                CartInfo cartInfo = CartUtils.getCartInSession(request);
                ProductInfo productInfo = new ProductInfo(productData);
                
                if(action.equals("add")){
                    cartInfo.addProduct(productInfo, quantity);
                }else{
                        cartInfo.removeProduct(productInfo);
                }
            }
        }
        
        
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        
        mav.addObject("cartForm", cartInfo);
        
    return mav;
    }
    
    
    @GetMapping("/shoppingCartCheckout")
    public ModelAndView shoppingCartCheckout(
            HttpServletRequest request            
    ){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("shoppingCartCheckout");
        
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        
        if(customerInfo != null){
            CustomerForm customerForm = new CustomerForm(customerInfo);
            mav.addObject("customerForm", customerForm);
        }else{
                mav.addObject("customerForm", new CustomerForm());
        }
        
        
        if(cartInfo == null || cartInfo.isEmpty()){
            return new ModelAndView("redirect:/shoppingCart");
        }
        
        mav.addObject("cartForm", cartInfo);
        
        
    return mav;
    }
    
    
    @PostMapping("/shoppingCartCheckout")
    public ModelAndView shoppinCartCheckoutHandler(
            HttpServletRequest request,
            @ModelAttribute("customerForm") @Validated CustomerForm customerForm,
            BindingResult result
    ){
        
        ModelAndView mav = new ModelAndView();
        
        
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        
        if(result.hasErrors()){
        
            for(Object object : result.getAllErrors()) {
                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;

                    System.out.println("/shoppingCartCheckout POST fieldError-> " +fieldError.getCode());
                }

                if(object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;

                    System.out.println("/shoppingCartCheckout POST objectError-> " +objectError.getCode());
                }
            }
            
            customerForm.setValid(false);
            
            mav.setViewName("shoppingCartCheckout");
            
            mav.addObject("cartForm", cartInfo);
            mav.addObject("customerForm", customerForm);
            
            return mav;
        }
        
        customerForm.setValid(true);
        
        CustomerInfo customerInfo = new CustomerInfo(customerForm);
        cartInfo.setCustomerInfo(customerInfo);
        
        //mav.setViewName("shoppingCartConfirmation");
        //mav.addObject("cartForm", cartInfo);
        //mav.addObject("customerForm", customerForm);
        
        return new ModelAndView("redirect:/shoppingCartConfirmation");
    
    //return mav;
    }
    
    
    @GetMapping("/shoppingCartConfirmation")
    public ModelAndView shoppingCartConfirmation(
            HttpServletRequest request
    ){
        
        ModelAndView mav = new ModelAndView();
        
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        
        mav.setViewName("shoppingCartConfirmation");
        
        mav.addObject("cartForm", cartInfo);
        mav.addObject("customerForm", customerInfo);
        
    return mav;
    }
    
    
    @PostMapping("/shoppingCartConfirmation")
    public ModelAndView shoppingCartConfirmationHandler(
            HttpServletRequest request
    ){
        
        //ModelAndView mav = new ModelAndView();
        
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        
        CartUtils.removeCartInSession(request);
        CartUtils.storeLastOrderedCartInSession(request, cartInfo);
        
    return new ModelAndView("redirect:/shoppingCartFinalize");
    }
    
    
    @GetMapping("/shoppingCartFinalize")
    public ModelAndView shoppingCartFinalize(
            HttpServletRequest request
    ){
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("shoppingCartFinalize");
        
    return mav;
        
    }
    
    
}
