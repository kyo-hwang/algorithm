-- 코드를 입력하세요
WITH CARS_OVER(CAR_ID) AS (
    SELECT C.CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY C
    WHERE DATE_FORMAT(C.START_DATE,"%Y-%m") BETWEEN '2022-08' AND '2022-10'
    GROUP BY C.CAR_ID
    HAVING COUNT(*)>=5
)
SELECT MONTH(CR.START_DATE) AS MONTH, CO.CAR_ID, COUNT(DATE_FORMAT(CR.START_DATE,"%m")) AS RECORDS FROM CARS_OVER CO 
JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY CR 
ON CO.CAR_ID=CR.CAR_ID
WHERE DATE_FORMAT(CR.START_DATE,"%m") BETWEEN 8 AND 10
GROUP BY CO.CAR_ID,DATE_FORMAT(CR.START_DATE,"%m")
ORDER BY MONTH,CAR_ID DESC;