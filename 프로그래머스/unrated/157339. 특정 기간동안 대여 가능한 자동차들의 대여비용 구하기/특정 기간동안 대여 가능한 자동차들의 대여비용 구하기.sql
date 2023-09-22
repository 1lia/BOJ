-- 코드를 입력하세요
select a.car_id , a.car_type , round(a.daily_fee * 30 * (100 - p.discount_rate) / 100) as fee
from CAR_RENTAL_COMPANY_CAR a
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
on a.car_type = p.car_type
where a.car_id not in(
    select c.car_id
    from CAR_RENTAL_COMPANY_CAR c
    join CAR_RENTAL_COMPANY_RENTAL_HISTORY h
    on c.car_id = h.car_id
    where (h.start_date between '2022-11-01' and '2022-11-30') or (h.end_date between '2022-11-01' and '2022-11-30') or (h.start_date < '2022-11-01' and h.end_date > '2022-11-30')) and p.duration_type = '30일 이상' and 500000 <= daily_fee * 30 * (100 - p.discount_rate) / 100 and daily_fee * 30 * (100 - p.discount_rate) / 100 < 2000000
order by fee desc , car_type , a.car_id desc;




# SELECT c.car_id as CAR_ID , c.car_type as CAR_TYPE , round(c.daily_fee * 30 * (100 - p.discount_rate) / 100) as FEE , start_date , end_date
# from CAR_RENTAL_COMPANY_CAR c
# join CAR_RENTAL_COMPANY_RENTAL_HISTORY h
# on c.car_id = h.car_id
# join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
# on c.car_type = p.car_type
# where (c.car_type = '세단' or c.car_type = 'SUV') and (h.start_date > '2022-11-30' or h.end_date < '2022-11-01') and p.duration_type = '30일 이상' and 500000 <= daily_fee * 30 * (100 - p.discount_rate) / 100 and daily_fee * 30 * (100 - p.discount_rate) / 100 < 2000000
# order by fee desc , car_type , c.car_id desc;