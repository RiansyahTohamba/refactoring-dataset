select method_id,count(method_id) as total_metric from 
( select mt.type,mt.method_id,mt.method,key,value 
from MethodsAndTypes as mt 
left join Measures as me  on me.measurable = mt.method_id
where 
((key = 'CINT' and value > 7) or 
(key = 'CDISP' and value < 1.0) or  
(key = 'MAXNESTING' and value > 0)) 
group by method_id,key
ORDER BY method_id
) as inte
group by method_id
having count(method_id) > 2
