select *
from pg_tables
where schemaname = 'public';
select *
from dse_user_role;
select *
from dse_user;
drop table dse_user_role CASCADE;
drop table dse_user CASCADE;