
create table foro(

    id bigint not null auto_increment,
    mensaje varchar(250) not null,
    nombreCurso varchar(250) not null unique,
    titulo varchar(250) not null unique,
    activo tinyint not null

    primary key(id)

);
