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

create table local_token (
  uuid                      varchar(255) not null,
  email                     varchar(255),
  created_at                timestamp,
  expire_at                 timestamp,
  is_sign_up                boolean,
  constraint pk_local_token primary key (uuid))
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

create table user (
  id                        varchar(255) not null,
  email                     varchar(255),
  username                  varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  full_name                 varchar(255),
  provider                  varchar(255),
  password_hash             varchar(255),
  password_salt             varchar(255),
  constraint pk_user primary key (id))
;

create sequence book_seq;

create sequence chapter_seq;

create sequence local_token_seq;

create sequence paragraph_seq;

create sequence sentence_seq;

create sequence user_seq;

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

drop table if exists local_token;

drop table if exists paragraph;

drop table if exists sentence;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists book_seq;

drop sequence if exists chapter_seq;

drop sequence if exists local_token_seq;

drop sequence if exists paragraph_seq;

drop sequence if exists sentence_seq;

drop sequence if exists user_seq;

