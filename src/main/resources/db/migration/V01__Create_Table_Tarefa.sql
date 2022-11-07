

create table usuario (
	`id` bigint(20) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;