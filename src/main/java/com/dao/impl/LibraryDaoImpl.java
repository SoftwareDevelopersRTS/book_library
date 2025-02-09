package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.bo.PaginationBO;
import com.dao.LibraryDao;
import com.model.Address;
import com.model.Library;

@Repository("LibraryDao")
public class LibraryDaoImpl implements LibraryDao {

	private final DataSource dataSource;

	public LibraryDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Library> getLibraryList(PaginationBO pagination) throws Exception {
		List<Library> libraryList = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer(
					"SELECT l.*,a.* FROM library l INNER JOIN address a ON l.address_id=a.address_id where l.library_id IS NOT NULL ");

			if (null != pagination.getSearchKey() && !pagination.getSearchKey().isEmpty()) {
				query.append(
						" AND ( l.library_name LIKE ? OR l.description LIKE ? OR l.library_id LIKE ? OR a.city LIKE ? OR a.street LIKE ? OR a.country LIKE ? OR a.state LIKE ? OR a.zip_code LIKE ?) ");
			}
			if (null != pagination.getOrderBy() && null != pagination.getOrderType()
					&& !pagination.getOrderBy().isEmpty() && !pagination.getOrderType().isEmpty()) {
				query.append(" ORDER BY ").append(pagination.getOrderBy()).append(" ")
						.append(pagination.getOrderType());
			} else {
				query.append(" ORDER BY created_at DESC ");
			}
			if (null != pagination.getPageNo() && null != pagination.getNumPerPage() && pagination.getPageNo() > 0
					&& pagination.getNumPerPage() > 0) {
				query.append(" LIMIT ? OFFSET ?");
			}
			
			int count = 1;
			ps = con.prepareStatement(query.toString());
			if (null != pagination.getSearchKey() && !pagination.getSearchKey().isEmpty()) {
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
				ps.setString(count++, "%" + pagination.getSearchKey() + "%");
			}
			if (null != pagination.getPageNo() && null != pagination.getNumPerPage() && pagination.getPageNo() > 0
					&& pagination.getNumPerPage() > 0) {
				ps.setInt(count++, pagination.getNumPerPage());
				ps.setInt(count++, (pagination.getPageNo() - 1) * pagination.getNumPerPage());
			}

			rs = ps.executeQuery();
			Library library = null;
			Address address = null;
			libraryList = new ArrayList<>();
			while (rs.next()) {
				library = new Library();
				library.setLibraryId(rs.getLong("library_id"));
				library.setLibraryName(rs.getString("library_name"));
				library.setLibraryUniqueUid(rs.getString("library_unique_uid"));
				address = new Address();
				address.setAddressId(rs.getLong("address_id"));
				address.setCity(rs.getString("city"));
				address.setStreet(rs.getString("street"));
				address.setState(rs.getString("state"));
				address.setCountry(rs.getString("country"));
				address.setZipCode(rs.getString("zip_code"));
				library.setAddress(address);
				library.setDescription(rs.getString("description"));
				library.setRating(rs.getFloat("rating"));
				library.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				library.setStartTime(rs.getTime("start_time"));
				library.setEndTime(rs.getTime("end_time"));
				library.setIsActive(rs.getBoolean("is_active"));
				library.setIsStartToday(rs.getBoolean("is_start_today"));

				libraryList.add(library);

			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (null != con) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != ps) {
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != rs) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return libraryList;
	}

	@Override
	public Long getLibraryCount(PaginationBO pagination) throws Exception {
		Long count = 0L;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer query = new StringBuffer(
					"SELECT COUNT(*) FROM library l INNER JOIN address a ON l.address_id=a.address_id WHERE l.library_id IS NOT NULL ");

			if (null != pagination.getSearchKey() && !pagination.getSearchKey().isEmpty()) {
				query.append(
						" AND ( l.library_name LIKE ? OR l.description LIKE ? OR l.library_id LIKE ? OR a.city LIKE ? OR a.street LIKE ? OR a.country LIKE ? OR a.state LIKE ? OR a.zip_code LIKE ?) ");
			}

			ps = con.prepareStatement(query.toString());
			int parameterIndex = 1;
			if (null != pagination.getSearchKey() && !pagination.getSearchKey().isEmpty()) {
				ps.setString(parameterIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(parameterIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(parameterIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(parameterIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(parameterIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(parameterIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(parameterIndex++, "%" + pagination.getSearchKey() + "%");
				ps.setString(parameterIndex++, "%" + pagination.getSearchKey() + "%");
			}

			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != con) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != ps) {
				try {
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != rs) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}

}
