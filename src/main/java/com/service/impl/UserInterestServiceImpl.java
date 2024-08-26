package com.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.PaginationBO;
import com.helper.UserInterestEnums.InterestOn;
import com.model.UserInterest;
import com.service.UserInterestService;
import com.utils.CommonChecks;

@Service
public class UserInterestServiceImpl implements UserInterestService {
	
	@Autowired
	private DataSource dataSource;

	@Override
	public List<UserInterest> providedSortedUserInterestedList(PaginationBO pagination) throws Exception {
		List<UserInterest> userInterestList=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuffer query=new StringBuffer("SELECT * FROM user_interest where user_interest_id is not null ");
			if(null!=pagination.getUserId()) {
				query.append(" AND user_id= ? ");
			}
			if(CommonChecks.paginationPageNumberCheck(pagination)) {
				query.append(" LIMIT ? OFFSET ? ");
			}
			ps=con.prepareStatement(query.toString());
			int count=1;
			if(null!=pagination.getUserId()) {
				ps.setLong(count++, pagination.getUserId());
			}
			if(CommonChecks.paginationPageNumberCheck(pagination)) {
				ps.setInt(count++, pagination.getNumPerPage());
				ps.setInt(count++, (pagination.getPageNo()-1)*pagination.getNumPerPage());
			}
			
			rs=ps.executeQuery();
			
			userInterestList=new ArrayList<>();
			UserInterest interest=null;
			while(rs.next()) {
				interest=new UserInterest();
				//To get interest on what so interest can be on Book ,Book category , Library etc
				String interestOn=rs.getString("interest_on");
				if(null!=interestOn && !interestOn.isEmpty()) {
					interest.setInterestOn(InterestOn.valueOf(interestOn));
				}
				
				//To get Interest type it can be true or false simply means true means interested and false means not interested
				interest.setIsInterested(rs.getBoolean("is_interested"));
				//
				if(interest.getInterestOn().equals(InterestOn.BOOK)) {
					interest.setBookId(rs.getLong("book_id"));
				}else if(interest.getInterestOn().equals(InterestOn.BOOKCATEGORY)) {
					interest.setBookCategoryId(rs.getLong("book_category_id"));
				}else {
					interest.setLibraryId(rs.getLong("library_id"));
				}
				
				
			}

		} catch (Exception e) {

		}finally {
			if(null!=con) {
				try {
					con.close();
				}catch(Exception e) {
					
				}
			}
			if(null!=ps) {
				try {
					ps.close();
				}catch(Exception e) {
					
				}
			}
			if(null!=rs) {
				try {
					rs.close();
				}catch(Exception e) {
					
				}
			}
		}
		return userInterestList; 
	}

}
