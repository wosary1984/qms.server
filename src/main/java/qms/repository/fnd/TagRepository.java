package qms.repository.fnd;

import java.util.List;

import qms.repository.base.BaseRepository;

public interface TagRepository extends BaseRepository<Tag, Long> {

	public Tag findByTag(String tag);

	public List<Tag> findAll();

}
