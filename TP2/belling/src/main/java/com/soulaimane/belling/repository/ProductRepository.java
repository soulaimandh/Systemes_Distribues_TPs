package com.soulaimane.belling.repository;

import java.util.Collection;

import com.soulaimane.belling.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository  extends JpaRepository <ProductItem, Long>{
    public Collection<ProductItem> findByBillId(Long id);
}
