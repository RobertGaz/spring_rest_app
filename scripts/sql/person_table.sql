create table person (
                        id integer not null auto_increment,
                        name varchar(20),
                        age integer,
                        email varchar(50),
                        create_time timestamp,
                        update_time timestamp,
                        primary key (id)
);

insert into person (name, age, email)
values  ('Robert', 23, 'my@gmail.com'),
        ('Alina', 24, 'alina@mail.ru'),
        ('We', 1, 'we@mail.com'),
        ('Kek', 34, 'oh@me.com'),
        ('myhomeisgood', 1, 'a@mail.ru'),
        ('Kristina', 26, 'k@mail.ru'),
        ('Person', 30, 'abc@def.gh');