package qms.service.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qms.exception.def.BadRequestException;
import qms.repository.data.FactorEvent;
import qms.repository.data.FactorRepository;
import qms.repository.data.KeyFactor;
import qms.service.context.ContextCheckService;

@Service
public class FactorDataService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FactorRepository factorRepository;

	@Autowired
	ContextCheckService context;

	public Iterable<FactorSummary> getFactorList() {
		List<FactorSummary> result = null;
		try {
			List<KeyFactor> data = factorRepository.findAll();
			result = data.stream().map(p -> {
				return new FactorSummary(p);
			}).collect(Collectors.toList());
			logger.info("user:{}, query KeyFactor list", context.getLoginUser().getUsername());
		} catch (Exception exception) {
			throw new BadRequestException("Internal Server Error");
		}
		return result;
	}

	public KeyFactor getFactor(String key) {
		return factorRepository.findByKey(key);
	}

	public KeyFactor createFactor(String data) {
		JSONObject object = new JSONObject(data);
		KeyFactor factor = new KeyFactor();
		String action = object.isNull("action") ? "" : object.getString("action");

		if (action.equalsIgnoreCase("create")) {
			JSONObject jsonFactor = object.getJSONObject("factor");
			if (jsonFactor == null)
				throw new BadRequestException("payload factor object is missed");

			factor.setKey(jsonFactor.isNull("key") ? "" : jsonFactor.getString("key"));
			factor.setName(jsonFactor.isNull("name") ? "" : jsonFactor.getString("name"));
			factor.setDesc(jsonFactor.isNull("desc") ? null : jsonFactor.getString("desc"));

			factorRepository.save(factor);
			logger.info("User: {}, create factor: {} information", context.getLoginUser().getUsername(),
					factor.getId());
		} else {
			throw new BadRequestException("payload action is missed or not correct");
		}

		return factor;
	}

	public KeyFactor createEvent(String data) {
		JSONObject object = new JSONObject(data);
		KeyFactor factor;
		String action = object.isNull("action") ? "" : object.getString("action");

		if (action.equalsIgnoreCase("create")) {
			JSONObject json = object.getJSONObject("event");
			if (json == null)
				throw new BadRequestException("payload data is missed");

			String factorKey = json.isNull("key") ? "" : json.getString("key");
			// String factorName = json.isNull("factor") ? "" : json.getString("factor");
			String title = json.isNull("title") ? "" : json.getString("title");
			String content = json.isNull("content") ? "" : json.getString("content");
			String date = json.isNull("date") ? "" : json.getString("date");
			Date dt;
			try {
				dt = new SimpleDateFormat("yyyy/MM/dd").parse(date);
			} catch (ParseException e) {
				throw new BadRequestException(e.getMessage());
			}

			factor = factorRepository.findByKey(factorKey);
			if (factor == null)
				throw new BadRequestException("factor key:" + factorKey + " is not existed");

			FactorEvent event = new FactorEvent();
			event.setTitle(title);
			event.setContent(content);
			event.setDate(dt);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			event.setYear(cal.get(Calendar.YEAR));
			event.setMonth(cal.get(Calendar.MONTH) + 1);
			event.setDay(cal.get(Calendar.DAY_OF_MONTH));

			event.setFactor(factor);

			factor.getEvents().add(event);
			factorRepository.save(factor);

			logger.info("User: {}, add event for factor: {} information", context.getLoginUser().getUsername(),
					factor.getKey());
		} else {
			throw new BadRequestException("payload action is missed or not correct");
		}

		return factor;
	}

	public class FactorSummary {

		public String key;
		public String name;
		public String desc;
		public long id;
		public int count;

		public FactorSummary(KeyFactor factor) {
			this.key = factor.getKey();
			this.name = factor.getName();
			this.desc = factor.getDesc();
			this.id = factor.getId();
			this.count = factor.getEvents().size();
		}
	}
}
