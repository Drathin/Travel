package com.drathin.dao;

import java.util.List;
import java.util.Map;

import com.drathin.model.Banner;

public interface BannerDao {
		
		public int bannerCount();
		public List<Banner> findAllBanner(Map<String, Object> map);
		public int addBanner(String imagePath);
		public int updateBanner(Banner banner);
		public int deleteBanner(Banner banner);
		public Banner findOneBanner(Banner banner);
	}