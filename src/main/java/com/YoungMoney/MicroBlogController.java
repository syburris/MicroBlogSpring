package com.YoungMoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by stevenburris on 10/22/16.
 */
@Controller
public class MicroBlogController {
    @Autowired
    MessagesRepo messages;

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
}
