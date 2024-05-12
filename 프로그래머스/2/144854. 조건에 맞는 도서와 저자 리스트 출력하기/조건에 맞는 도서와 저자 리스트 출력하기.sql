-- 코드를 입력하세요
SELECT book_id , author_name , substring(published_date , 1,  10) published_date
from book , author
where book.author_id = author.author_id and category like '경제' 
order by published_date
# join author on b.author_id
# where category like '경제' 


