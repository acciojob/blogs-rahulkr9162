package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageRepository imageRepository1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> blogs = new ArrayList<>();
        List<List<Blog>> listofblogs = new ArrayList<>();
        for(User user : userRepository1.findAll()){
            List<Blog> bloglist = user.getBlogList();
            listofblogs.add(bloglist);
        }
        for(List<Blog> x : listofblogs){
            for(Blog y : x){
                blogs.add(y);
            }
        }
        return blogs;
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        User user = userRepository1.findById(userId).get();
        blog.setUser_Info(user);

        //updating the blog details
        List<Blog> currentlistofBlogs = user.getBlogList();
        currentlistofBlogs.add(blog);
        user.setBlogList(currentlistofBlogs);


        //Updating the userInformation and changing its blogs
        userRepository1.save(user);

    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        Blog blog = findBlogById(blogId);
        image.setBlog_Info(blog);

        List<Image> imageList = blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);

        blogRepository1.save(blog);

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}
