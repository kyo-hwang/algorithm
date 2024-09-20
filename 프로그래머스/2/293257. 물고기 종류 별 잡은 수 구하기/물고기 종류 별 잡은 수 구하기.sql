-- 코드를 작성해주세요
select count(*) as "FISH_COUNT"
, (select fn.fish_name from fish_name_INFO fn where fn.fish_type = fi.fish_type ) as "FISH_NAME"
from fish_info fi
group by fi.fish_type
ORDER BY FISH_COUNT DESC;