-- 코드를 입력하세요
SELECT A.MCDP_CD AS "진료과코드", COUNT(*) AS "5월예약건수"  FROM APPOINTMENT A
WHERE DATE_FORMAT(A.APNT_YMD,"%Y-%m")="2022-05" 
GROUP BY A.MCDP_CD
ORDER BY 5월예약건수,진료과코드;

# SELECT * FROM APPOINTMENT