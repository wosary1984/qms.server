package qms.service.person;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import qms.exception.def.BadRequestException;
import qms.repository.person.Person;
import qms.repository.person.PersonRepository;
import qms.service.context.ContextCheckService;

@Service
public class PersonService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	ContextCheckService context;

	@PreAuthorize("hasPermission(#id, 'Person', 'read')")
	public Iterable<Person> getPersons() {
		List<Person> dbresult = personRepository.findAll();
		logger.info("user:{}, query all person information", context.getLoginUser().getUsername());
		return dbresult;
	}

	@PreAuthorize("hasPermission(#id, 'Person', 'read')")
	public Person getPerson(Long personid) {
		Person person = personRepository.findByPersonid(personid);

		if (person == null) {
			throw new BadRequestException("person id is not exist");
		}
		logger.info("User: {}, query person: {} information", context.getLoginUser().getUsername(),
				person.getPersonid());
		return person;
	}

	@PreAuthorize("hasPermission(#id, 'Person', 'write')")
	public Person createPerson(String data) {
		JSONObject object = new JSONObject(data);
		Person person = new Person();
		String action = object.isNull("action") ? "" : object.getString("action");

		if (action.equalsIgnoreCase("create")) {
			JSONObject jsonPerson = object.getJSONObject("person");
			if (jsonPerson == null)
				throw new BadRequestException("payload person object is missed");
			// set identity no
			person.setIdentityno(jsonPerson.isNull("identitycard") ? "" : jsonPerson.getString("identitycard"));
			// set person portrait
			person.setPortrait(jsonPerson.isNull("portrait") ? null : jsonPerson.getString("portrait").getBytes());
			// set person first name
			person.setFirstname(jsonPerson.isNull("firstname") ? "" : jsonPerson.getString("firstname"));
			// set person last name
			person.setLastname(jsonPerson.isNull("lastname") ? "" : jsonPerson.getString("lastname"));
			if (person.getFirstname().isEmpty() || person.getLastname().isEmpty()) {
				throw new BadRequestException("firstname or lastname is null");
			}
			// set birth date
			person.setBirthdate(jsonPerson.isNull("birthdate") ? "" : jsonPerson.getString("birthdate"));
			// set birth place
			person.setBirthplace(jsonPerson.isNull("birthplace") ? "" : jsonPerson.getString("birthplace"));
			// set gender
			person.setGender(jsonPerson.isNull("gender") ? "" : jsonPerson.getString("gender"));

			// set application user
			person.setUsername(jsonPerson.isNull("username") ? null : jsonPerson.getString("username"));
			personRepository.save(person);
			logger.info("User: {}, create person: {} information", context.getLoginUser().getUsername(),
					person.getPersonid());
		}
		else{
			throw new BadRequestException("payload action is missed or not correct");
		}

		return person;
	}

}
