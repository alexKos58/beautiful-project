//package com.example.security.controllers;
//
//import com.example.security.enumm.Status;
//import com.example.security.models.Cart;
//import com.example.security.models.Order;
//import com.example.security.models.Person;
//import com.example.security.models.Product;
//import com.example.security.repository.CartRepository;
//import com.example.security.repository.OrderRepository;
//import com.example.security.repository.ProductRepository;
//import com.example.security.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class UserController {
//    private final ProductService productService;
//
//    private final ProductRepository productRepository;
//
//    private final CartRepository cartRepository;
//
//    private final OrderRepository orderRepository;
//
//
//    @GetMapping("/cart/add/{id}")
//    public String addProductInCart(@PathVariable("id") int id, Model model, Person person) {
//        Product product = productService.getProductId(id);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long id_person = person.getId();
//        Cart cart = Cart.builder().id(id_person.intValue()).productId(product.getId()).build();
//        cartRepository.save(cart);
//        return "redirect:/cart";
//    }
//
//    @GetMapping("/cart")
//    public String cart(Model model, Person person) {
//        List<Product> productList = getProductList(person.getId());
//
//        float price = 0;
//        for (Product product : productList) {
//            price += product.getPrice();
//        }
//
//        model.addAttribute("price", price);
//        model.addAttribute("cart_product", productList);
//        return "/person/user/cart";
//    }
//
//    @GetMapping("/cart/delete/{id}")
//    public String deleteProductFromCart(Model model, @PathVariable("id") int id, Person person) {
//        List<Product> productList = getProductList(person.getId());
//        cartRepository.deleteCartByProductId(id);
//        return "redirect:/cart";
//    }
//
//    @GetMapping("/order/create")
//    public String order(Person person) {
//        List<Product> productList = getProductList(person.getId());
//
//        float price = 0;
//        for (Product product : productList) {
//            price += product.getPrice();
//        }
//
//        String number = "1000";
//        if (orderRepository.count() != 0) {
//            int maxId = orderRepository.findMaxId();
//            Order order = orderRepository.findById(maxId).orElseThrow();
//            number = String.valueOf(Integer.parseInt(order.getNumber()) + 1);
//        }
//        for (Product product : productList) {
//            Order newOrder = Order.builder().number(number).product(product).person(person).price(product.getPrice())
//            .count(1).status(Status.Принят).build();
//            orderRepository.save(newOrder);
//            cartRepository.deleteCartByProductId(product.getId());
//        }
//        return "redirect:/orders";
//    }
//
//    @GetMapping("/orders")
//    public String orderUser(Model model, Person person) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        List<Order> orderList = orderRepository.findByPerson(person);
//        model.addAttribute("orders", orderList);
//        return "/person/user/orders";
//    }
//
//
//    private List<Product> getProductList(Long id) {
//
//        List<Cart> cartList = cartRepository.findByPersonId(id);
//        List<Product> productList = new ArrayList<>();
//
//        for (Cart cart : cartList) {
//            productList.add(productService.getProductId(cart.getProductId()));
//        }
//        return productList;
//    }
//
//    private void findAllProductsByTitleAndPriceAndCategory(String search, String ot, String Do, String contract, Model model) {
//        if (!contract.isEmpty()) {
//            switch (contract) {
//                case "furniture" -> model.addAttribute("search_product", productRepository.findByCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
//                case "appliances" -> model.addAttribute("search_product", productRepository.findByCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 2));
//                case "clothes" -> model.addAttribute("search_product", productRepository.findByCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 3));
//            }
//        } else {
//            model.addAttribute("search_product", productRepository.findByTitleOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
//        }
//    }
//
//    private void findAllProductsByTitleAndCategory(String search, @RequestParam(value = "contract", required = false, defaultValue = "") String contract, Model model) {
//        switch (contract) {
//            case "furniture" -> model.addAttribute("search_product", productRepository.findByCategoryOrderByPriceAsc(search.toLowerCase(), 1));
//            case "appliances" -> model.addAttribute("search_product", productRepository.findByCategoryOrderByPriceAsc(search.toLowerCase(), 2));
//            case "clothes" -> model.addAttribute("search_product", productRepository.findByCategoryOrderByPriceAsc(search.toLowerCase(), 3));
//        }
//    }
//}
