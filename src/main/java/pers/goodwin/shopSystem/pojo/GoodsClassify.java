package pers.goodwin.shopSystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsClassify {
	private int id;
	private String classify;//分类
	private int sum;//该分类的商品总数

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
}
