select ID , fi.fish_name , length
from fish_info f 
join fish_name_info fi
on f.fish_type = fi.fish_type
where f.length = (
    select max(length)
    from fish_info f1
    group by f1.fish_type
    having f.fish_type = f1.fish_type )
order by ID
