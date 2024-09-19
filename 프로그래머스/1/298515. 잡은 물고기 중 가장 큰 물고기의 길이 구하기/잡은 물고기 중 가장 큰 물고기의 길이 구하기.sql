-- 코드를 작성해주세요

select concat(convert(fi.length, CHAR),'cm') as "MAX_LENGTH" from fish_info fi order by fi.length desc limit 1;

# select cast(fi.length as float) from fish_info fi