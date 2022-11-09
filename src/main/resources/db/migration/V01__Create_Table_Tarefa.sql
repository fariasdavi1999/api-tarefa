

create table tarefa (
	id bigint(20) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

ALTER TABLE tarefa
  ADD PRIMARY KEY id;

ALTER TABLE tarefa
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;