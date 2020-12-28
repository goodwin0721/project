package pers.goodwin.shopSystem.constant;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public final class ClassifyConstant {
	public static Map<String ,String> CLASSIFYMAP = new HashMap<String, String>();
	static {
		CLASSIFYMAP.put("active", "生活用品");
		CLASSIFYMAP.put("toy", "玩具");
		CLASSIFYMAP.put("stationery", "文具");
		CLASSIFYMAP.put("snacks", "零食");
		CLASSIFYMAP.put("vegetables", "蔬菜");
		CLASSIFYMAP.put("meat", "肉类");
		CLASSIFYMAP.put("fruits", "水果");
	}
	@Test
	public void test() {
		System.out.println(ClassifyConstant.CLASSIFYMAP.size());
		System.out.println(ClassifyConstant.CLASSIFYMAP.get("active"));
	}
}
