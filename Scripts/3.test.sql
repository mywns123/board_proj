select max(board_num) from board;
select * from board;
insert into board 
VALUE(1,'김상건', '1111','마칠시간','5시','TEST.TXT',0,0,0,0,'2021-03-03');

 board_num, board_name, board_pass, board_subject, board_content, board_file, board_re_ref, board_re_lev, board_re_seq, board_readcount, board_date from board;
 
insert into board 
(board_num, board_name, board_pass, board_subject,
board_content, board_file, board_re_ref)
VALUE(2,'김상건', '1111','마칠시간','5시','TEST.TXT',0);

-- list 페이지
/*

[1][2][3]

(page -1) * 10 => 1 page 0, 2 page 10,3 page 20

*/

select board_num, board_name, board_pass,
board_subject, board_content, board_file,
board_re_ref, board_re_lev, board_re_seq,
board_readcount, board_date from board
order by board_re_ref desc, board_re_seq asc limit 0, 10;

select board_num, board_name, board_pass,
board_subject, board_content, board_file,
board_re_ref, board_re_lev, board_re_seq,
board_readcount, board_date from board
order by board_re_ref desc, board_re_seq asc limit 10, 10;


SELECT count(*)from board;

select board_content from board where board_num = 3;

update  board  set board_readcount  = board_readcount + 1
where board_num  = 23;

 SELECT * from board ;
 SELECT * from board where board_num=24;
 
SELECT 1 from board where board_num =25 and board_pass = 'aaa';


-- 수정
select board_num, board_name, board_pass,
board_subject, board_content, board_file,
board_re_ref, board_re_lev, board_re_seq,
board_readcount, board_date from board
order by board_re_ref desc, board_re_seq;


update board
set board_subject ='aaa', board_content = 'aaa'
where board_num = 43;

--글에 대한 답변
update board set board_re_seq  = board_re_seq + 1
where board_re_ref  =? and board_re_seq > ?;


insert into board 
(board_num, board_name, board_pass,
board_subject, board_content, board_file,
board_re_ref, board_re_lev, board_re_seq,
board_readcount, board_date)
VALUEs(46,'김상건', '1111','마칠시간','5시','TEST.TXT',0);

select * from board where board_re_ref =44;
select * from board where board_re_ref =48;

