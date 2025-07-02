package com.cupk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cupk.common.Result;
import com.cupk.mapper.BookMapper;
import com.cupk.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookMapper bookMapper;

//    @GetMapping("/books")
//    Result selectAll(){
//        List<Book> booklist=bookMapper.selectList(null);
//        for(Book book:booklist){
//            System.out.println(book);
//        }
//        return Result.sucess(booklist);
//    }

    @GetMapping("/books")
    Result selectPages(@RequestParam(defaultValue = "")String name,
            @RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize){
        Page<Book> page=new Page<>(pageNum,pageSize);
       // bookMapper.selectPage(page,null);
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",name);
        bookMapper.selectPage(page,queryWrapper);

        System.out.println("当前的页数:"+page.getCurrent());
        System.out.println("总记录数:"+page.getTotal());
        System.out.println("总页数:"+page.getPages());
        System.out.println("是否有下一页:"+page.hasNext());
        return Result.sucess(page);
    }

    @GetMapping("/books/{id}")
    Result selectById(@PathVariable Integer id){
        Book book=bookMapper.selectById(id);
        if(book!=null){
            System.out.println("数据查询成功！");
            return Result.sucess(book);
        }else {
            System.out.println("数据查询失败");
            return Result.error();
        }
    }
    @DeleteMapping("/books/{id}")
    Result deleteById(@PathVariable Integer id){
        int i=bookMapper.deleteById(id);
        if(i>0){
            System.out.println("数据删除成功！");
            return Result.sucess();
        }else {
            System.out.println("数据删除失败");
            return Result.error();
        }
    }

    @DeleteMapping("/books")
    Result deleteBatch(@RequestBody List<Integer> ids){
        int i=bookMapper.deleteByIds(ids);
        if(i>0){
            System.out.println("数据删除成功！");
            return Result.sucess();
        }else {
            System.out.println("数据删除失败");
            return Result.error();
        }
    }

    @PostMapping("/books")
    Result insertBook(@RequestBody Book book){
        int i=bookMapper.insert(book);
        if(i>0){
            System.out.println("数据添加成功");
            return Result.sucess();
        }else  {
            System.out.println("添加失败");
            return Result.error();
        }
    }

    @PutMapping("/books")
    Result updateBook(@RequestBody Book book){
        int i=bookMapper.updateById(book);
        if(i>0){
            System.out.println("数据修改成功");
            return Result.sucess();
        }else{
            System.out.println("修改失败");
            return Result.error();
        }
    }
}
