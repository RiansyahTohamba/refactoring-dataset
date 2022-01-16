-- bother 

select count(*) as total from detect_dcoh where 
level = 'value >= 1 and value < 3.25'
-- warning
select count(*) as total from detect_dcoh where 
level = 'value >= 3.25 and value < 5.5'

-- trouble
select count(*) as total from detect_dcoh where 
level = 'value >= 5.5 and value < 7.75'

-- problem
select count(*) as total from detect_dcoh where 
level = 'value >= 7.75 and value < 10'

-- harm
select count(*) as total from detect_dcoh where 
level = 'value = 10'
