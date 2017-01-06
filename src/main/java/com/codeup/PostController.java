package com.codeup;

import com.codeup.dao.DaoFactory;
import com.codeup.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping()
    public String index(Model model){
        List<Post> postList = DaoFactory.getPostsDao().all();
        model.addAttribute("postList", postList);
        return "/posts/index";
    }

    @GetMapping("/create")
    public String createGet(Model model){
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post){
        DaoFactory.getPostsDao().insert(post);
        return "redirect:/posts";
    }
}
