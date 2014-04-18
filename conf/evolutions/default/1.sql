# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book (
  id                        bigint not null,
  title                     varchar(255) not null,
  author                    varchar(255),
  publication_date          timestamp,
  constraint pk_book primary key (id))
;

create table chapter (
  id                        bigint not null,
  book_id                   bigint,
  constraint pk_chapter primary key (id))
;

create table paragraph (
  id                        bigint not null,
  chapter_id                bigint,
  constraint pk_paragraph primary key (id))
;

create table sentence (
  id                        bigint not null,
  content                   varchar(255) not null,
  paragraph_id              bigint,
  constraint pk_sentence primary key (id))
;

create sequence book_seq;

create sequence chapter_seq;

create sequence paragraph_seq;

create sequence sentence_seq;

alter table chapter add constraint fk_chapter_book_1 foreign key (book_id) references book (id) on delete restrict on update restrict;
create index ix_chapter_book_1 on chapter (book_id);
alter table paragraph add constraint fk_paragraph_chapter_2 foreign key (chapter_id) references chapter (id) on delete restrict on update restrict;
create index ix_paragraph_chapter_2 on paragraph (chapter_id);
alter table sentence add constraint fk_sentence_paragraph_3 foreign key (paragraph_id) references paragraph (id) on delete restrict on update restrict;
create index ix_sentence_paragraph_3 on sentence (paragraph_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists book;

drop table if exists chapter;

drop table if exists paragraph;

drop table if exists sentence;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists book_seq;

drop sequence if exists chapter_seq;

drop sequence if exists paragraph_seq;

drop sequence if exists sentence_seq;

