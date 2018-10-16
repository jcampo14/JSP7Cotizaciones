package com.aspsols.cotizaciones.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.commons.beanutils.converters.AllTypesConverter;
import org.apache.commons.io.IOUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.aspsolutions.jforms.commons.AddressComparator;
import com.aspsolutions.jforms.commons.ConfigProperties;
import com.aspsolutions.jforms.commons.RunReports;
import com.aspsolutions.jforms.commons.Utils;

public class RunReportsAddressCustom {

	private static final String RUN_REPORTS_FILE = "/run-reports.xml";
	private static final String ATT_URL = "url";
	private static final String ATT_ALTERNATE_URL = "alternateUrl";
	private static final String PRIMARY_ADDRESS = "primaryAddress";
	private static final String SECONDARY_ADDRESS = "secondaryAddress";
	private static final String RESOURCES_URL = "/resources";
	private static final String NUMBER_SESSIONS_SERVLET="/WSAdminNode/getNumberSessions";
	public static final String USE_ALTERNATE_ADDRESS_REPORTS="UseAlternateAddress";
	
	private static List<ReportsAddress> listUrl;

	static {
        loadRunReportsAddress();
    }
	
	public static class ReportsAddress {

		private String url;
		
		private String alternateUrl;
		
		
		public ReportsAddress(Element element){
			this.url = element.getAttributeValue(ATT_URL);
			this.alternateUrl = element.getAttributeValue(ATT_ALTERNATE_URL);
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getAlternateUrl() {
			return alternateUrl;
		}

		public void setAlternateUrl(String alternateUrl) {
			this.alternateUrl = alternateUrl;
		}
		
	}
	
    private static void loadRunReportsAddress(){
		Document doc = getDocument();
		if (doc != null) {
			Element root = doc.getRootElement();
			if (root != null) {
				Element primary = root.getChild(PRIMARY_ADDRESS);
				addUrl(new ReportsAddress(primary));
				loadSecondaryAddress(root);
			}
		}
    }
    
    @SuppressWarnings("unchecked")
	private static void loadSecondaryAddress(Element root){
		List<Element> listSecondary = root.getChildren(SECONDARY_ADDRESS);
		if (listSecondary != null) {
			for(Element elem : listSecondary){
				addUrl(new ReportsAddress(elem));
			}
		}
    }
    
    private static void addUrl(ReportsAddress reportsAddress){
		if (listUrl == null) {
			listUrl = new ArrayList<ReportsAddress>();
		}
		//System.out.println("cargando la url de reportes=>"+reportsAddress.getUrl());
		listUrl.add(reportsAddress);
    }
    
    private static Document getDocument(){
    	InputStream input = ConfigProperties.class.getResourceAsStream(RUN_REPORTS_FILE);
        if (input != null) {
        	SAXBuilder builder=new SAXBuilder();
        	try {
				return builder.build(input);
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				closeInput(input);
			}
        }
        return null;
    }
    
    private static void closeInput(InputStream input){
    	if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    public static List<ReportsAddress> getListUrl() {
		return listUrl;
	}

    public static String getContext(String url){
    	int index = url.indexOf(RESOURCES_URL);
		return url.substring(0, index);
    }
    
    public static String getNameReport(String url){
    	int index = url.indexOf("=");
		return url.substring(index+1, url.length());
    }
    
    public static String getPriorityNode(String nameReport){
    	//Implementacion del balanceo de carga para los reportes
    	if(listUrl!=null && listUrl.size()==1){
    		//return listUrl.get(0).getUrl()+nameReport;
    		return listUrl.get(0).getUrl()+nameReport;
    	}else{
    		return getPriority()+nameReport;
    	}
    }
    
    @SuppressWarnings("unchecked")
	private static String getPriority(){
    	List<RunReports> list = new Vector<RunReports>();
    	for(ReportsAddress reportsAddress : listUrl){
    		RunReports run = getRunReportsInstance(reportsAddress);
    		if(run!=null){
    			list.add(run);
    		}
    	}
		if (list.size() == 0) {
    		//Si los n nodos de reportes est�n fuera de linea se manda el primero que se tenga en la lista del xml
    		//return listUrl.get(0).getUrl();
			return listUrl.get(0).getUrl();
    	}else if (list.size() > 1) {
    		//Ordeno la lista de menor a mayor
    		Collections.sort(list, new AddressComparator());
        	/*for(RunReports run : list){
        		//System.out.println("url=>"+run.getUrl()+"  sesiones =>"+run.getSessions());
        	}*/
    	}
		//En este punto validamos sobre cual url vamos a desplegar los reportes. MDIAZ 08/11/2016
    	//return list.get(0).getUrl();
		return list.get(0).getUrl();
    }
    
    
    private static RunReports getRunReportsInstance(ReportsAddress reportsAddress){
    	String urlWs = getContext(reportsAddress.getUrl())+NUMBER_SESSIONS_SERVLET;
    	InputStream input = Utils.consumeWebService(urlWs);
		if (input != null) {
			return new RunReports(reportsAddress.getUrl(), reportsAddress.getAlternateUrl(), getIntValue(input));
		}
    	return null;
    }
    
    private static Integer getIntValue(InputStream input){
    	try {
			ByteArrayOutputStream outStream=new ByteArrayOutputStream();
			IOUtils.copy(input,outStream);
			String value = new String(outStream.toByteArray());
			if(value!=null){
				return AllTypesConverter.getInt(value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
}
