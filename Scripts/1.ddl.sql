drop table if exists web_gradle_erp.board;

create table if not exists web_gradle_erp.board(
	board_num int,
	board_name varchar(20) not null,
	board_pass varchar(15) not null,
	board_subject varchar(50) not null,
	board_content varchar(2000) not null,
	board_file varchar(50) not null,
	
	board_re_ref int not null,
	
	board_re_lev int default 0,
	board_re_seq int default 0,
	board_readcount int default 0,
	
	board_date datetime default CURRENT_TIMESTAMP,
	primary key(board_num)
	);

