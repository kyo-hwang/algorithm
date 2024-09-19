-- 코드를 작성해주세요

select fi.id as "id", fn.fish_name , fi.length from fish_info fi
join fish_name_info fn
on fi.fish_type = fn.fish_type
where (fi.fish_type,fi.length) in 
(select sfi.fish_type, max(sfi.length) as "len" from fish_info sfi group by sfi.fish_type)
order by fi.id;