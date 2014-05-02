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

create table context (
  id                        bigint not null,
  book_id                   bigint,
  guess_id                  bigint,
  constraint pk_context primary key (id))
;

create table context_has_sentences (
  id                        bigint not null,
  context_id                bigint,
  sentence_id               bigint,
  visible                   boolean,
  cost                      NUMERIC,
  constraint pk_context_has_sentences primary key (id))
;

create table guess (
  id                        bigint not null,
  context_id                bigint,
  user_id                   bigint,
  guess                     varchar(240),
  constraint pk_guess primary key (id))
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

create table purchase (
  id                        bigint not null,
  user_id                   bigint,
  amount                    NUMERIC,
  `when`                      timestamp,
  constraint pk_purchase primary key (id))
;

create table review (
  id                        bigint not null,
  guess_id                  bigint,
  reviewer_id               bigint,
  score_id                  bigint,
  constraint pk_review primary key (id))
;

create table score (
  id                        bigint not null,
  user_id                   bigint,
  review_id                 bigint,
  guess_id                  bigint,
  grammar_score             NUMERIC,
  fun_score                 NUMERIC,
  accuracy_score            NUMERIC,
  constraint pk_score primary key (id))
;

create table sentence (
  id                        bigint not null,
  content                   varchar(255) not null,
  paragraph_id              bigint,
  constraint pk_sentence primary key (id))
;

create table user (
  id                        bigint not null,
  email                     varchar(255),
  username                  varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  full_name                 varchar(255),
  provider                  varchar(255),
  password_hash             varchar(255),
  password_salt             varchar(255),
  monies                    NUMERIC,
  constraint pk_user primary key (id))
;


create table context_context_has_sentences (
  context_id                     bigint not null,
  context_has_sentences_id       bigint not null,
  constraint pk_context_context_has_sentences primary key (context_id, context_has_sentences_id))
;
create sequence book_seq;

create sequence chapter_seq;

create sequence context_seq;

create sequence context_has_sentences_seq;

create sequence guess_seq;

create sequence local_token_seq;

create sequence paragraph_seq;

create sequence purchase_seq;

create sequence review_seq;

create sequence score_seq;

create sequence sentence_seq;

create sequence user_seq;

alter table chapter add constraint fk_chapter_book_1 foreign key (book_id) references book (id) on delete restrict on update restrict;
create index ix_chapter_book_1 on chapter (book_id);
alter table context add constraint fk_context_book_2 foreign key (book_id) references book (id) on delete restrict on update restrict;
create index ix_context_book_2 on context (book_id);
alter table context add constraint fk_context_guess_3 foreign key (guess_id) references guess (id) on delete restrict on update restrict;
create index ix_context_guess_3 on context (guess_id);
alter table context_has_sentences add constraint fk_context_has_sentences_conte_4 foreign key (context_id) references context (id) on delete restrict on update restrict;
create index ix_context_has_sentences_conte_4 on context_has_sentences (context_id);
alter table context_has_sentences add constraint fk_context_has_sentences_sente_5 foreign key (sentence_id) references sentence (id) on delete restrict on update restrict;
create index ix_context_has_sentences_sente_5 on context_has_sentences (sentence_id);
alter table guess add constraint fk_guess_context_6 foreign key (context_id) references context (id) on delete restrict on update restrict;
create index ix_guess_context_6 on guess (context_id);
alter table guess add constraint fk_guess_user_7 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_guess_user_7 on guess (user_id);
alter table paragraph add constraint fk_paragraph_chapter_8 foreign key (chapter_id) references chapter (id) on delete restrict on update restrict;
create index ix_paragraph_chapter_8 on paragraph (chapter_id);
alter table purchase add constraint fk_purchase_user_9 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_purchase_user_9 on purchase (user_id);
alter table review add constraint fk_review_guess_10 foreign key (guess_id) references guess (id) on delete restrict on update restrict;
create index ix_review_guess_10 on review (guess_id);
alter table review add constraint fk_review_reviewer_11 foreign key (reviewer_id) references user (id) on delete restrict on update restrict;
create index ix_review_reviewer_11 on review (reviewer_id);
alter table review add constraint fk_review_score_12 foreign key (score_id) references score (id) on delete restrict on update restrict;
create index ix_review_score_12 on review (score_id);
alter table score add constraint fk_score_user_13 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_score_user_13 on score (user_id);
alter table score add constraint fk_score_review_14 foreign key (review_id) references review (id) on delete restrict on update restrict;
create index ix_score_review_14 on score (review_id);
alter table score add constraint fk_score_guess_15 foreign key (guess_id) references guess (id) on delete restrict on update restrict;
create index ix_score_guess_15 on score (guess_id);
alter table sentence add constraint fk_sentence_paragraph_16 foreign key (paragraph_id) references paragraph (id) on delete restrict on update restrict;
create index ix_sentence_paragraph_16 on sentence (paragraph_id);



alter table context_context_has_sentences add constraint fk_context_context_has_senten_01 foreign key (context_id) references context (id) on delete restrict on update restrict;

alter table context_context_has_sentences add constraint fk_context_context_has_senten_02 foreign key (context_has_sentences_id) references context_has_sentences (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists book;

drop table if exists chapter;

drop table if exists context;

drop table if exists context_context_has_sentences;

drop table if exists context_has_sentences;

drop table if exists guess;

drop table if exists local_token;

drop table if exists paragraph;

drop table if exists purchase;

drop table if exists review;

drop table if exists score;

drop table if exists sentence;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists book_seq;

drop sequence if exists chapter_seq;

drop sequence if exists context_seq;

drop sequence if exists context_has_sentences_seq;

drop sequence if exists guess_seq;

drop sequence if exists local_token_seq;

drop sequence if exists paragraph_seq;

drop sequence if exists purchase_seq;

drop sequence if exists review_seq;

drop sequence if exists score_seq;

drop sequence if exists sentence_seq;

drop sequence if exists user_seq;

