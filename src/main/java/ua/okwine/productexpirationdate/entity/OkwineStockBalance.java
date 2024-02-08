package ua.okwine.productexpirationdate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "okwine_stock_balance")
public class OkwineStockBalance {

    @Id
    @Column(name = "okwine_product_id")
    private String OkwineProductId;

    @Column(name = "stock_balance")
    private Integer stockBalance;
}
