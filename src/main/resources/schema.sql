create table Channel(
    id                  integer auto_increment primary key,
    username            varchar(30) not null,
    email               varchar(80) not null unique,
    password            varchar(50) not null,
    subscribers_counter integer not null default 0,
    about               tinytext,
    location            varchar(100) not null,
    creation_date       date not null
);

create table Subscribed(
    subscribed_channel_id   integer not null,
    channel_id              integer not null,
    foreign key (subscribed_channel_id) references Channel(id),
    foreign key (channel_id)            references Channel(id)
);

create table Video(
    id          integer auto_increment primary key,
    channel_id  integer not null,
    title       integer not null default 0,
    description text,
    upload_date date not null,
    video_file  varchar(255) not null unique,
    likes       integer not null default 0,
    dislikes    integer not null default 0,
    foreign key (channel_id) references Channel(id)
);

create table Likes(
    channel_id  integer not null,
    video_id    integer not null,
    foreign key (channel_id) references Channel(id),
    foreign key (video_id) references Video(id)
);