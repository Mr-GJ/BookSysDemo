package com.booksysdemo.demo.reposity;

import com.booksysdemo.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
/*
利用jpa简化数据库查询->调用数据至后端->后端接收到前端的请求之后（mapping），把数据按照规范给前端
 */
public interface BookRepository extends JpaRepository<Book,Integer> {
}
