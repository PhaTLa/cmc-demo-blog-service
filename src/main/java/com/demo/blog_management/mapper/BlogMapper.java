package com.demo.blog_management.mapper;

import com.demo.blog_management.dto.BlogDto;
import com.demo.blog_management.feign.dto.Image;
import com.demo.blog_management.model.BlogVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Insert("INSERT INTO blog (id, title, description, created_id)" +
            "VALUES (#{id},#{title}, #{description}, #{createdId})")
    int insertBlog(BlogVo blogVo);

    public List<BlogVo> getBlogs(@Param(value = "input") BlogDto input, @Param("current_page") int current_page, @Param("page_size") int page_size);

    @Select("SELECT * from blog WHERE id = #{blogId}")
    @Results(value = {
            @Result(property = "id", column = "id")
            , @Result(property = "userId", column = "user_id")
            , @Result(property = "title", column = "title")
            , @Result(property = "description", column = "description")})
    public BlogVo getBlogById(@Param(value = "blogId") String blogId);

    @Delete("delete from blog WHERE id = #{blogId}")
    public int deleteBlogById(@Param(value = "blogId") String blogId);

    @Update("UPDATE blog\n" +
            "SET tile = #{title}\n" +
            "description = #{description}\n" +
            "updated_dtm = CURRENT_TIMESTAMP()" +
            "updated_id = #{updatedId}")
    public int updateBlogById(@Param(value = "blogDto") BlogDto input);

    public int countAllBlogs(@Param(value = "input") BlogDto input);

    //TODO: remove
    @Select("SELECT url from image WHERE blog_id = #{blogId}")
    @Results(value = {
            @Result(property = "url", column = "url")})
    List<Image> getImagesByBlogId(String blogId);

    //TODO: remove
    @Select("SELECT cate.id from product_category as pc\n"
            + "INNER JOIN category as cate on cate.id = pc.category_id\n"
            + "WHERE pc.blog_id = #{blogId}")
    List<Long> getCategoryIdsByBlogId(String blogId);
}
