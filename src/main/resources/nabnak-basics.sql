

create table members(
	email varchar(40) primary key,
	full_name varchar(20) not null,
	experience_months numeric check ( experience_months >= 0),
	registration_date date not null,
	"password" varchar(32) not null
);
-- drop for members
--drop table members cascade;
--
--

create table card(
	card_id serial primary key,
	description text not null,
	points int,
	tech varchar(20),
	status varchar(20),
	members_email varchar(20)
);
-- drop for card
drop table card;
--
--

-- alter card for FK
alter table card 
add constraint fk_members_card
foreign key (members_email) references members(email) on delete cascade;


-- Select and view tables
select * from members;
select * from card;

-- Create some dummy data
insert into members 
values
('cj@mail.com', 18, 'Charles Jester',  'pass', '2022-01-01'),
('ar@mail.com', 2400, 'Adam Ranieri', 'gatorFan', '2022-07-06'),
('bp@mail.com', 24, 'Ben Petruzziello', 'ooBen', '2022-07-31'),
('po@mail.com', 12, 'Patrick Orwin', 'notThatPatrick', '2022-07-22'),
('ab@mail.com', 180, 'Ashwin Bharath', 'coolBoss', '2022-06-18');

insert into card 
values
(default, 'Show off some cool JDBC', 2, 'Java', 'In-progress', 'cj@mail.com'),
(default, 'Show them maven', 2, 'Java', 'Completed', 'cj@mail.com'),
(default, 'Show them JankUnit & Reflections', 2, 'Java', 'In-progress', 'cj@mail.com'),
(default, 'Show off Spring Boot', 2, 'Java', 'In-progress', 'ar@mail.com'),
(default, 'Show off Multiplicities', 2, 'SQL', 'Completed', 'po@mail.com'),
(default, 'Show off a button with JS', 2, 'JavaScript', 'In-progress', 'bp@mail.com');

-- Read some data
select * from members m; -- findAll members
select * from card; -- findAll cards

select * from members where email = 'cj@mail.com'; -- find a member by identity 
select * from card where card_id = 1; --  find a card by id
select * from card where members_email = 'cj@mail.com'; -- find all cards made by user

-- Update some data
update members set experience_months = 24 where email = 'cj@mail.com'; -- update member
update card set status = 'Completed' where card_id = 1; -- update card
update members 
set email = 'cj@mail.com', full_name = 'Charles Jester', experience_months = 18, registration_date = '2022-01-01', "password" = 'pass' 
where email = 'cj@mail.com'; -- complete update 

-- Delete some data
delete from members where email = 'valid'; -- delete by id
delete from card where status = 'Completed'; -- delete by status

-- Alter table to remove columns
alter table members drop column registrationdate;

delete from card where card_id in (1,2)

update members set is_admin = true where email = 'jj@mail.com';

update members set avatar = concat('https://avatars.dicebear.com/api/human/', email, '.svg') where avatar is null ;
select * from members m ;
