# select substr(differentiation_date , 6 , 2)
# from ECOLI_DATA



select
    case 
        when(substr(differentiation_date , 6 , 2) < 4) then '1Q' 
        when(substr(differentiation_date , 6 , 2) < 7) then '2Q' 
        when(substr(differentiation_date , 6 , 2) < 10) then '3Q' 
        when(substr(differentiation_date , 6 , 2) < 13) then '4Q' 
        end as 'QUARTER' ,
    count(*) as ECOLI_COUNT
from ECOLI_DATA
group by QUARTER
order by QUARTER