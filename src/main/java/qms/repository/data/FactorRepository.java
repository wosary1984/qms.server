package qms.repository.data;

import java.util.List;

import qms.repository.base.BaseRepository;

public interface FactorRepository extends BaseRepository<Factor, Long> {

	public Factor findByKey(String key);

	public List<Factor> findAll();

}
