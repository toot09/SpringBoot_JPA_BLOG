package com.cos.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	PrincipalDetailService principalDetailService;
	
	
	@Transactional
	public void insert(Board board, User user) {

		board.setCount(0);
		board.setUser(user);
		
		boardRepository.save(board);
	} 
	
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void update(Board mod) {
		Board board = boardRepository.findById(mod.getId()).orElseThrow(()->{
			return new IllegalArgumentException("존재하지 않는 글입니다.");
		});
		board.setTitle(mod.getTitle());
		board.setContent(mod.getContent());
		//Dirty Check
	}
	
	@Transactional(readOnly = true)
	public Page<Board> getBoards(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board findById (int id) {
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패 ");
		});
	}
	
	@Transactional
	public void saveReply(Reply reply, User user, int boardId) {
		reply.setBoard(boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("Board ID를 찾을 수 없습니다.");
		}));
		reply.setUser(user);
		replyRepository.save(reply);
	}
	
}
