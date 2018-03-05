package me.travisgray.Controller;

import me.travisgray.Models.Item;
import me.travisgray.Models.User;
import me.travisgray.Repositories.ItemRepository;
import me.travisgray.Repositories.UserRepository;
import me.travisgray.Service.UserService;
import org.hibernate.pretty.MessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ${TravisGray} on 11/13/2017.
 */

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(){

        return "index";
    }

    @RequestMapping("/login")
    public String login(){



        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){

        return "admin";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processregistration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model ){

        model.addAttribute("user",user);
        if(result.hasErrors()){
            return "registration";
        }else{
            userService.saveUser(user);
            model.addAttribute("message","User Account Successfully Created");
        }
        return "index";
    }

    @GetMapping("/additem")
    public String additemForm(Model model){
        model.addAttribute("item", new Item());

        return "additemform";
    }


    //    Must pass created book entry here then save to repository model for thymeleaf loop
    @PostMapping("/additem")
    public String processitemsForm(@Valid @ModelAttribute("item") Item  item, BindingResult result, Model model, Authentication auth){

        if (result.hasErrors()){
            return "additemform";
        }

        item.setItemStatus("Missing");
        User user = userRepository.findByUsername(auth.getName());
        item.addUser(user);
        userRepository.save(user);
        itemRepository.save(item);
        model.addAttribute("itemslist",itemRepository.findAll());
        return "itemlist";
    }


    @GetMapping("/list")
    public String listitems(Model model){
        model.addAttribute("itemslist",itemRepository.findAll());
        return "itemlist";
    }


    @GetMapping("/update/{id}")
    public String updateItems(@PathVariable("id") long id, Model model){
        model.addAttribute("item",itemRepository.findOne(id));
        return "redirect:/additem";
    }

    @GetMapping("/delete/{id}")
    public String deleteItems(@PathVariable("id") long id, Model model){
        model.addAttribute("item",itemRepository.findOne(id));
        itemRepository.delete(id);
        return "redirect:/list";
    }

    @RequestMapping("/secure")
    public String secure(HttpServletRequest request, Authentication authentication, Principal principal) {
        Boolean isAdmin = request.isUserInRole("ADMIN");
        Boolean isUSer = request.isUserInRole("USER");
        UserDetails userDetails = (UserDetails)
                authentication.getPrincipal();
        String username = principal.getName();
        return "secure";
    }

    @GetMapping("/addtofound/{id}")
    public String additemtofoundlist(@PathVariable("id") long id, Model model, Authentication auth){

        Item item = itemRepository.findOne(id);
//        Must use database user not spring security user
        User user = userRepository.findByUsername(auth.getName());
        user.addItem(item);
        item.setItemStatus("Found");
        model.addAttribute("founditemslist", itemRepository.findOne(id));
        itemRepository.save(item);
        userRepository.save(user);
        model.addAttribute("userlist",userRepository.findAll());
        model.addAttribute("itemslist",userRepository.findAll());
        return "redirect:/list";
    }

    @GetMapping("/showfounditems")
    public String showallfounditems(Model model){
        model.addAttribute("founditemslist", itemRepository.findAllByItemStatus("Found"));
        return "founditemslist";
    }

    @GetMapping("/addtolost/{id}")
    public String additemtolostlist(@PathVariable("id") long id, Model model, Authentication auth){

        Item item = itemRepository.findOne(id);
//        Must use database user not spring security user
        User user = userRepository.findByUsername(auth.getName());
        user.addItem(item);
        item.setItemStatus("Lost");
        model.addAttribute("lostitemslist", itemRepository.findOne(id));
        itemRepository.save(item);
        userRepository.save(user);
        model.addAttribute("userlist",userRepository.findAll());
        model.addAttribute("itemslist",userRepository.findAll());
        return "redirect:/list";
    }

    @GetMapping("/showlostitems")
    public String showalllostitems(Model model){
        model.addAttribute("lostitemslist", itemRepository.findAllByItemStatus("Lost"));
        return "lostitemslist";
    }

    @GetMapping("/showpetitems")
    public String showallpetitems(Model model){
        model.addAttribute("itemslist", itemRepository.findAllByItemType("Pets"));
        return "lostitemslist";
    }

    @GetMapping("/showclothesitems")
    public String showallclothesitems(Model model,@ModelAttribute("item") Item item){

        model.addAttribute("itemslist", itemRepository.findAllByItemType("Clothes"));

        return "lostitemslist";
    }





    @GetMapping("/userlist")
    public String showuserlist(Model model, Authentication auth){

        User user1 = userRepository.findByUsername(auth.getName());
        model.addAttribute("useritems",itemRepository.findAllByUser(user1));


        return "useritemslist";

    }

//

    @PostMapping("/showclothes")
    public String showclotheslist(Model model){
        model.addAttribute("itemslist",itemRepository.findAllByItemTypeContainingIgnoreCase("clothes"));
        return "itemslist";
    }




    @GetMapping("/search")
    public String getSearch(){
        return "searchform";
    }

    @PostMapping("/search")
    public String showSearchResults(HttpServletRequest request, Model model){
        String searchItems = request.getParameter("search");
        model.addAttribute("search",searchItems);
//

//        Expecting multiple parameters or else will throw No parameter available Need to pass as many as are in constructor in Entity.
        model.addAttribute("itemsearch",itemRepository.findAllByItemTypeContainingIgnoreCase(searchItems));
//
        return "searchitemlist";
    }
}
