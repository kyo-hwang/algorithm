-- 코드를 작성해주세요

# select fi.id as "id", fn.fish_name , fi.length from fish_info fi
# join fish_name_info fn
# on fi.fish_type = fn.fish_type
# where fi.length in 
# (select max(sfi.length) as "len" from fish_info sfi where sfi.fish_type=fi.fish_type)
# order by fi.id;

with biggest_length_each_type as 
(select fish_type, max(length) as "max_length" from fish_info group by fish_type)

select fi.id,fn.fish_name,fi.length from biggest_length_each_type b
join fish_info fi on fi.fish_type = b.fish_type and fi.length = b.max_length
join fish_name_info fn on fn.fish_type = fi.fish_type;