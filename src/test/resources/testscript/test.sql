create or replace
PROCEDURE ADD_SEQUENCE(SEQUENCENAME IN VARCHAR2) AUTHID CURRENT_USER
AS
  counter int;
  command VARCHAR2(1000);
BEGIN
  select count(*) INTO counter
  from user_sequences where sequence_name = SEQUENCENAME;

  IF counter = 0 THEN
    command := 'CREATE SEQUENCE ' || SEQUENCENAME || ' START WITH 1 MAXVALUE 9999999999 MINVALUE 0 NOCYCLE NOCACHE NOORDER';
    EXECUTE IMMEDIATE command;
  END IF ;
END;
/
create or replace
PROCEDURE ADD_CONSTRAINT(TABLENAME IN VARCHAR2, CONSTRAINTNAME IN VARCHAR, CONSTRAINTTEXT IN VARCHAR) AUTHID CURRENT_USER
AS
  counter int;
  command VARCHAR2(1000);
BEGIN
  select count(*) INTO counter
  from user_constraints where constraint_name = CONSTRAINTNAME;

  IF counter = 0 THEN
    command := 'ALTER TABLE ' || TABLENAME || ' ADD CONSTRAINT ' || CONSTRAINTNAME || ' ' || CONSTRAINTTEXT;
    EXECUTE IMMEDIATE command;
  END IF ;
END;
/
create or replace
PROCEDURE ADD_UNIQUE_INDEX(TABLENAME IN VARCHAR2, COLUMNNAME IN VARCHAR2, INDEXNAME IN VARCHAR2) AUTHID CURRENT_USER
AS
  counter int;
  command VARCHAR2(1000);
BEGIN
  select count(*) INTO counter
  from user_indexes where index_name = INDEXNAME;

  IF counter = 0 THEN
    command :=   'CREATE UNIQUE INDEX ' || INDEXNAME || ' ON ' || TABLENAME || '(' || COLUMNNAME || ') LOGGING TABLESPACE &indxtablespace';
    EXECUTE IMMEDIATE command;
  END IF ;
END;
/
create or replace
PROCEDURE ADD_COLUMN(TABLENAME IN VARCHAR2, COLUMNNAME IN VARCHAR2, COLUMNTYPE IN VARCHAR2) AUTHID CURRENT_USER
AS
  counter int;
  command VARCHAR2(1000);
BEGIN
  select count(*) INTO counter
  from user_tables where table_name = TABLENAME;

  IF counter = 0 THEN
    command := 'CREATE TABLE ' || TABLENAME ||  ' ( ' || COLUMNNAME || ' ' || COLUMNTYPE || ' ) TABLESPACE &datatablespace';
    EXECUTE IMMEDIATE command;
  ELSE
    select count(*) into counter
    from user_tab_columns
    where table_name = TABLENAME and column_name = COLUMNNAME;

    IF counter = 0 THEN
      command := 'ALTER TABLE ' || TABLENAME || ' ADD ' || COLUMNNAME || ' ' || COLUMNTYPE;
      EXECUTE IMMEDIATE command;
    END IF;
  END IF ;
END;
/

DECLARE
BEGIN
  ADD_COLUMN('TEST', 'TEST', 'NUMBER(10) NOT NULL');
END;
/