create table books
(
    id          serial primary key,
    title       varchar(30) not null,
    author      varchar(30) not null,
    date_added  timestamp not null
)


insert into books (title, author, date_added)
values('Недоросль', 'Д.И. Фонвизин', now());
insert into books (title, author, date_added)
values('Потерянный рай', 'Д. Мильтон', now() - interval '24h');
insert into books (title, author, date_added)
values('Война и Мир', 'Л.Н. Толстой', now() - interval '24h');
insert into books (title, author, date_added)
values('Братья Карамазовы', 'Ф.М. Достоевский', now() - interval '24h');

select * from books