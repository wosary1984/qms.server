package qms.repository.data;

import qms.repository.base.BaseRepository;

public interface FactorEventRepository extends BaseRepository<FactorEvent, Long> {

	public FactorEvent findByEventid(Long eventid);


}
