package pers.goodwin.shopSystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	private int id;
	private int userId;
	private int goodsId;
	private int amount;
	private int price;
}
