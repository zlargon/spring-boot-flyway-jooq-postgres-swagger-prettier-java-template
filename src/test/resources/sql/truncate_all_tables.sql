-- https://stackoverflow.com/a/63227261
DO $$
DECLARE
  records CURSOR FOR
    SELECT tablename
    FROM pg_tables
    WHERE schemaname = 'spring_demo_test' AND tablename NOT IN (
      'flyway_schema_history'
    );
BEGIN
  FOR record IN records LOOP
    -- truncate table and reset the auto-incrementing row id
    EXECUTE 'TRUNCATE TABLE spring_demo_test.' || record.tablename || ' RESTART IDENTITY CASCADE';
  END LOOP;
END $$;
