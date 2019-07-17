package qms.repository.person;

import qms.repository.base.BaseRepository;

public interface PersonRepository extends BaseRepository<Person, Long> {

	public Person findByPersonid(Long personid);

}
