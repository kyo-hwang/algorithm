-- 코드를 입력하세요
SELECT * FROM 
(SELECT DATE_FORMAT(SALES_DATE,"%Y-%m-%d") AS SALES_DATE,PRODUCT_ID,USER_ID,SALES_AMOUNT FROM ONLINE_SALE UNION ALL
SELECT DATE_FORMAT(SALES_DATE,"%Y-%m-%d") AS SALES_DATE,PRODUCT_ID,NULL,SALES_AMOUNT FROM OFFLINE_SALE) AS SALE_INFO
WHERE DATE_FORMAT(SALES_DATE,"%Y-%m")="2022-03"
ORDER BY SALES_DATE,PRODUCT_ID,USER_ID;