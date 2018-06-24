package com.drathin.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.alibaba.fastjson.JSONObject;
import com.drathin.common.FileUtils;
import com.drathin.model.Banner;
import com.drathin.service.BannerService;
import com.drathin.service.impl.BannerServiceImpl;

public class BannerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BannerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");
		if ("findAllBanner".equals(action)) {
			dofindAllBanner(request, response);
		} else if ("addBanner".equals(action)) {
			doAddBanner(request, response);
		} else if ("delBanner".equals(action)) {
			doDelBanner(request, response);
		}
	}

	private void doDelBanner(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ����Service����
		BannerService bannerService = BannerServiceImpl.getInstance();
		int bannerid = Integer.parseInt(request.getParameter("bannerid"));
		Banner banner = new Banner();
		banner.setBannerid(bannerid);
		int res = bannerService.deleteBanner(banner);
		System.out.println(res);
		Map<String, Object> map = new HashMap<String, Object>();
		if (res > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
			map.put("errorMsg", "delete contact fail !!!");
		}

		String str = JSONObject.toJSONString(map);
		response.getWriter().write(str);

	}

	@SuppressWarnings("unchecked")
	private void doAddBanner(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡsession
		HttpSession se = request.getSession();
		// ����Service����
		BannerService bannerService = BannerServiceImpl.getInstance();

		// �ϴ��ļ���Ŀ¼
		//	 String path = request.getSession().getServletContext().getRealPath("/")+"images";    //�ļ�ϵͳ��·��
        // String path= System.getProperty("catalina.home") + "\\webapps\\lvcityFG\\images\\";    //tomcat�����·��
       				
		//�����ļ�����
		File savePath = new File(FileUtils.UPLOAD_PATH);	
		
		// ��������Ƿ����ļ��ϴ�����
		//boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		// 1,����DiskFileItemFactory�����࣬������ָ������������һ����ʱĿ¼
		DiskFileItemFactory disk = new DiskFileItemFactory(1024 * 10, savePath);
	
		// 2,����ServletFileUpload�������ϱߵ���ʱ�ļ���Ҳ����Ĭ��ֵ
		ServletFileUpload up = new ServletFileUpload(disk);		

		try {
			// 3,����request
			List<FileItem> list;
			list = up.parseRequest(request);
			// �����һ���ļ���
			FileItem file = list.get(0);
			// ��ȡ�ļ�����
			String fileName = file.getName();
			
			// ��ȡ�ļ������ͣ�
			//String fileType = file.getContentType();
			// �ļ���С
	     	//	int size = file.getInputStream().available();
			
			// ��ȡ�ļ��������������ڶ�ȡҪ�ϴ����ļ�
			InputStream in = file.getInputStream();
			
			// ��������ֽ��������ڽ��ļ��ϴ���Ŀ��λ��
			OutputStream out = new FileOutputStream(new File(savePath + "/" + fileName));
		
			
			// �ļ�copy
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
				//out2.write(b, 0, len);
			}
			out.flush();
			out.close();
		

			// ɾ���ϴ����ɵ���ʱ�ļ�
			file.delete();
			
			//�����ݿ���Ӽ�¼����ǰ�����ͼƬ��������ǰ׺			
			int result = bannerService.addBanner("http://localhost:9080/uploads/" + fileName);
			
			//���ɷ��ؽ����map
			Map<String, Object> map = new HashMap<String, Object>();
			if (result > 0) {
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("errorMsg", "Save user fail !");
			}
           //��mapת��JSON����
			String str = JSONObject.toJSONString(map);
			//���ؽ��
			response.getWriter().write(str);

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void dofindAllBanner(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// page��ҳ�룬��ʼֵ 1�� rows��ÿҳ��ʾ�С� pageΪǰ̨Ҫ��ѯ��ҳ��rowsΪǰ̨��ÿҳ��¼��
		int rows = Integer.parseInt(request.getParameter("rows"));
		// System.out.println(rows);
		int page = Integer.parseInt(request.getParameter("page"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);

		// ����Service����
		BannerService bannerService = BannerServiceImpl.getInstance();
		//����ָ��ҳ�ļ�¼
		List<Banner> list = bannerService.findAllBanner(pageMap);
		// System.out.println(list.size());
		//�����ܼ�¼��
		int total = bannerService.bannerCount();

		// JSON�У�total��¼������rows��¼��totalΪ��̨���صģ����ݿ�ģ��ܼ�¼�������ڶ�����rowsΪ��̨���ص�json�������顣
		map.put("rows", list);
		map.put("total", total);
		String str = JSONObject.toJSONString(map);
		// System.out.println(map.toString());
		response.getWriter().write(str);

		/*
		 * //��ȡsession HttpSession se = request.getSession(); // ����Service����
		 * BannerService bannerService = BannerServiceImpl.getInstance();
		 * List<Banner> banners = null; // ��ѯ�����û� banners =
		 * bannerService.findAllBanner(); if(banners != null) { //
		 * System.out.println(banners.size()); se.setAttribute("bannerList",
		 * banners); response.sendRedirect(request.getContextPath() +
		 * "/Admin/banner.jsp");
		 * 
		 * }
		 */
	}

}
