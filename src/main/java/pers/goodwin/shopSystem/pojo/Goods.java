package pers.goodwin.shopSystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author goodwin 商品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
	private int id;
	private String name;// 商品名称
	private String described;// 描述
	private int price;// 价格
	private int stock;// 库存
	private String pictureUrl;// 图片地址
	private int sold;// 已售


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribed() {
		return described;
	}

	public void setDescribed(String described) {
		this.described = described;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", described=" + described + ", price=" + price + ", stock="
				+ stock + ", pictureUrl=" + pictureUrl + ", sold=" + sold + "]";
	}

}
