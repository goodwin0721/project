package pers.goodwin.shopSystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto {
    private String name;
    private String describe;
    private Integer price;
    private Integer sum;
    private String classify;
    private String base64Img;
}
