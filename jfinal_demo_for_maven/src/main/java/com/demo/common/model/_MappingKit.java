package com.demo.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("blog", "id", Blog.class);
		arp.addMapping("childpackagelist", "packinglistid", Childpackagelist.class);
		arp.addMapping("company", "companyid", Company.class);
		arp.addMapping("motherpackagelist", "motherpackageid", Motherpackagelist.class);
		arp.addMapping("packing", "packinglistid", Packing.class);
		arp.addMapping("project", "projectid", Project.class);
	}
}

