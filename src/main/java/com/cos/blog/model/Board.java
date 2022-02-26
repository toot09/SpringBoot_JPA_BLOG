package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String content;
	
	private int count;	
	
	@ManyToOne // Many = Board, One = User
	@JoinColumn(name="userId") // DB에서는 Object를 저장할 수 없으니 실제 DB에서 사용될 PK명 입력.
	private User userId; // RDB는 FK 값으로 JOIN하지만 ORM(JPA)는 객체 자체로 생성.
	
	// [mappedBy]
	// 1. 연관관계의 주인이 아님. (FK는 board에 있음)
	// 2. DB에 컬럼 생성 아님!
	// 3. Board를 가져올때, Reply를 가져오기 위해 생성.
	// 4. board는 Reply에서 Board연결한 FK값.
	// [fetch]
	// 1. FetchType.EAGER : Board를 select 할 때 바로 가져온다. (한페이지에 바로 모든 데이터 보여줄때)
	// 2. FetchType.Lazy : 필요할때(호출할때) 가져온다. (예로 리플 접기, 펼치기 기능 있을때)
	@OneToMany(mappedBy =  "board", fetch = FetchType.EAGER) 
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
