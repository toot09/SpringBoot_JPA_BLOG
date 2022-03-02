package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	@Modifying
	@Query(value="INSERT INTO reply(content, boardId, userId, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery=true)
	int mSave(String content, int boardId, int userId);
	
	@Modifying
	@Query(value="DELETE FROM reply WHERE boardId=?1 AND id = ?2", nativeQuery = true)
	int mDelete(int boardId, int replyId);
}
