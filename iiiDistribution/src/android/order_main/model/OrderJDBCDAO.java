package android.order_main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import iii.size.model.SizeJDBCDAO;
//import iii.size.model.SizeVO;

public class OrderJDBCDAO implements OrderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.56.3:1521:XE";
	String userid = "GROUP4";
	String passwd = "GROUP4";
	
	private static final String INSERT_STMT=
			"INSERT INTO ORDER_MAIN(ORDER_ID, EMP_ID, MEMBER_ID, DB_ID, ORDER_STATUS, PAYMENT_TYPE, FEE, EXTRAFEE, ITEM_SIZE, ITEM_WEIGHT, "
			+ "ITEM_TYPE, RECEIVER_NAME, RECEIVER_TEL, RECEIVER_CELL, RECEIVER_CITY, RECEIVER_COUNTY, RECEIVER_ADDRESS, "
			+ "RECEIVER_MAIL, SENDER_NAME, SENDER_TEL, SENDER_CELL, SENDER_CITY, SENDER_COUNTY, SENDER_ADDRESS, EXPECTED_TIME, ORDER_NOTE, CREATE_TIME)"
			+ "VALUES('ORD'||LPAD(TO_CHAR(ORDER_MAIN_SEQ.NEXTVAL),3 ,'0'), ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ?, ?, ? ,? ,? ,? ,? ,? ,?, ?, SYSDATE)";
//	private static final String UPDATE =	
//			"UPDATE ORDER_MAIN SET EMP_ID=?, MEMBER_ID=?, DB_ID=?, PAYMENT_TYPE=?, FEE=?, EXTRAFEE=?, ITEM_SIZE=?, ITEM_WEIGHT=?, ITEM_TYPE=?, "
//			+ "ORDER_STATUS=?, CREATE_TIME=?, RECEIVER_NAME=?, RECEIVER_TEL=?, RECEIVER_COUNTRY=?, RECEIVER_CITY=?, RECEIVER_COUNTY=?, "
//			+ "RECEIVER_ADDRESS=?, RECEIVER_MAIL=?, SENDER_NAME=?, SENDER_TEL=?, SENDER_MAIL=?, EXPECTED_TIME=?, ORDER_NOTE=?, ORDER_UPDATETIME=? WHERE ORDER_ID=?";
//	private static final String DELETE = 
//			"DELETE FROM ORDER_MAIN WHERE ORDER_ID = ?";
//	private static final String GET_ONE_STMT = 
//			"SELECT ORDER_ID, EMP_ID, MEMBER_ID, DB_ID, PAYMENT_TYPE, FEE, EXTRAFEE, ITEM_SIZE, ITEM_WEIGHT, ITEM_TYPE, ORDER_STATUS, "
//			+ "TO_CHAR(CREATE_TIME, 'yyyy-mm-dd hh:mm:ss')CREATE_TIME, RECEIVER_NAME, RECEIVER_TEL, RECEIVER_COUNTRY, RECEIVER_CITY, RECEIVER_COUNTY, "
//			+ "RECEIVER_ADDRESS, RECEIVER_MAIL, SENDER_NAME, SENDER_TEL, SENDER_MAIL, TO_CHAR(EXPECTED_TIME, 'yyyy-mm-dd hh:mm:ss')EXPECTED_TIME, "
//			+ "ORDER_NOTE, TO_CHAR(ORDER_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')ORDER_UPDATETIME FROM ORDER_MAIN WHERE ORDER_ID = ?";
	private static final String GET_ALL_STMT = 
			"SELECT ORDER_ID, EMP_ID, MEMBER_ID, DB_ID, ORDER_STATUS, PAYMENT_TYPE, FEE, EXTRAFEE, ITEM_SIZE, ITEM_WEIGHT, ITEM_TYPE, "
			+ "TO_CHAR(CREATE_TIME, 'yyyy-mm-dd hh:mm:ss')CREATE_TIME, RECEIVER_NAME, RECEIVER_TEL, RECEIVER_CELL, RECEIVER_CITY, RECEIVER_COUNTY, "
			+ "RECEIVER_ADDRESS, RECEIVER_MAIL, SENDER_NAME, SENDER_TEL, SENDER_CELL, SENDER_CITY, SENDER_COUNTY, SENDER_ADDRESS, TO_CHAR(EXPECTED_TIME, 'yyyy-mm-dd hh:mm:ss')EXPECTED_TIME, "
			+ "ORDER_NOTE, TO_CHAR(ORDER_UPDATETIME, 'yyyy-mm-dd hh:mm:ss')ORDER_UPDATETIME FROM ORDER_MAIN ORDER BY ORDER_ID";
	
//	private static final String GET_ONE_STMT = 
//			"SELECT * FROM ORDER_MAIN WHERE ORDER_ID = ?";
	
//	private static final String GET_ALL_STMT = 
//			"SELECT * FROM ORDER_MAIN ORDER BY ORDER_ID";
	
	
	
	@Override
	public Integer insert(OrderVO orderVO) {
		Integer insertShow = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, orderVO.getEmp_id());
			pstmt.setString(2, orderVO.getMem_id());
			pstmt.setString(3, orderVO.getDb_id());
			pstmt.setString(4, orderVO.getOrder_status());
			pstmt.setString(5, orderVO.getPayment_type());
			pstmt.setDouble(6, orderVO.getFee());
			pstmt.setDouble(7, orderVO.getExtrafee());
			pstmt.setDouble(8, orderVO.getItem_size());
			pstmt.setDouble(9, orderVO.getItem_weight());
			pstmt.setString(10, orderVO.getItem_type());
			pstmt.setString(11, orderVO.getReceiver_name());
			pstmt.setString(12, orderVO.getReceiver_tel());
			pstmt.setString(13, orderVO.getReceiver_cell());
			pstmt.setString(14, orderVO.getReceiver_city());
			pstmt.setString(15, orderVO.getReceiver_county());
			pstmt.setString(16, orderVO.getReceiver_address());
			pstmt.setString(17, orderVO.getReceiver_mail());
			pstmt.setString(18, orderVO.getSender_name());
			pstmt.setString(19, orderVO.getSender_tel());
			pstmt.setString(20, orderVO.getReceiver_cell());
			pstmt.setString(21, orderVO.getSender_city());
			pstmt.setString(22, orderVO.getSender_county());
			pstmt.setString(23, orderVO.getSender_address());
			pstmt.setTimestamp(24, orderVO.getExpected_time());
			pstmt.setString(25, orderVO.getOrder_note());
				
			insertShow = pstmt.executeUpdate();		
			
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			} 
			if(con != null) {
				try {
				con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return insertShow;
	}

//	@Override
//	public Integer update(OrderVO orderVO) {
//		Integer updateShow = 0;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			
//			pstmt.setString(25, orderVO.getOrder_id());
//			pstmt.setString(1, orderVO.getEmp_id());
//			pstmt.setString(2, orderVO.getMem_id());
//			pstmt.setString(3, orderVO.getDb_id());
//			pstmt.setString(4, orderVO.getPayment_type());
//			pstmt.setDouble(5, orderVO.getFee());
//			pstmt.setDouble(6, orderVO.getExtrafee());
//			pstmt.setDouble(7, orderVO.getItem_size());
//			pstmt.setDouble(8, orderVO.getItem_weight());
//			pstmt.setString(9, orderVO.getItem_type());
//			pstmt.setString(10, orderVO.getOrder_status());
//			pstmt.setTimestamp(11, orderVO.getCreate_time());
//			pstmt.setString(12, orderVO.getReceiver_name());
//			pstmt.setString(13, orderVO.getReceiver_tel());
//			pstmt.setString(14, orderVO.getReceiver_country());
//			pstmt.setString(15, orderVO.getReceiver_city());
//			pstmt.setString(16, orderVO.getReceiver_county());
//			pstmt.setString(17, orderVO.getReceiver_address());
//			pstmt.setString(18, orderVO.getReceiver_mail());
//			pstmt.setString(19, orderVO.getSender_name());
//			pstmt.setString(20, orderVO.getSender_tel());
//			pstmt.setString(21, orderVO.getSender_mail());
//			pstmt.setTimestamp(22, orderVO.getExpected_time());
//			pstmt.setString(23, orderVO.getOrder_note());
//			pstmt.setTimestamp(24, orderVO.getOrder_updatetime());
//			
//			updateShow = pstmt.executeUpdate();
//			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try{
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		}	
//		return updateShow;
//	}
//
//	@Override
//	public void delete(String order_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet deleteShow = null;
//		
//		try {
//			Class.forName(driver);
//			con  = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//			
//			pstmt.setString(1, order_id);
//			
//			deleteShow = pstmt.executeQuery();
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}		
//	}
//
//	@Override
//	public OrderVO findByPrimaryKey(String order_id) {
//
//		OrderVO orderVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			
//			pstmt.setString(1, order_id);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()){
//				orderVO = new OrderVO();
//				orderVO.setOrder_id(rs.getString("ORDER_ID"));
//				orderVO.setEmp_id(rs.getString("EMP_ID"));
//				orderVO.setMem_id(rs.getString("MEMBER_ID"));
//				orderVO.setDb_id(rs.getString("DB_ID"));
//				orderVO.setPayment_type(rs.getString("PAYMENT_TYPE"));
//				orderVO.setFee(rs.getDouble("FEE"));
//				orderVO.setExtrafee(rs.getDouble("EXTRAFEE"));
//				orderVO.setItem_size(rs.getDouble("ITEM_SIZE"));
//				orderVO.setItem_weight(rs.getDouble("ITEM_WEIGHT"));
//				orderVO.setItem_type(rs.getString("ITEM_TYPE"));
//				orderVO.setOrder_status(rs.getString("ORDER_STATUS"));
//				orderVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
//				orderVO.setReceiver_name(rs.getString("RECEIVER_NAME"));
//				orderVO.setReceiver_tel(rs.getString("RECEIVER_TEL"));
//				orderVO.setReceiver_country(rs.getString("RECEIVER_COUNTRY"));
//				orderVO.setReceiver_city(rs.getString("RECEIVER_CITY"));
//				orderVO.setReceiver_mail(rs.getString("RECEIVER_MAIL"));
//				orderVO.setReceiver_county(rs.getString("RECEIVER_COUNTY"));
//				orderVO.setReceiver_address(rs.getString("RECEIVER_ADDRESS"));
//				orderVO.setSender_name(rs.getString("SENDER_NAME"));
//				orderVO.setSender_tel(rs.getString("SENDER_TEL"));
//				orderVO.setSender_mail(rs.getString("SENDER_MAIL"));
//				orderVO.setExpected_time(rs.getTimestamp("EXPECTED_TIME"));
//				orderVO.setOrder_note(rs.getString("ORDER_NOTE"));
//				orderVO.setOrder_updatetime(rs.getTimestamp("ORDER_UPDATETIME"));	
//				
//				ResultSetMetaData rm = rs.getMetaData();
//				System.out.println(rm.getColumnCount());
//				System.out.println(rm.getColumnDisplaySize(1));
//				System.out.println(rm.getColumnType(1));
//				System.out.println(rm.getPrecision(1));
//				System.out.println(rm.getScale(1));
//				System.out.println(rm.getColumnLabel(1));
//				System.out.println(rm.getColumnName(1));
//				System.out.println(rm.getColumnClassName(1));
//				System.out.println(rm.isCurrency(1));
//				
//				
//				System.out.println("=========");
//				
//			}
//			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}	
//		}	
//		return orderVO;
//	}
	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				orderVO = new OrderVO();
				orderVO.setOrder_id(rs.getString("ORDER_ID"));
				orderVO.setEmp_id(rs.getString("EMP_ID"));
				orderVO.setMem_id(rs.getString("MEMBER_ID"));
				orderVO.setDb_id(rs.getString("DB_ID"));
				orderVO.setOrder_status(rs.getString("ORDER_STATUS"));
				orderVO.setPayment_type(rs.getString("PAYMENT_TYPE"));
				orderVO.setFee(rs.getDouble("FEE"));
				orderVO.setExtrafee(rs.getDouble("Extrafee"));
				orderVO.setItem_size(rs.getDouble("ITEM_SIZE"));
				orderVO.setItem_weight(rs.getDouble("ITEM_WEIGHT"));
				orderVO.setItem_type(rs.getString("ITEM_TYPE"));
				orderVO.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				orderVO.setReceiver_name(rs.getString("RECEIVER_NAME"));
				orderVO.setReceiver_tel(rs.getString("RECEIVER_TEL"));
				orderVO.setReceiver_cell(rs.getString("RECEIVER_CELL"));
				orderVO.setReceiver_city(rs.getString("RECEIVER_CITY"));
				orderVO.setReceiver_county(rs.getString("RECEIVER_COUNTY"));
				orderVO.setReceiver_address(rs.getString("RECEIVER_ADDRESS"));
				orderVO.setReceiver_mail(rs.getString("RECEIVER_MAIL"));
				orderVO.setSender_name(rs.getString("SENDER_NAME"));
				orderVO.setSender_tel(rs.getString("SENDER_TEL"));
				orderVO.setSender_cell(rs.getString("SENDER_CELL"));
				orderVO.setSender_city(rs.getString("SENDER_CITY"));
				orderVO.setSender_county(rs.getString("SENDER_COUNTY"));
				orderVO.setSender_address(rs.getString("SENDER_ADDRESS"));
				orderVO.setExpected_time(rs.getTimestamp("EXPECTED_TIME"));
				orderVO.setOrder_note(rs.getString("ORDER_ID"));
				orderVO.setOrder_updatetime(rs.getTimestamp("ORDER_UPDATETIME"));		
				list.add(orderVO);
			}
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String args[]){
		OrderJDBCDAO dao = new OrderJDBCDAO();
		
		OrderVO orderVO1 = new OrderVO();
		orderVO1.setEmp_id("EMP001");
		orderVO1.setMem_id("MEM001");
		orderVO1.setDb_id("DB01");
		orderVO1.setPayment_type("PAYMENT_TYPE");
		orderVO1.setOrder_status("ORDER_STATUS");
		orderVO1.setFee(9000.00);
		orderVO1.setExtrafee(9030.31);
		orderVO1.setItem_size(3020.20);
		orderVO1.setItem_weight(6050.22);
		orderVO1.setItem_type("ITEM_TYPE");
		orderVO1.setReceiver_name("RECEIVER_NAME");
		orderVO1.setReceiver_tel("RECEIVER_TEL");
		orderVO1.setReceiver_cell("RECEIVER_CELL");
		orderVO1.setReceiver_city("RECEIVER_CITY");
		orderVO1.setReceiver_mail("RECEIVER_MAIL");
		orderVO1.setReceiver_county("RECEIVER_COUNTY");
		orderVO1.setReceiver_address("RECEIVER_address");
		orderVO1.setSender_name("SENDER_NAME");
		orderVO1.setSender_tel("SENDER_TEL");
		orderVO1.setSender_cell("SENDER_CELL");
		orderVO1.setSender_city("SENDER_CITY");
		orderVO1.setSender_county("SENDER_COUNTY");
		orderVO1.setSender_address("SENDER_address");
		orderVO1.setExpected_time(java.sql.Timestamp.valueOf("2011-07-31 07:33:59"));
		orderVO1.setOrder_note("ORDER_ID");
		System.out.println("共新增" + dao.insert(orderVO1) + "一筆資料");
		
//		
//		OrderVO orderVO2 = new OrderVO();
//		orderVO2.setOrder_id("ORD001");
//		orderVO2.setEmp_id("EMP001");
//		orderVO2.setMem_id("MEM001");
//		orderVO2.setDb_id("DB01");
//		orderVO2.setPayment_type("PAYMENT_TYPE");
//		orderVO2.setFee(9000.00);
//		orderVO2.setExtrafee(9030.31);
//		orderVO2.setItem_size(3020.20);
//		orderVO2.setItem_weight(6050.22);
//		orderVO2.setItem_type("ITEM_TYPE");
//		orderVO2.setOrder_status("ORDER_STATUS");
//		orderVO2.setCreate_time(java.sql.Timestamp.valueOf("2014-06-30 04:02:03"));
//		orderVO2.setReceiver_name("RECEIVER_NAME");
//		orderVO2.setReceiver_tel("RECEIVER_TEL");
//		orderVO2.setReceiver_country("RECEIVER_COUNTRY");
//		orderVO2.setReceiver_city("RECEIVER_CITY");
//		orderVO2.setReceiver_mail("RECEIVER_MAIL");
//		orderVO2.setReceiver_county("RECEIVER_COUNTY");
//		orderVO2.setReceiver_address("RECEIVER_NAME");
//		orderVO2.setSender_name("SENDER_NAME");
//		orderVO2.setSender_tel("SENDER_TEL");
//		orderVO2.setSender_mail("SENDER_MAIL");
//		orderVO2.setExpected_time(java.sql.Timestamp.valueOf("2011-07-31 07:33:59"));
//		orderVO2.setOrder_note("ORDER_ID");
//		orderVO2.setOrder_updatetime(java.sql.Timestamp.valueOf("2019-07-31 07:33:59"));
//		System.out.println("��s" + dao.update(orderVO2) + "�����");
//		
//		dao.delete("ORD002");
//		System.out.println("Deleting is OK!");
//		
//		OrderVO orderVO3 = new OrderVO();
//		System.out.println("======�浧�d��======");
//		orderVO3 = dao.findByPrimaryKey("ORD001");
//		System.out.println(orderVO3.getOrder_id());
//		System.out.println(orderVO3.getEmp_id());
//		System.out.println(orderVO3.getMem_id());
//		System.out.println(orderVO3.getDb_id());
//		System.out.println(orderVO3.getPayment_type());
//		System.out.println(orderVO3.getFee());
//		System.out.println(orderVO3.getExtrafee());
//		System.out.println(orderVO3.getItem_type());
//		System.out.println(orderVO3.getItem_size());
//		System.out.println(orderVO3.getItem_weight());
//		System.out.println(orderVO3.getOrder_status());
//		System.out.println(orderVO3.getCreate_time());
//		System.out.println(orderVO3.getReceiver_name());
//		System.out.println(orderVO3.getReceiver_tel());
//		System.out.println(orderVO3.getReceiver_country());
//		System.out.println(orderVO3.getReceiver_city());
//		System.out.println(orderVO3.getReceiver_mail());
//		System.out.println(orderVO3.getReceiver_county());
//		System.out.println(orderVO3.getReceiver_address());
//		System.out.println(orderVO3.getSender_name());
//		System.out.println(orderVO3.getSender_tel());
//		System.out.println(orderVO3.getSender_mail());
//		System.out.println(orderVO3.getExpected_time());
//		System.out.println(orderVO3.getOrder_note());
//		System.out.println(orderVO3.getOrder_updatetime());
//		
		List<OrderVO> list = dao.getAll();
		int i = 0;
		System.out.println("======查詢全部======");
		for(OrderVO aOrder : list){
			System.out.println("======" + ++i + "======" );
			System.out.println(aOrder.getOrder_id());
			System.out.println(aOrder.getEmp_id());
			System.out.println(aOrder.getMem_id());
			System.out.println(aOrder.getDb_id());
			System.out.println(aOrder.getOrder_status());
			System.out.println(aOrder.getPayment_type());
			System.out.println(aOrder.getFee());
			System.out.println(aOrder.getExtrafee());
			System.out.println(aOrder.getItem_type());
			System.out.println(aOrder.getItem_size());
			System.out.println(aOrder.getItem_weight());
			System.out.println(aOrder.getCreate_time());
			System.out.println(aOrder.getReceiver_name());
			System.out.println(aOrder.getReceiver_tel());
			System.out.println(aOrder.getReceiver_cell());
			System.out.println(aOrder.getReceiver_city());
			System.out.println(aOrder.getReceiver_mail());
			System.out.println(aOrder.getReceiver_county());
			System.out.println(aOrder.getReceiver_address());
			System.out.println(aOrder.getSender_name());
			System.out.println(aOrder.getSender_tel());
			System.out.println(aOrder.getSender_cell());
			System.out.println(aOrder.getSender_city());
			System.out.println(aOrder.getSender_county());
			System.out.println(aOrder.getSender_address());
			System.out.println(aOrder.getExpected_time());
			System.out.println(aOrder.getOrder_note());
			System.out.println(aOrder.getOrder_updatetime());
		}	
	}

	@Override
	public Integer update(OrderVO orderVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String order_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderVO findByPrimaryKey(String order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> findByMem(String mem_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> findByEmp(String emp_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer UpdateStatusDbByPrimaryKey(String order_status, String db_id, String order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertOneWithOrderIDLo(String emp_id, String order_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertOneWithOrderIDFo(String emp_id, String order_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
