package com.codeup;

import com.codeup.models.Post;
import com.codeup.models.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    Posts postsRepository;

    @GetMapping()
    public String index(Model model){
        List<Post> postList = (List<Post>) postsRepository.findAll();
        List<Post> orderedPostList = new ArrayList<>();
        for (int i = postList.size()-1; i >= 0; i--) {
            orderedPostList.add(postList.get(i));
        }
        model.addAttribute("postList", orderedPostList);
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
        postsRepository.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String individualPost(@PathVariable long id, Model model){
        model.addAttribute("post", postsRepository.findOne(id));
        return "/posts/show";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable long id){
        postsRepository.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String updatePostGet(@PathVariable long id, Model model){
        model.addAttribute("post", postsRepository.findOne(id));
        return "/posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String updatePost(@PathVariable long id, @Valid Post post, Errors validation, Model model){
        if(validation.hasErrors()){
            model.addAttribute(validation.getAllErrors());
            model.addAttribute("post", post);
            return "posts/edit";
        }
        Post oldPost = postsRepository.findOne(id);
        oldPost.setTitle(post.getTitle());
        oldPost.setBody(post.getBody());
        postsRepository.save(oldPost);
        return "redirect:/posts/{id}";
    }
}
