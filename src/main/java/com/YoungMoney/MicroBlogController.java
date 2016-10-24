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
    public String home(Model model, HttpSession session) {
        String name = (String) session.getAttribute("username");
        User user = users.findByUsername(name);

        Iterable<Message> messageList = messages.findAll();

        for (Message m : messageList) {
            m.isMe = m.user.username.equals(name);
        }
        model.addAttribute("messages", messageList);
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLogin() throws Exception {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) throws Exception {
        User user = users.findByUsername(username);
        if (user == null) {
            user = new User(username,password);
            users.save(user);
        }
        else if(!password.equals(user.password)) {
            throw new Exception("Wrong password!");
        }
        session.setAttribute("username",username);
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage(HttpSession session, String text) throws Exception {
        String name = (String) session.getAttribute("username");
        User user = users.findByUsername(name);
        if(user == null) {
            throw new Exception("you're not logged in!");
        }
        Message message = new Message(text, user);
        messages.save(message);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage(int id, HttpSession session) throws Exception {
        String name = (String) session.getAttribute("username");
        User user = users.findByUsername(name);
        Message m = messages.findOne(id);
        if (user == null) {
            throw new Exception("You're not logged in!");
        }
        else if (!user.username.equals(m.user.username)) {
            throw new Exception("Not yours to delete.");
        }
        messages.delete(m);
        return "redirect:/";
    }

    @RequestMapping(path = "/edit-message", method = RequestMethod.GET)
    public String getEdit(Model model, HttpSession session, int id) throws Exception {
        String name = (String) session.getAttribute("username");
        User user = users.findByUsername(name);
        Message m = messages.findOne(id);
        if (user == null) {
            throw new Exception("you're not logged in.");
        }
        model.addAttribute("user", user);
        model.addAttribute("message", m);
        return "edit";
    }

    @RequestMapping(path = "/edit-message", method = RequestMethod.POST)
    public String edit(HttpSession session, String text, Integer id) throws Exception{
        String name = (String) session.getAttribute("username");
        User user = users.findByUsername(name);
        Message m = messages.findOne(id);
        if(user==null) {
            throw new Exception("Not logged in!");
        }
        else if(!user.username.equals(m.user.username)) {
            throw new Exception("Not yours to edit.");
        }
        m.setText(text);
        messages.save(m);
        return "redirect:/";
    }

    @RequestMapping(path = "logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
