package vn.edu.vnuk.airlines.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.airlines.model.PlaneManufacturer;
import vn.edu.vnuk.airlines.model.PlaneModel;

public class PlaneModelRowMapper implements RowMapper<PlaneModel> {

	@Override
	public PlaneModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PlaneManufacturer planeManufacturer = new PlaneManufacturer();
		PlaneModel planeModel = new PlaneModel();
		
		planeManufacturer.setId(rs.getLong("plane_manufacturer_id"));
		planeManufacturer.setLabel(rs.getString("plane_manufacturer_label"));
		
		planeModel.setId(rs.getLong("id"));
		planeModel.setPlaneManufacturerId(planeManufacturer.getId());
		planeModel.setLabel(rs.getString("label"));
		planeModel.setPlaneManufacturer(planeManufacturer);
		
		return planeModel;
	}
	
	
	public List<PlaneModel> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<PlaneModel> planeModels = new ArrayList<PlaneModel>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		PlaneManufacturer planeManufacturer = new PlaneManufacturer();
			PlaneModel planeModel = new PlaneModel();
			
			planeManufacturer.setId((Long) row.get("plane_manufacturer_id"));
			planeManufacturer.setLabel((String) row.get("plane_manufacturer_label"));
			
			planeModel.setId((Long) row.get("id"));
			planeModel.setPlaneManufacturerId((Long) row.get("plane_manufacturer_id"));
			planeModel.setLabel((String) row.get("label"));
			planeModel.setPlaneManufacturer(planeManufacturer);
			
			planeModels.add(planeModel);
			
		}
		
    	
		return planeModels;

	}

}
