package com.youjian.blog.user.entity;

import com.github.rjeschke.txtmark.Processor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity(name = "b_blog")
public class Blog implements Serializable {
    private static final long serialVersionUID = 8977496053490241574L;
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @NotEmpty(message = "标题不能为空")
    @Size(min=2, max=50)
    @Column(nullable = false, length = 50) // 映射为字段，值不能为空
    private String title;

    @NotEmpty(message = "摘要不能为空")
    @Size(min=2, max=300)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String summary;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min=2)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch=FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min=2)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String htmlContent; // 将 md 转为 html

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    @Column(name="reading")
    private Long reading = 0L; // 访问量、阅读量

    @Column(name="comments")
    private Long comments = 0L;  // 评论量

    @Column(name="likes")
    private Long likes = 0L;  // 点赞量

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.htmlContent = Processor.process(content); // 将 Markdown 转换为 HTML 格式
    }
}
