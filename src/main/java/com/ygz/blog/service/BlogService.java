package com.ygz.blog.service;

import com.ygz.blog.po.Blog;
import com.ygz.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);
    Blog getAndConvert(Long id);
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);//分页查询
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(String query,Pageable pageable);
    Page<Blog> listBlog(Long tagId,Pageable pageable);
    List<Blog> listRecommend8logTop(Integer size);
    Blog saveBlog(Blog blog);//保存
    Blog updateBlog(Long id,Blog blog);//更新
    void deleteBlog(Long id);//删除

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();
}
