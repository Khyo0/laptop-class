-- 회원
CREATE TABLE `member` (
	`m_idx`    VARCHAR(255) NOT NULL COMMENT '자동 증가', -- 회원 번호
	`id`       VARCHAR(40)  NOT NULL, -- 아이디
	`email`    VARCHAR(40)  NOT NULL, -- 이메일
	`password` VARCHAR(40)  NOT NULL, -- 비밀번호
	`name`     VARCHAR(40)  NOT NULL, -- 이름
	`m_photo`  VARCHAR(40)  NULL     DEFAULT profile.png, -- 프로필 사진
	`bio`      VARCHAR(255) NULL,     -- 소개글
	`code`     VARCHAR(10)  NOT NULL, -- 이메일 인증 코드
	`verify`   VARCHAR(10)  NOT NULL  -- 이메일 인증 여부


);

-- 회원
ALTER TABLE `member`
	ADD CONSTRAINT `PK_member` -- 회원 기본키
		PRIMARY KEY (
			`m_idx` -- 회원 번호
		);

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX `UIX_member`
	ON `member` ( -- 회원
		`id` ASC -- 아이디
	);

ALTER TABLE `member`
	MODIFY COLUMN `m_idx` VARCHAR(255) NOT NULL AUTO_INCREMENT COMMENT '자동 증가';

ALTER TABLE `member`
	AUTO_INCREMENT = 1;