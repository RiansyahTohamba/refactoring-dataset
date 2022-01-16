format penamaan sqlite

urutan_koding - nama_branch

database diurutkan sesuai waktu eksperimen
bisa dicek pada tanggal branch

1-tahap-0-argouml.sqlite
2-tahap-1-argouml.sqlite

viewnya

CREATE VIEW detect_dcoh as
select 
meth.id as method_id,
type.name as type,meth.name,
value
from 
(select ms.id , ms.parent,ms.name,value 
	from Measurables as ms left join Measures as me 
on ms.id = me.measurable
where key ='DispersedCouplingHarmfulness' and value > 0
order by value desc) as meth
left join 
(select id ,name from Measurables  where type ='type' ) as type
on meth.parent = type.id