-- apa makna dari nilai smell 
-- 0 , 104.821 row
-- > 0 , 725 row
-- > 1.0 , 225 row 
-- buka pdf jcodeodor nya
-- lalu ada key smell buat harmfullnes juga
-- e.g. BrainMethod dan BrainMethodHarmfulness
-- 
select ms.id,key,me.type,value from Measurables as ms left join Measures as me 
on ms.id = me.measurable
where ms.id = 9000