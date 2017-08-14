package com.itheima.slorj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SlorjTest {

	@Test
	public void testCreateAndUpdateIndex() throws Exception{
		String baseURL = "http://localhost:8080/solr";
		HttpSolrServer httpSolrServer = new HttpSolrServer(baseURL);
		
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "c1001");
		document.addField("content", "hello world!");
		
		httpSolrServer.add(document);
		httpSolrServer.commit();
	}
	@Test
	public void testDeleteIndex() throws Exception{
		String baseURL = "http://localhost:8080/solr";
		HttpSolrServer httpSolrServer = new HttpSolrServer(baseURL);
		
		
		httpSolrServer.deleteByQuery("*:*");
		httpSolrServer.commit();
	}
	@Test
	public void testSearchIndex() throws Exception{
		String baseURL = "http://localhost:8080/solr";
		HttpSolrServer httpSolrServer = new HttpSolrServer(baseURL);
		
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		
		QueryResponse response = httpSolrServer.query(query);
		SolrDocumentList results = response.getResults();
		
		System.out.println("搜索总条数"+results.getNumFound());
		
		for (SolrDocument solrDocument : results) {
			System.out.println("---------------------");
			System.out.println("id:"+solrDocument.get("id"));
			System.out.println("content:"+solrDocument.get("content"));
		
		
		}
	}
	
}
