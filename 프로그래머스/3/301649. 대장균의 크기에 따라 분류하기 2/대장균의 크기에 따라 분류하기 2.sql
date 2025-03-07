-- 코드를 작성해주세요
SELECT S.ID, (
    CASE WHEN S.PERCENT<=0.25 THEN "CRITICAL" 
    WHEN S.PERCENT<=0.5 THEN "HIGH" 
    WHEN S.PERCENT<=0.75 THEN "MEDIUM" 
    ELSE "LOW" END) AS COLONY_NAME
FROM(SELECT E.ID, RANK() OVER(ORDER BY E.SIZE_OF_COLONY DESC)/COUNT(*) OVER() AS PERCENT FROM ECOLI_DATA E) S
ORDER BY S.ID;
# SELECT E.ID, RANK() OVER(ORDER BY E.SIZE_OF_COLONY DESC)/COUNT(*) OVER() AS PERCENT FROM ECOLI_DATA E