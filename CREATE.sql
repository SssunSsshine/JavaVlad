CREATE SEQUENCE s_profile;

CREATE SEQUENCE s_note;

create table profile (
   	id_profile           	BIGINT               not null DEFAULT (nextval('s_profile')),
   	surname_profile         VARCHAR(100)         not null,
   	name_profile            VARCHAR(100)         not null,
   	birthday_profile        DATE                 not null,
   	phone_profile    		VARCHAR(20)          not null UNIQUE,
   	email_profile     		VARCHAR(100)         null UNIQUE,
	password_profile		VARCHAR(100)         null	
);

create table note (
   	id_note           	BIGINT              			not null DEFAULT (nextval('s_note')),
   	text_note			VARCHAR(100)					not null,
	time_creation		TIMESTAMP without time zone		not null DEFAULT(NOW()),
	id_profile			BIGINT              			not null
);
alter table profile
   add constraint PK_PROFILE primary key (id_profile);
   
alter table note
   add constraint PK_NOTE primary key (id_note);

alter table note
   add constraint FK_NOTE_PROFILE foreign key (id_profile)
      references profile (id_profile)
      on delete cascade on update cascade;