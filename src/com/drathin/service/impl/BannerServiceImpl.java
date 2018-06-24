package com.drathin.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.drathin.common.DBUtils;
import com.drathin.dao.BannerDao;
import com.drathin.dao.impl.BannerDaoImpl;
import com.drathin.model.Banner;
import com.drathin.service.BannerService;

public class BannerServiceImpl implements BannerService{
	
	/**
	 * 类实例
	 */
	private static final BannerService instance = new BannerServiceImpl();

	/**
	 * 取得实例
	 * 
	 * @return 实例对象
	 */
	public static BannerService getInstance() {
		return instance;
	}

	/**
	 * 构造方法
	 */
	private BannerServiceImpl() {
	}

	@Override
	public int bannerCount() {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		//声明变量，用于保存数据库查询结果
		int result = 0;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			//创建userDao的实现类对象
			BannerDao bannerDao = new BannerDaoImpl(conn);
			
			//调用dao中的bannerCount方法，进行数据库查询操作，结果赋值给查询结果变量
			result = bannerDao.bannerCount();	
		
		} catch (Exception e) {
			System.out.println("查询统计所有banner错误"+e.getMessage());
		} finally {
			//调用数据库工具类的closeConnection方法，关闭连接
			DBUtils.closeConnection(conn);
		}
		//返回数据库查询结果
		return result;
	}

	@Override
	public List<Banner> findAllBanner(Map<String, Object> map) {
		// TODO Auto-generated method stub
				//声明数据库连接对象，用于保存数据库连接对象
						Connection conn = null;
						//声明变量，用于保存数据库查询结果
						List<Banner> banners = null;
						try{
							//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
							conn = DBUtils.getConnection();
							//创建userDao的实现类对象
							BannerDao bannerDao = new BannerDaoImpl(conn);
							//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
							banners = bannerDao.findAllBanner(map);			
						
						} catch (Exception e) {
							System.out.println("查询所有banner错误"+e.getMessage());
						} finally {
							//调用数据库工具类的closeConnection方法，关闭连接
							DBUtils.closeConnection(conn);
						}
						//返回数据库查询结果
						return banners;
	}

	@Override
	public int addBanner(String imagePath) {
		//声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		//声明变量，用于保存数据库查询结果
		int result = 0;
		try{
			//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			//创建userDao的实现类对象
			BannerDao BannerDao = new BannerDaoImpl(conn);
			//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
			result = BannerDao.addBanner(imagePath);			
		    DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("增加banner错误"+e.getMessage());
		} finally {
			//调用数据库工具类的closeConnection方法，关闭连接
			DBUtils.closeConnection(conn);
		}
		//返回数据库查询结果
		return result;
	}

	@Override
	public int updateBanner(Banner banner) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBanner(Banner banner) {
		//声明数据库连接对象，用于保存数据库连接对象
				Connection conn = null;
				//声明变量，用于保存数据库查询结果
				int result = 0;
				try{
					//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
					conn = DBUtils.getConnection();
					DBUtils.beginTransaction(conn);
					//创建userDao的实现类对象
					BannerDao BannerDao = new BannerDaoImpl(conn);
					//调用dao中的selectAll方法，进行数据库查询操作，结果赋值给查询结果变量
					result = BannerDao.deleteBanner(banner);
				    DBUtils.commit(conn);
				} catch (Exception e) {
					System.out.println("删除banner错误"+e.getMessage());
				} finally {
					//调用数据库工具类的closeConnection方法，关闭连接
					DBUtils.closeConnection(conn);
				}
				//返回数据库查询结果
				return result;
	}

	@Override
	public Banner findOneBanner(Banner banner) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
