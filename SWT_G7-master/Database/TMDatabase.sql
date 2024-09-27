CREATE DATABASE TextManagement;
USE TextManagement;

create table ManagAcc(
id int not null primary key,
name nvarchar(100),
account varchar(100),
password varchar(100)
);

create table EmpAcc(
id varchar(20) not null primary key	,
name nvarchar(100),
account varchar(100),
password varchar(100),
manager int foreign key references ManagAcc(id),
);

create table Projects(
id nvarchar(20) not null primary key,
name nvarchar(100),
emp_id varchar(20) not null foreign key references EmpAcc(id),
details nvarchar(250),
current_day DATE,
dead_line DATE,
CONSTRAINT check_deadline CHECK (dead_line >= current_day))

create table DoneProjects(
id nvarchar(20) not null foreign key references Projects(id),
name nvarchar(100),
emp_id varchar(20) not null foreign key references EmpAcc(id),
path varchar(100),
fileName varchar(200),
current_day DATE,
check_deadline bit,
text nvarchar(4000)
);

select * from DoneProjects
ALTER TABLE DoneProjects
ADD CONSTRAINT PK_Person PRIMARY KEY (id,emp_id);

INSERT INTO ManagAcc (id, name, account, password)
VALUES (1, N'Đỗ Đăng Đạt', 'datdeptraino1', '250608');

INSERT INTO EmpAcc (id, name, account, password, manager)
VALUES 
('NV001', N'Nguyễn Huy Phong', 'hphong132', '4002', 1),
('NV002', N'Phan Thanh Tuyền', 'oldman', 'taihen', 1),
('NV003', N'Hồ Thành Công', 'coong', 'konn', 1),
('NV004', N'Chu Quang Đăng', 'dangdandon', '1234', 1);

delete from DONEProjects where id ='DA9'
select * from DoneProjects

-- Create trigger to automatically set the check_deadline
DROP TRIGGER trg_SetCheckDeadline

CREATE TRIGGER trg_SetCheckDeadline
ON DoneProjects
AFTER INSERT, UPDATE
AS
BEGIN
    -- Ensure only affected rows are updated
    UPDATE dp
    SET dp.check_deadline = CASE
        WHEN dp.current_day > p.dead_line THEN 0
        ELSE 1
    END
    FROM DoneProjects dp
    INNER JOIN Projects p ON dp.id = p.id
    INNER JOIN inserted i ON dp.id = i.id;
END;