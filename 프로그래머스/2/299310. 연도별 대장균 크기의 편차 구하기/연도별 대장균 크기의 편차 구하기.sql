select year(differentiation_date) as YEAR ,  q.q - size_of_colony as YEAR_DEV , ID
from ecoli_data e
join 
(select substr(differentiation_date , 1  , 4) as year , max(size_of_colony) q
from ecoli_data 
group by year ) as q
on substr(differentiation_date , 1  , 4) = q.year
order by year , year_dev