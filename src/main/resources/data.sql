-- 게시글 더미데이터
INSERT INTO article(title, content)
VALUES ('가가가가', '1111'),
       ('나나나나', '2222'),
       ('다다다다', '3333'),
       ('당신의 인생 영화는?', '댓글 ㄱ'),
       ('당신의 소울푸드는?', '댓글 ㄱㄱ'),
       ('당신의 취미는??', '댓글 ㄱㄱㄱ');

-- 댓글 더미 데이터
INSERT INTO comment(article_id, nickname, body)
VALUES (4,'ㅇㅇ', '야미네'),
       (4,'ㅇㅇ', '야미네'),
       (4,'ㅇㅇ', '야미네'),
       (6,'ㅇㅇ', '야미네'),
       (6,'ㅇㅇ', '야미네');


