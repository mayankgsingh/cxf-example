create table number_inp
(
   id integer auto_increment primary key,
   num integer not null,
   created_on date default now()
);

create table number_gcd
(
   id integer auto_increment primary key,
   x integer,
   y integer,
   num integer
);