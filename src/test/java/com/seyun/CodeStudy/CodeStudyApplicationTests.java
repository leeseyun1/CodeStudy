package com.seyun.CodeStudy;


import com.seyun.CodeStudy.model.lecture.Lecture;
import com.seyun.CodeStudy.model.post.Comment;
import com.seyun.CodeStudy.model.post.Post;
import com.seyun.CodeStudy.service.CommentService;
import com.seyun.CodeStudy.service.LectureService;
import com.seyun.CodeStudy.service.PostService;
import com.seyun.CodeStudy.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


//@SpringBootTest
//class CodeStudyApplicationTests {
//
//	@Autowired
//	private LectureService lectureService;
//    @Autowired
//    private PostService postService;
//    @Autowired
//	private CommentService commentService;
//	@Autowired
//	private TagService tagService;
//
//
//    @Test
//	void createLectures(){
//		String[] id = {"seyun1", "seyun2", "seyun3", "seyun4", "seyun5", "seyun6", "seyun7", "seyun8", "seyun9"};
//		String[] name = {"이이이", "김김김", "박박박", "최최최", "강강강", "권권권", "장장장", "정정정", "하하하"};
//		String[][] tagContent = {
//			{"css", "html", "자바", "자바스크립트", "제이쿼리", "코틀린", "파이썬", "리액트", "스프링부트", "타입스크립트"},
//			{"초급", "중급", "입문", "고급", "초보자", "기초"},
//			{"꿀팁", "특강", "실전"},
//			{"테스트", "코드테스트", "코드", "코딩", "공부"}
//		};
//
//
//		for(int i = 0; i < 100; i++){			//i 0~99까지 반복
//			Lecture lectures = new Lecture();
//			lectures.setWriterId(id[i%9]);
//			lectures.setWriterName(name[i%9]);
//			lectures.setIntro("인트로입니당" + i);
//			lectures.setTitle("제목입니다" + i);
//			lectureService.create(lectures);
//
//			String[] tags = {
//				tagContent[0][(int)(Math.random() * 10)],
//				tagContent[1][(int)(Math.random() * 6)],
//				tagContent[2][(int)(Math.random() * 3)],
//				tagContent[3][(int)(Math.random() * 5)]
//			};
//			tagService.saveTag(tags, lectures.getLectureId());
//
//		}
//	}
//
//    @Test
//    void createPosts(){
//        String[] id = {"seyun1", "seyun2", "seyun3", "seyun4", "seyun5", "seyun6", "seyun7", "seyun8", "seyun9"};
//        String[] name = {"이이이", "김김김", "박박박", "최최최", "강강강", "권권권", "장장장", "정정정", "하하하"};
//        for(int i = 0; i < 100; i++){
//            Post posts = new Post();
//            posts.setWriterId(id[i%9]);
//            posts.setWriterName(name[i%9]);
//            posts.setTitle("테스트용 제목입니다. " + (i+1));
//            posts.setContent("테스트용 내용입니다. " + (i+1));
//            postService.create(posts);
//        }
////        , #{title}
////        , #{content}
////        , #{writerId}
////        , #{writerName}
//    }
//
//    @Test
//    void createComment(){
//		String[] id = {"seyun1", "seyun2", "seyun3", "seyun4", "seyun5", "seyun6", "seyun7", "seyun8", "seyun9"};
//		String[] name = {"이이이", "김김김", "박박박", "최최최", "강강강", "권권권", "장장장", "정정정", "하하하"};
//		Long[] postIds = postService.findAllPostId();
//
//		for(int i = 0; i < postIds.length; i++){
//
//			for(int j = 0; j < 30; j++){
//				Comment comments = new Comment();
//				comments.setPostId(postIds[i]);
//				comments.setWriterId(id[j%9]);
//				comments.setWriterName(name[j%9]);
//				comments.setContent("이 댓글은 " + postIds[i] + "게시글의 " + (j+1) + "번쨰 댓글 입니다.");
//				commentService.create(comments);
//			}
//		}
////		, #{postId}
////		, #{writerId}
////		, #{writerName}
////		, #{content}
//    }
//}
