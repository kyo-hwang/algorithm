-- 코드를 작성해주세요

select year(ec.differentiation_date) AS YEAR,
(select max(size_of_colony) from ecoli_data sec where year(sec.differentiation_date) = year(ec.differentiation_date)) - ec.size_of_colony as "YEAR_DEV", EC.ID
from ecoli_data ec
ORDER BY YEAR, YEAR_DEV