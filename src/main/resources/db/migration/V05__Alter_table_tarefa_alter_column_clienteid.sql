ALTER TABLE public.tarefa ALTER COLUMN cliente_id TYPE bigint USING cliente_id::bigint;