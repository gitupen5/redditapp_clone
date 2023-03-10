package com.example.reddit.redditapp.mapper;

import com.example.reddit.redditapp.dto.CommentsDto;
import com.example.reddit.redditapp.module.Comment;
import com.example.reddit.redditapp.module.Post;
import com.example.reddit.redditapp.module.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "post", source = "post")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser() != null ? comment.getUser().getUsername() : null)")
    CommentsDto mapToDto(Comment comment);
}