package com.YoungMoney;

import org.springframework.beans.factory.access.el.SpringBeanELResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.net.www.http.Hurryable;
import javax.servlet.http.HttpSession;

/**
 * Created by stevenburris on 10/22/16.
 */
@Controller
public class MicroBlogController {
    @Autowired
    MessagesRepo messages;

    @Autowired
    UserRepo users;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        Iterable<Message> messageList = messages.findAll();
        model.addAttribute("messages", messageList);
        return "index";
    }

    @RequestMapping(path = "add-message", method = RequestMethod.POST)
    public String addMessage(String text) {
        Message message = new Message(text);
        messages.save(message);
        return "redirect:/";
    }

    @RequestMapping(path = "delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id) {
//        int message2delete =
//        int delete = messages.findOne(id);

        return "redirect:/";
    }
}
