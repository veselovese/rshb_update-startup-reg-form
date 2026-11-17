delete
from
    digital_publications.o_plt_post_view_history vh
where
        vh.session_id in (
        select distinct
            h.session_id
        from
            digital_publications.o_plt_post_view_history h
                left join
            digital_system.sessions s on h.session_id = s.session_id
        where
            s.session_id is null
    );

alter table
    digital_publications.o_plt_post_view_history
    add foreign key (session_id)
        references digital_system.sessions (session_id)
        on delete cascade;

delete
from
    digital_publications.o_plt_post_likes ppl
where
        ppl.session_id in (
        select distinct
            pl.session_id
        from
            digital_publications.o_plt_post_likes pl
                left join
            digital_system.sessions s on pl.session_id = s.session_id
        where
            s.session_id is null
    );

alter table
    digital_publications.o_plt_post_likes
    add foreign key (session_id)
    references digital_system.sessions (session_id)
    on delete cascade;
