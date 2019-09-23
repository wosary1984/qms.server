package qms.repository.data;

import java.util.List;

import qms.repository.base.BaseRepository;

public interface FactorRepository extends BaseRepository<KeyFactor, Long> {

	public KeyFactor findByKey(String key);

	public List<KeyFactor> findAll();

}
