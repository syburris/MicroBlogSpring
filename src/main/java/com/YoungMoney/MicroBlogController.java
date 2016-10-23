package com.YoungMoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(path = "login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public String login(String username, String password) {
        User user = new User(username, password);
        users.save(user);
        return "redirect:/";
    }
}
