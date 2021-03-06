package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.parks.CampsiteDAO;
import com.techelevator.parks.JDBCCampsiteDAO;

public class JDBCCampsiteDAOTest extends DAOIntegrationTest {


	CampsiteDAO dao;

	@Before
	public void setupDAO() {
		DataSource dataSource = getDataSource();
		dao = new JDBCCampsiteDAO(dataSource);
		
	}
	@Test
	public void test_dao_returns_several_campsites() {
		addParkToDatabase(1);
		addCampgroundToDatabase(1l,1l);
		addCampsiteToDatabase(1l);
		addCampsiteToDatabase(1l);
		addCampsiteToDatabase(1l);
		assertEquals(3, dao.getAllCampsitesForCampground(1l).size());
	}
	
	@Test
	public void test_dao_returns_only_campsites_for_specified_campground() {
		addParkToDatabase(1);
		addCampgroundToDatabase(1l,1l);
		addCampsiteToDatabase(1l);
		addCampsiteToDatabase(1l);
		addCampsiteToDatabase(1l);
		addCampgroundToDatabase(1l,2l);
		addCampsiteToDatabase(2l);
		addCampsiteToDatabase(2l);
		addCampsiteToDatabase(2l);
		assertEquals(3, dao.getAllCampsitesForCampground(1l).size());
	}
	
	@Test
	public void dao_successfully_filters_out_overlapping_reservations() {
		addParkToDatabase(1);
		addCampgroundToDatabase(1l,1l);
		addCampsiteToDatabase(1l,1l);
		addCampsiteToDatabase(1l,2l);
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd", Locale.ENGLISH);
		LocalDate date = LocalDate.parse("2016-10-15");
		LocalDate date2 = LocalDate.parse("2016-10-17");
		//addReservationToDatabase(1l,"Cool Site",date , date2);
		//addReservationToDatabase(1l,"Cool Site",date , date2);
		//List<Campsite> site1 = addReservationToDatabase(1l,"Cool Site",date , date2);
		assertEquals(1,dao.getAvailableCampsitesByDate(date, date2).size());
		
		
	}
	

}
