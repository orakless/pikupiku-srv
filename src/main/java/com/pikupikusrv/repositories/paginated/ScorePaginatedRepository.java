package com.pikupikusrv.repositories.paginated;

import com.pikupikusrv.entities.Map;
import com.pikupikusrv.entities.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ScorePaginatedRepository extends ListPagingAndSortingRepository<Score, Integer> {
    Page<Score> findAllByMap(Map map, Pageable pageable);
}
