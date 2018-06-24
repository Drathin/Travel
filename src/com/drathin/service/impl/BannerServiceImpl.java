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
	 * ��ʵ��
	 */
	private static final BannerService instance = new BannerServiceImpl();

	/**
	 * ȡ��ʵ��
	 * 
	 * @return ʵ������
	 */
	public static BannerService getInstance() {
		return instance;
	}

	/**
	 * ���췽��
	 */
	private BannerServiceImpl() {
	}

	@Override
	public int bannerCount() {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		int result = 0;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			//����userDao��ʵ�������
			BannerDao bannerDao = new BannerDaoImpl(conn);
			
			//����dao�е�bannerCount�������������ݿ��ѯ�����������ֵ����ѯ�������
			result = bannerDao.bannerCount();	
		
		} catch (Exception e) {
			System.out.println("��ѯͳ������banner����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return result;
	}

	@Override
	public List<Banner> findAllBanner(Map<String, Object> map) {
		// TODO Auto-generated method stub
				//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
						Connection conn = null;
						//�������������ڱ������ݿ��ѯ���
						List<Banner> banners = null;
						try{
							//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
							conn = DBUtils.getConnection();
							//����userDao��ʵ�������
							BannerDao bannerDao = new BannerDaoImpl(conn);
							//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
							banners = bannerDao.findAllBanner(map);			
						
						} catch (Exception e) {
							System.out.println("��ѯ����banner����"+e.getMessage());
						} finally {
							//�������ݿ⹤�����closeConnection�������ر�����
							DBUtils.closeConnection(conn);
						}
						//�������ݿ��ѯ���
						return banners;
	}

	@Override
	public int addBanner(String imagePath) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		int result = 0;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			//����userDao��ʵ�������
			BannerDao BannerDao = new BannerDaoImpl(conn);
			//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
			result = BannerDao.addBanner(imagePath);			
		    DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("����banner����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return result;
	}

	@Override
	public int updateBanner(Banner banner) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBanner(Banner banner) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
				Connection conn = null;
				//�������������ڱ������ݿ��ѯ���
				int result = 0;
				try{
					//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
					conn = DBUtils.getConnection();
					DBUtils.beginTransaction(conn);
					//����userDao��ʵ�������
					BannerDao BannerDao = new BannerDaoImpl(conn);
					//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
					result = BannerDao.deleteBanner(banner);
				    DBUtils.commit(conn);
				} catch (Exception e) {
					System.out.println("ɾ��banner����"+e.getMessage());
				} finally {
					//�������ݿ⹤�����closeConnection�������ر�����
					DBUtils.closeConnection(conn);
				}
				//�������ݿ��ѯ���
				return result;
	}

	@Override
	public Banner findOneBanner(Banner banner) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
