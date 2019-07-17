package qms.controller.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import qms.controller.BaseController;
import qms.repository.person.Person;
import qms.service.person.PersonService;

@RestController
public class PersonController extends BaseController {

	final String PATH_GET_ALL_PERSON = "/api/persons";
	final String PATH_GET_CREATE_EDIT_PERSON = "/api/person";

	@Autowired
	PersonService personService;

	/***
	 * query all persons
	 * 
	 * @return
	 */
	@RequestMapping(path = PATH_GET_ALL_PERSON, method = RequestMethod.GET)
	public Iterable<Person> getPersons() {
		return personService.getPersons();
	}

	/***
	 * query single person
	 * 
	 * @param personid
	 * @return
	 */
	@RequestMapping(path = PATH_GET_CREATE_EDIT_PERSON + "/{personid}", method = RequestMethod.GET)
	public Person getPerson(@PathVariable Long personid) {
		return personService.getPerson(personid);
	}

	/**
	 * create a new person
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(path = PATH_GET_CREATE_EDIT_PERSON, method = RequestMethod.POST)
	public Person createPerson(@RequestBody String data) {
		return personService.createPerson(data);
	}

}
