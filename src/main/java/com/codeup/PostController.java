package com.codeup;

import com.codeup.dao.DaoFactory;
import com.codeup.dao.PostsDao;
import com.codeup.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String createPost(@Valid Post post, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute(validation.getAllErrors());
            model.addAttribute("post", post);
            return "posts/create";
        }
        DaoFactory.getPostsDao().insert(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String individualPost(@PathVariable int id, Model model){
        model.addAttribute("post", DaoFactory.getPostsDao().getPostByID(id));
        return "/posts/show";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable int id){
        DaoFactory.getPostsDao().deletePost(id);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String updatePostGet(@PathVariable int id, Model model){
        model.addAttribute("post", DaoFactory.getPostsDao().getPostByID(id));
        return "/posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@PathVariable int id, @Valid Post post, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute(validation.getAllErrors());
            model.addAttribute("post", post);
            return "posts/edit";
        }
        Post oldPost = DaoFactory.getPostsDao().getPostByID(id);
        oldPost.setTitle(post.getTitle());
        oldPost.setBody(post.getBody());
        DaoFactory.getPostsDao().updatePost(oldPost);
        return "redirect:/posts/{id}";
    }
}
