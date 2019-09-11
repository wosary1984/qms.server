package qms.controller.wafer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import qms.controller.BaseController;
import qms.repository.wafer.Wafer;
import qms.repository.wafer.WaferData;
import qms.service.wafer.WaferDataService;
import qms.service.wafer.WaferDataService.WaferCode;

@RestController
public class WaferDaraController extends BaseController {

	final String PATH_WAFER = "/api/wafer";

	@Autowired
	WaferDataService waferDataService;

	@RequestMapping(path = PATH_WAFER + "/{waferid}", method = RequestMethod.GET)
	public Iterable<Wafer> getWaferData(@PathVariable String waferid) {

		// return new WaferModel(waferDataService.getWaferData(waferid),
		// waferDataService.getWaferRadius(waferid));

		return waferDataService.getWaferData(waferid);
	}

	@RequestMapping(path = PATH_WAFER, method = RequestMethod.GET)
	public Iterable<WaferCode> getWaferData() {

		return waferDataService.getWaferList();
	}

	protected class WaferModel {

		public List<WaferData> data = null;
		public long radius = 0;

		public WaferModel(Iterable<WaferData> data, long radius) {
			this.data = (List<WaferData>) data;
			this.radius = radius;
		}
	}

}
